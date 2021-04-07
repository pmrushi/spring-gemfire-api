package com.example.GemifireRestAPISample.config;

import com.example.GemifireRestAPISample.model.Customer;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;

import java.util.Properties;

@Configuration
public class GemfireConfig {

    @Bean
    Properties gemfireProperties() {
        Properties gemfireProperties = new Properties();
        gemfireProperties.setProperty("name", "EmbeddedGemfireApplication");
        gemfireProperties.setProperty("mcast-port", "0");
        return gemfireProperties;
    }

    @Bean
    CacheFactoryBean gemfireCache() {
        CacheFactoryBean gemfireCache = new CacheFactoryBean();
        gemfireCache.setProperties(gemfireProperties());
        return gemfireCache;
    }

   /* @Bean
    ClientRegionFactoryBean<Long, Customer> customersRegion(GemFireCache cache) {
        ClientRegionFactoryBean<Long, Customer> customers = new ClientRegionFactoryBean<>();
        customers.setCache(cache);
        customers.setPersistent(false);
        customers.setShortcut(ClientRegionShortcut.PROXY);
        return customers;
    }*/

/*
    @Bean
    public GemfireTemplate exampleLocalRegionTemplate(Cache gemfireCache) throws Exception {
        return new GemfireTemplate(customerRegion(gemfireCache).getObject());
    }
*/

    @Bean
    LocalRegionFactoryBean<String, Customer> customerRegion(final GemFireCache cache) {
        LocalRegionFactoryBean<String, Customer> customerRegion = new LocalRegionFactoryBean<>();
        customerRegion.setCache(cache);
        customerRegion.setName("customer");
        customerRegion.setPersistent(false);
        return customerRegion;
    }

}