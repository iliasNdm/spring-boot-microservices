package com.lyevsky.microservices.order.product;


import com.lyevsky.microservices.order.orderline.OrderLineRequest;
import com.lyevsky.microservices.order.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.*;

@RequiredArgsConstructor
@Service
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<ProductPurchaseResponse> purchaseProducts(List<OrderLineRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<List<OrderLineRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<ProductPurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};
        try {
            ResponseEntity<List<ProductPurchaseResponse>> responseEntity = restTemplate.exchange(
                    productUrl + "/purchase",
                    org.springframework.http.HttpMethod.POST,
                    requestEntity,
                    responseType
            );
            return responseEntity.getBody();
        } catch (RestClientException e) {
            throw new BusinessException("An error occurred while processing the products purchase: " + e.getMessage());
        }
    }
}
