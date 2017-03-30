package com.donath.service;

import com.donath.model.moip.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTopicService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ConfirmationTopicService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void send(Payment payment) {
        this.messagingTemplate.convertAndSend("/topic/" + payment.getId(), payment.getStatus());
    }
}
