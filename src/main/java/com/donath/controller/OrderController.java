package com.donath.controller;

import br.com.moip.resource.Payment;
import com.donath.model.Order;
import com.donath.repository.OrderRepository;
import com.donath.service.MoipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private MoipService moipService;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Order placeAnOrder(@RequestBody Order order) {
        order.setStatus(Order.Status.PROCESSING);
        order.setFinalPrice(calcPrice(order));

        Order newOrder = moipService.placeMoipOrder(order);
        Payment payment = moipService.createPayment(newOrder);

        newOrder.setPaymentId(payment.getId());
        orderRepository.saveAndFlush(newOrder);
        return newOrder;
    }

    private BigDecimal calcPrice(Order order) {
        BigDecimal finalPrice = order.getProduct().getPrice();
        if (order.hasCupom()) {
            finalPrice = finalPrice.multiply(BigDecimal.valueOf(0.95));
        }
        if (order.getInstallments() > 1) {
            finalPrice = finalPrice.multiply(BigDecimal.valueOf(1.025));
        }
        return finalPrice;

    }

}
