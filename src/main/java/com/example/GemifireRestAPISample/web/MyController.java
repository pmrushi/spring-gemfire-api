package com.example.GemifireRestAPISample.web;

import com.example.GemifireRestAPISample.model.Customer;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    @Autowired
    private GemFireCache cache;

    @PostMapping("customer")
    @Cacheable("customer")
    public Customer post(@RequestBody Customer customer) {
        return customer;
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
        SelectResults<Customer> customers = (SelectResults)query.execute();
        System.out.println(customers);
        model.addAttribute("customers", customers);
        return customers;
    }
}
