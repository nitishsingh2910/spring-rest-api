package com.nitish.springrestapi.repositories;

import com.nitish.springrestapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
