package com.bunch_of_keys.bunch;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("table-orders");
        registry.addViewController("/orders").setViewName("table-orders");
        registry.addViewController("/customers").setViewName("customers");
        registry.addViewController("/order/{orderId}").setViewName("order-page");
        registry.addViewController("/template").setViewName("template");
        registry.addViewController("/services").setViewName("services");
        registry.addViewController("/cost/types").setViewName("cost-types");
        registry.addViewController("/stuff").setViewName("stuff");
        registry.addViewController("/costs-table-page").setViewName("costsPages/costsTablePage");
        registry.addViewController("/newCostPage").setViewName("costsPages/newCostPage");
        registry.addViewController("/cost/{costId}").setViewName("costsPages/theCostPage");
        registry.addViewController("/new-order").setViewName("new-order");
        registry.addViewController("/grid").setViewName("grid");
        registry.addViewController("/test").setViewName("test");
    }

}
