package com.example.genfire.web;

import com.example.genfire.model.Customer;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.query.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@Component
public class MyController {

    Logger logger = LoggerFactory.getLogger(MyController.class);

    private Random random;
    private MeterRegistry meterRegistry;
    @Autowired
    private GemFireCache cache;

    public MyController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.random = new SecureRandom();
    }

    @GetMapping("hello/{id}")
    public String getSample() {
        var wait = random.nextInt(100);
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Error", e);
        }
        meterRegistry.timer("sample").record(wait, TimeUnit.MILLISECONDS);
        return "hello-" + wait;
    }

    @GetMapping("sample")
    @Cacheable("customer")
    public Customer getMessage(@PathVariable String id) {
        meterRegistry.counter("hello").increment();
        return new Customer(new Date(), "Welcome to Gemfire", id);
    }

    @GetMapping("/list")
    public Model list(Model model) {
        model.addAttribute("customers", getAll());
        return model;
    }

    @GetMapping("/all")
    public SelectResults<Customer> getAll() {
        var queryService = cache.getQueryService();
        var query = queryService.newQuery("SELECT * FROM /customer");
        SelectResults<Customer> customers = null;
        try {
            customers = (SelectResults) query.execute();
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return customers;
    }
}
