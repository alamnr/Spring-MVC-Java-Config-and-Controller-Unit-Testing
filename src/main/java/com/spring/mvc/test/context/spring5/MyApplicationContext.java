package com.spring.mvc.test.context.spring5;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages= {"com.spring.mvc.test.service"})
@Import({MvcWebConfig.class})
public class MyApplicationContext {
	private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/welcome";

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }
}
