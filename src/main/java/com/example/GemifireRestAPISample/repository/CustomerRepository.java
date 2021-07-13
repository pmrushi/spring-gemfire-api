package com.example.GemifireRestAPISample.repository;

import com.example.GemifireRestAPISample.model.CustomerObj;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends GemfireRepository<CustomerObj, Long> {
    CustomerObj findByFirstname(@Param("firstname") String firstname);

    CustomerObj findByLastname(@Param("lastname") String lastname);

    Iterable<CustomerObj> findByAgeGreaterThan(@Param("age") int age);

    Iterable<CustomerObj> findByAgeLessThan(@Param("age") int age);
}