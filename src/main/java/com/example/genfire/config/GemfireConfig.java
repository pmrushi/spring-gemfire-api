package com.example.genfire.config;

import com.example.genfire.model.CustomerObj;
import org.apache.geode.cache.GemFireCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;

import java.util.Properties;

@Configuration
public class GemfireConfig {

    @Bean
    Properties gemfireProperties() {
        var properties = new Properties();
        properties.setProperty("name", "EmbeddedGemfireApplication");
        properties.setProperty("mcast-port", "0");
        return properties;
    }

    @Bean
    CacheFactoryBean gemfireCache() {
        var gemfireCache = new CacheFactoryBean();
        gemfireCache.setProperties(gemfireProperties());
        return gemfireCache;
    }

    @Bean
    LocalRegionFactoryBean<String, CustomerObj> customerRegion(final GemFireCache cache) {
        LocalRegionFactoryBean<String, CustomerObj> customerRegion = new LocalRegionFactoryBean<>();
        customerRegion.setCache(cache);
        customerRegion.setName("customer");
        customerRegion.setPersistent(false);
        return customerRegion;
    }

}