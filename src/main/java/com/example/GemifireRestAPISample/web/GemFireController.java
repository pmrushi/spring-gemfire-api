package com.example.GemifireRestAPISample.web;

import com.example.GemifireRestAPISample.model.CustomerObj;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class GemFireController {

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
        CustomerObj customerObj = new CustomerObj();
        customerObj.setFirstname("fname");
        customerObj.setLastname("lname");
        customerObj.setAge(10);
        return customerObj;
    }

    @GetMapping("/list")
    public Model list(Model model) throws Exception {
        model.addAttribute("customers", getAll(model));
        return model;
    }

    @GetMapping("/all")
    public SelectResults<CustomerObj> getAll(Model model) throws Exception {
        QueryService queryService = cache.getQueryService();
        Query query = queryService.newQuery("SELECT * FROM /customer");
        SelectResults<CustomerObj> customerObjs = (SelectResults)query.execute();
        System.out.println(customerObjs);
        return customerObjs;
    }
}
