package com.lyevsky.microservices.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RequestCustomer(
        String id,

        @NotNull(message = "First name is required")
        String firstName,

        @NotNull(message = "Last name is required")
        String lastName,

        @NotNull(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        Address address
) {

}
