/*
 * Copyright (c) 2014 North Concepts Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.stackhunter.config;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextConfigLoader extends FileConfigLoader implements ServletContextListener {

    private static ServletContext servletContext;

    @Override
    public Properties getConfig(String file) {
        ServletContext localServletContext = servletContext;
        
        if (localServletContext == null) {
            return null;
        }
        
        return getConfigImpl(localServletContext.getInitParameter("config"), file);
    }
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        servletContext = event.getServletContext();
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        servletContext = null;
    }

}
