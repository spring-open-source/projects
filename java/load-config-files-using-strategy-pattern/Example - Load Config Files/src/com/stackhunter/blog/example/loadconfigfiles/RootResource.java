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
package com.stackhunter.blog.example.loadconfigfiles;

import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.stackhunter.config.ConfigService;


@Path("/")
@Produces(MediaType.TEXT_HTML)
public class RootResource {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHome() throws Throwable {
        StringBuilder s = new StringBuilder();
        
        getConfig(s, "config-jvm.properties");
        getConfig(s, "config-env.properties");
        getConfig(s, "config-servlet.properties");
        getConfig(s, "config-tomcat.properties");
        getConfig(s, "config-classpath.properties");
        getConfig(s, "config-nowhere.properties");
        
        return s.toString();
    }

    private void getConfig(StringBuilder s, String file) {
        s.append("\n\n" + file + ":\n");
        try {
            Properties config = ConfigService.getConfig(file);
            if (config == null) {
                s.append("\t" + "no file found");
            } else {
                s.append("\t" + "location=" + config.getProperty("location"));
            }
        } catch (Throwable e) {
            s.append("\t" + "no file found - " + e.getMessage());
        }
    }
    
}
