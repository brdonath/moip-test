package com.donath.controller;

import br.com.moip.resource.Payment;
import com.donath.config.MoipAuth;
import com.donath.model.Order;
import com.donath.repository.OrderRepository;
import com.donath.service.MoipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cin_bdonath on 21/03/2017.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private MoipService moipService;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Order placeAnOrder(@RequestBody Order order){
        order.setStatus(Order.Status.PROCESSING);
        Order newOrder = moipService.placeMoipOrder(order);
        Payment payment = moipService.createPayment(newOrder);

        newOrder.setPaymentId(payment.getId());
        orderRepository.saveAndFlush(newOrder);
        return newOrder;
    }

}
