package com.donath.controller;

import com.donath.model.Order;
import com.donath.model.moip.Payment;
import com.donath.model.moip.Response;
import com.donath.repository.OrderRepository;
import com.donath.service.ConfirmationTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/moip")
public class MoipController {

    @Autowired
    private ConfirmationTopicService confirmationTopicService;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/response", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void response(@RequestBody Response response) throws InterruptedException {

        Payment payment = response.getResource().getPayment();
        Order order = orderRepository.findOneByPaymentId(payment.getId());
        if(order != null && "AUTHORIZED".equals(payment.getStatus())){
            order.setStatus(Order.Status.OK);
            orderRepository.saveAndFlush(order);
        }

        confirmationTopicService.send(payment);
    }
}
