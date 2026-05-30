package com.lyevsky.microservices.order.orderline;


import com.lyevsky.microservices.order.dao.Order;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Long id;
    private double quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Long productId;

}
