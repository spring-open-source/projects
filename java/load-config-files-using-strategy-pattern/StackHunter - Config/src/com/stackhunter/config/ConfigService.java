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

public class ConfigService {
    
    private static ConfigLoader[] LOADERS = new ConfigLoader[]{
        new SystemPropertyConfigLoader(),
        new EnvironmentVariableConfigLoader(),
        new ServletContextConfigLoader(),
        new TomcatConfigLoader(),
        new ClasspathConfigLoader()
    };
    
    public static Properties getConfig(String file) {
        for (ConfigLoader loader : LOADERS) {
            Properties config = loader.getConfig(file);
            if (config != null) {
                return config;
            }
        }
        
        throw new RuntimeException("config file not found: " + file);
    }
    
}
