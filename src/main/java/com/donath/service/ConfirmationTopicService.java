package com.donath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by cin_bdonath on 22/03/2017.
 */
@Service
public class ConfirmationTopicService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ConfirmationTopicService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void send(String paymentId, final String status) {
        this.messagingTemplate.convertAndSend("/topic/" + paymentId, status);
    }
}
