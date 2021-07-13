package com.example.GemifireRestAPISample.web;

import com.example.GemifireRestAPISample.model.Customer;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@Component
public class MyController {

    private Random random;
    private MeterRegistry meterRegistry;
    @Autowired
    private GemFireCache cache;

    public MyController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.random = new Random();
    }

    @GetMapping("hello/{id}")
    public String getSample() {
        int wait = random.nextInt(100);
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
    public Model list(Model model) throws Exception {
        model.addAttribute("customers", getAll(model));
        return model;
    }

    @GetMapping("/all")
    public SelectResults<Customer> getAll(Model model) throws Exception {
        QueryService queryService = cache.getQueryService();
        Query query = queryService.newQuery("SELECT * FROM /customer");
        SelectResults<Customer> customers = (SelectResults) query.execute();
        System.out.println(customers);
        return customers;
    }
}
