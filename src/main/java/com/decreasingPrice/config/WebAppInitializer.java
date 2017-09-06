package com.decreasingPrice.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer{

    private static final Logger LOGGER = Logger.getLogger(WebApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LOGGER.debug("WebAppInitializer.onStartup is called");

        // Registering WebConfig class for further servlet configuration
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebConfig.class);

        // manage the lifecycle of rootcontext
        servletContext.addListener(new ContextLoaderListener(rootContext));

        //activate spring security by configuring filter
        //all requests will path through this filter
        DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");

        // Define and register a dispatcher servlet that can manage all servlets
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);

        //adding a filter to servletContext that handles all requests
        servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");

        // allow lazy loading in web views despite the original transactions already being completed
        servletContext.addFilter("OpenEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class)
                .addMappingForUrlPatterns(null, false, "*");

        //filter for encoding all request parameters to unicode
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        servletContext.addFilter("encode", characterEncodingFilter)
                .addMappingForUrlPatterns(null, false, "/*");

        // load servlet only once
        registration.setLoadOnStartup(1);

        // add mapping this servlet to all requests
        registration.addMapping("/*");

    }
}
