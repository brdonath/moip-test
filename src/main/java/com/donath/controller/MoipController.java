package com.donath.controller;

import com.donath.model.Order;
import com.donath.repository.OrderRepository;
import com.donath.service.ConfirmationTopicService;
import com.fasterxml.jackson.databind.JsonNode;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by cin_bdonath on 21/03/2017.
 */
@Controller
@RequestMapping("/moip")
public class MoipController {

    @Autowired
    private ConfirmationTopicService confirmationTopicService;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/response")
    @ResponseStatus(HttpStatus.OK)
    public void response(@RequestBody JsonNode node) {
        String status = node.path("resource").path("payment").path("status").asText();
        String paymentId = node.path("resource").path("payment").path("id").asText();

        Order order = orderRepository.findOneByPaymentId(paymentId);
        if(order == null){
            return;
        }

        if (status.equals("WAITING") || status.equals("IN_ANALYSIS")) {
            order.setStatus(Order.Status.PROCESSING);

        } else if (status.equals("AUTHORIZED")) {
            order.setStatus(Order.Status.OK);
        }
        orderRepository.saveAndFlush(order);
        confirmationTopicService.send(paymentId, status);
    }
}
