package servlet.filter;

import cache.memcached.JMemcachedClient;
import java.io.IOException;
import java.security.Key;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;

/**
 * Memcache Servlet filter to implement caching at request response level
 *
 * @author hardiku
 */
public class MemcacheFilter implements Filter
{

    private static Logger logger = Logger.getLogger(MemcacheFilter.class);

    private CacheConfig cacheConfig;

    /**
     * Memcached lookup is being performed in this method. Firstly, keys are
     * generated depending on the request method (GET/POST). Then a cache lookup
     * is performed. If a value is obtained, the value is written to the
     * response otherwise, the actual target (in this case, Spring's Dispatcher
     * Servlet) is called by calling doFilter on the filteChain. The dispatcher
     * servlet calls the controller to produce the desire response which is
     * intercepted when the doFilter method returns. The Response is added to
     * the cache if the reponse code was 200(OK).
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException
    {

        try
        {

            if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse))
            {

                // Wrapping the response in HTTPServletResponseWrapper
                MemcacheResponseWrapper responseWrap = new MemcacheResponseWrapper((HttpServletResponse) response);

                // Wrapping the request in HTTPServletResponseWrapper
                MemcacheRequestWrapper requestWrap = new MemcacheRequestWrapper((HttpServletRequest) request);

                // Get Memcached Client Instance
                JMemcachedClient client = new JMemcachedClient();

                Key keyGenerator = getKeyGenerator(requestWrap);

                if(keyGenerator != null)
                {

                    String key = keyGenerator.getKey(requestWrap, cacheConfig);
                    String value = (String) client.get(key);

                    if(value == null)
                    {
                        // cache miss
                        logger.info("Cache miss for key " + key);

                        // call next filter/actual target for value
                        filterChain.doFilter(requestWrap, responseWrap);

                        if(responseWrap.getStatus() == HttpServletResponse.SC_OK)
                        {

                            // obtaining response content from
                            // HttpServletResponseWrapper
                            value = responseWrap.getOutputStream().toString();

                            // adding response to cache
                            client.add(key, 0, value);

                            logger.info("Adding response to cache: " + (value.length() > 50 ? value.substring(0, 50) + "..." : value));
                        }
                        else
                        {
                            logger.warn("Did not add content to cache as response status is not 200");
                        }
                    }
                    else
                    {
                        // This case is a cache hit
                        logger.info("Cache hit for key " + key);

                        response.getWriter().println(value);
                    }

                }
                else
                {
                    logger.warn("Request skipped because no key generator could be found for the request's method");
                    // attempting call to actual target
                    filterChain.doFilter(request, response);
                }
            }
        }
        catch(Exception ex)
        {
            logger.info("Cache functionality skipped due to exception", ex);

            // attempting call to actual target
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Factory method that returns KeyGenerator based on the request method.
     *
     * @param httpRequest
     * @return
     */
    private Key getKeyGenerator(HttpServletRequest httpRequest)
    {

        Key keyGenerator = null;

        if(httpRequest.getMethod().equalsIgnoreCase("GET"))
        {
            keyGenerator = new GetRequestKey();
        }
        else if(httpRequest.getMethod().equalsIgnoreCase("POST"))
        {
            keyGenerator = new PostRequestKey();
        }

        return keyGenerator;
    }

    public void init(FilterConfig arg0) throws ServletException
    {
        logger.debug("init");
    }

    public CacheConfig getCacheConfig()
    {
        return cacheConfig;
    }

    public void setCacheConfig(CacheConfig cacheConfig)
    {
        this.cacheConfig = cacheConfig;
    }

    public void destroy()
    {
        logger.debug("destroy");
    }

}
