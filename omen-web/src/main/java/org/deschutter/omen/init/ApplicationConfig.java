package org.deschutter.omen.init;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Configuration
@ComponentScan(basePackages = {"org.deschutter"}, excludeFilters = {
        @ComponentScan.Filter(value = Repository.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = Configuration.class)})
public class ApplicationConfig {
}
