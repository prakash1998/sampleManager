package com.pra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource(value = { "file:resources/config.properties" })
@Component
public class AppConfigProperties {

    @Autowired
    private Environment env;

    private String getProperty(String pPropertyKey) {
        return env.getProperty(pPropertyKey);
    }
    
    public String getComapanyName() {
    	return getProperty("company");
    }
    
    public String getReportsPath() {
    	return getProperty("reportFolder");
    }
    
    public String getSuggetionFilePath() {
    	return getProperty("suggestionFile");
    }
    
    
} 