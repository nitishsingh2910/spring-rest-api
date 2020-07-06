package com.nitish.springrestapi.api.v1.mapper;

import com.nitish.springrestapi.api.v1.model.CustomerDTO;
import com.nitish.springrestapi.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

//    @Mapping(target = "id", ignore = true)
    CustomerDTO customerToCustomerDTO(Customer customer);
}
