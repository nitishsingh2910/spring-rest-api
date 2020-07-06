package com.nitish.springrestapi.api.v1.mapper;

import com.nitish.springrestapi.api.v1.model.CustomerDTO;
import com.nitish.springrestapi.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    public static final String FIRSTNAME = "Jimmy";
    public static final String LASTNAME = "Fallon";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void customerToCustomerDTO() {
        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }
}