package com.stackhunter.jdbc.mysql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class ExplainInvocationHandler implements InvocationHandler {
    
    private static final long EXPLAIN_THRESHOLD = 10L;
    
    private final static Logger log = Logger.getLogger(ExplainInvocationHandler.class);

    public static JdbcOperations wrap(JdbcOperations db) {
        return (JdbcOperations) Proxy.newProxyInstance(
                ExplainInvocationHandler.class.getClassLoader(), 
                new Class[]{JdbcOperations.class}, 
                new ExplainInvocationHandler(db));
    } 
    
    private final JdbcOperations db;

    private ExplainInvocationHandler(JdbcOperations db) {
        this.db = db;
        log.info(ExecutionPlan.toHeaderTSV());        
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return method.invoke(db, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        } catch (Throwable e) {
            throw e;
        } finally {
            try {
                long duration = System.currentTimeMillis() - start;
                if (duration >= EXPLAIN_THRESHOLD) {
                    explain(method, args, duration);
                }
            } catch (Throwable e) {
                log.warn(e, e);
            }
        }
    }

    private void explain(Method method, Object[] args, long duration) throws IllegalAccessException, InvocationTargetException {
        final String methodName = method.getName();
        if (!methodName.startsWith("query") && !methodName.startsWith("update") && !methodName.startsWith("execute")) {
            return;
        }
        
        final Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 0 || parameterTypes[0] != String.class) {
            return;
        }
        final String sql = (String) args[0];

        int argumentsIndex = findParameter(parameterTypes, Object[].class);
        int argumentTypesIndex = findParameter(parameterTypes, int[].class);
        int preparedStatementSetterIndex = findParameter(parameterTypes, PreparedStatementSetter.class);
        
        List<ExecutionPlan> executionPlan;
        if (argumentsIndex > 0 && argumentTypesIndex > 0) {
            executionPlan = db.query("EXPLAIN " + sql, (Object[])args[1], (int[])args[2], new ExecutionPlanRowMapper(sql, duration));
        } else if (preparedStatementSetterIndex > 0) {
            executionPlan = db.query("EXPLAIN " + sql, (PreparedStatementSetter)args[1], new ExecutionPlanResultSetExtractor(sql, duration));
        } else if (argumentsIndex > 0) {
            executionPlan = db.query("EXPLAIN " + sql, (Object[])args[1], new ExecutionPlanRowMapper(sql, duration));
        } else {
            executionPlan = db.query("EXPLAIN " + sql , new ExecutionPlanRowMapper(sql, duration));
        }

        for (ExecutionPlan plan : executionPlan) {
            log.info(plan.toTSV());
        }
    }

    private static int findParameter(Class<?>[] parameterTypes, Class<?> type) {
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == type) {
                return i;
            }
        }
        return -1;
    }

}
