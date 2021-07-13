package com.example.GemifireRestAPISample.web;

import com.example.GemifireRestAPISample.model.Customer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.internal.LinkedResultSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyControllerTest {

    @Mock
    private GemFireCache cache;

    @Mock
    private QueryService queryService;

    @Mock
    private Query query;

    @InjectMocks
    private MyController myController = new MyController(new SimpleMeterRegistry());

    @Test
    public void getSample() {
        String sample = myController.getSample();
        assertTrue(sample.startsWith("hello-"));
    }

    @Test
    public void getMessage() {
        Customer customer = myController.getMessage("id");
        assertEquals("Welcome to Gemfire", customer.getMessage());
        assertEquals("id", customer.getId());
    }

    @Test
    public void list() throws Exception {
        ExtendedModelMap extendedModelMap = new ExtendedModelMap();
        when(cache.getQueryService()).thenReturn(queryService);
        when(queryService.newQuery("SELECT * FROM /customer")).thenReturn(query);
        LinkedResultSet linkedResultSet = new LinkedResultSet();
        when(query.execute()).thenReturn(linkedResultSet);
        Model model = myController.list(extendedModelMap);
        SelectResults<Customer> customers = (SelectResults<Customer>) model.getAttribute("customers");
        assertEquals(0, customers.size());
    }

    @Test
    public void getAll() throws Exception {
        ExtendedModelMap model = new ExtendedModelMap();
        when(cache.getQueryService()).thenReturn(queryService);
        when(queryService.newQuery("SELECT * FROM /customer")).thenReturn(query);
        LinkedResultSet linkedResultSet = new LinkedResultSet();
        when(query.execute()).thenReturn(linkedResultSet);
        SelectResults<Customer> customers = myController.getAll(model);
        assertEquals(0, customers.size());
    }
}