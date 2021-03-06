/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.grails.web.context

import groovy.transform.CompileStatic
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.support.GrailsApplicationDiscoveryStrategy
import org.springframework.context.ApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

import javax.servlet.ServletContext

/**
 * Strategy for discovering the GrailsApplication and ApplicationContext instances in the Servlet environment
 *
 * @author Graeme Rocher
 * @since 2.4
 */
@CompileStatic
class ServletEnvironmentGrailsApplicationDiscoveryStrategy implements GrailsApplicationDiscoveryStrategy{
    ServletContext servletContext

    ServletEnvironmentGrailsApplicationDiscoveryStrategy(ServletContext servletContext) {
        this.servletContext = servletContext
    }
    @Override
    public GrailsApplication findGrailsApplication() {
        return findApplicationContext().getBean(GrailsApplication.APPLICATION_ID, GrailsApplication.class);
    }

    @Override
    public ApplicationContext findApplicationContext() {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }
}
