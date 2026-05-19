package com.lyevsky.microservices.customer.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {}