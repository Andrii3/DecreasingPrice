package com.decreasingPrice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@EnableWebMvc
@Configuration
public class TilesConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = Logger.getLogger(TilesConfig.class);

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/layouts/tiles-definitions.xml");
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.viewResolver(viewResolver());
        registry.viewResolver(jspViewResolver());
        registry.viewResolver(tilesViewResolver());
    }

//    @Bean
//    public MultipleViewResolver viewResolver() {
//        Map<String, ViewResolver> viewsResolvers = new HashMap<String, ViewResolver>();
//        viewsResolvers.put(MultipleViewResolver.ViewType.JSP.getKey(), jspViewResolver());
//        viewsResolvers.put(MultipleViewResolver.ViewType.TILES.getKey(), tilesViewResolver());
//
//        MultipleViewResolver viewResolver = new MultipleViewResolver();
//        viewResolver.setViewsResolvers(viewsResolvers);
//        viewResolver.setOrder(1);
//        return viewResolver;
//    }

    @Bean
    public UrlBasedViewResolver tilesViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setViewClass(TilesView.class);
        urlBasedViewResolver.setOrder(1);
        return urlBasedViewResolver;
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(0);
        return viewResolver;
    }



}
