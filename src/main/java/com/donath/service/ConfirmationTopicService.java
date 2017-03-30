package com.donath.service;

import com.donath.model.Order;
import com.donath.model.StalledEvent;
import com.donath.model.Subscriber;
import com.donath.model.moip.Payment;
import com.donath.repository.StalledEventRepository;
import com.donath.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfirmationTopicService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private StalledEventRepository stalledEventRepository;

    @Autowired
    public ConfirmationTopicService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void send(Payment payment) {
        StalledEvent stalledEvent = new StalledEvent();
        stalledEvent.setPaymentId(payment.getId());
        stalledEvent.setStatus(getStatus(payment.getStatus()));
        stalledEventRepository.saveAndFlush(stalledEvent);

        send(payment.getId());
    }

    private Order.Status getStatus(String status) {
        Order.Status updatedStatus;
        switch (status) {
            case "AUTHORIZED":
                updatedStatus = Order.Status.OK;
                break;
            default:
                updatedStatus = Order.Status.PROCESSING;
        }
        return updatedStatus;
    }

    public void addSubs(String paymentId) {
        subscriberRepository.saveAndFlush(new Subscriber(paymentId));
        send(paymentId);
    }

    private void send(String paymentId) {
        if (subscriberRepository.getOne(paymentId) != null) {
            List<StalledEvent> byPaymentId = stalledEventRepository.findByPaymentId(paymentId);
            byPaymentId.stream()
                    .filter((ev) -> !ev.isSent())
                    .forEach((ev) -> {
                        messagingTemplate.convertAndSend("/topic/pay/" + paymentId, ev.getStatus().name());
                        ev.setSent(true);
                        stalledEventRepository.saveAndFlush(ev);
                    });
        }
    }
}
