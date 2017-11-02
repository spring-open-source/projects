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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public abstract class FileConfigLoader implements ConfigLoader {

    protected Properties getConfigImpl(String folder, String filename) {

        if (folder == null || folder.length() == 0) {
            return null;
        }
    
        File file = new File(folder, filename);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
    
        try {
            Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            try {
                Properties properties = new Properties();
                properties.load(reader);
                return properties;
            } finally {
                reader.close();
            }
        } catch (Throwable e) {
            throw new RuntimeException("Unable to read property file: " + file.getAbsolutePath(), e);
        }
    }

}