package com.example.GemifireRestAPISample.repository;

import com.example.GemifireRestAPISample.model.Customer;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends GemfireRepository<Customer, Long> {
    Customer findByFirstname(@Param("firstname") String firstname);

    Customer findByLastname(@Param("lastname") String lastname);

    Iterable<Customer> findByAgeGreaterThan(@Param("age") int age);

    Iterable<Customer> findByAgeLessThan(@Param("age") int age);
}