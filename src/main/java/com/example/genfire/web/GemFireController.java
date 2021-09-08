package com.example.genfire.web;

import com.example.genfire.model.CustomerObj;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.query.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class GemFireController {

    Logger logger = LoggerFactory.getLogger(GemFireController.class);

    @Autowired
    private GemFireCache cache;

    @PostMapping("customer")
    @Cacheable("customer")
    public CustomerObj post(@RequestBody CustomerObj customerObj) {
        return customerObj;
    }

    @GetMapping("hello/{id}")
    @Cacheable("customer")
    public CustomerObj hello(@PathVariable String id) {
        var customerObj = new CustomerObj();
        customerObj.setFirstname("fname");
        customerObj.setLastname("lname");
        customerObj.setAge(10);
        return customerObj;
    }

    @GetMapping("/list")
    public Model list(Model model) {
        model.addAttribute("customers", getAll());
        return model;
    }

    @GetMapping("/all")
    public SelectResults<CustomerObj> getAll() {
        var queryService = cache.getQueryService();
        var query = queryService.newQuery("SELECT * FROM /customer");
        SelectResults<CustomerObj> customerObjs = null;
        try {
            customerObjs = (SelectResults)query.execute();
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return customerObjs;
    }
}
