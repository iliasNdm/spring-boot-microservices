package com.lyevsky.microservices.customer.mappers;

import com.lyevsky.microservices.customer.dto.CustomerResponse;
import com.lyevsky.microservices.customer.dto.RequestCustomer;
import com.lyevsky.microservices.customer.dao.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(RequestCustomer request) {
        if (request == null) return null;
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
