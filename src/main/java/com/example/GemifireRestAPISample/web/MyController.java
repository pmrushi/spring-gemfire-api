package com.example.GemifireRestAPISample.web;

import com.example.GemifireRestAPISample.model.Customer;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private GemFireCache cache;

    @GetMapping("/all")
    public SelectResults<Customer> test() throws Exception {
        QueryService queryService = cache.getQueryService();
        Query query = queryService.newQuery("SELECT * FROM /customer");
        SelectResults<Customer> results = (SelectResults)query.execute();
        System.out.println(results);
        return results;
    }
}
