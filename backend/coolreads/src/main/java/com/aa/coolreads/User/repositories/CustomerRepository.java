package com.aa.coolreads.User.repositories;

import com.aa.coolreads.User.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
}
