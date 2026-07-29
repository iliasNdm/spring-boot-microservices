package com.lyevsky.microservices.notification;

import com.lyevsky.microservices.kafka.order.OrderConfirmation;
import com.lyevsky.microservices.kafka.payment.PaymentConfirmation;
import lombok.*;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}