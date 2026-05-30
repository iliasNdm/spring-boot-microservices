package com.lyevsky.microservices.customer.dto;

import com.lyevsky.microservices.customer.dao.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {}
