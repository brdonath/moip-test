package com.donath.interceptor;

import com.donath.service.ConfirmationTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StompConnectEvent implements ApplicationListener<SessionSubscribeEvent> {

    private static final Pattern p = Pattern.compile("/topic/pay/(.*)");

    @Autowired
    private ConfirmationTopicService confirmationTopicService;

    public void onApplicationEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        Matcher matcher = p.matcher(sha.getNativeHeader("destination").get(0));

        if(matcher.find()){
            String paymentTopic = matcher.group(1);
            confirmationTopicService.addSubs(paymentTopic);
        }
    }
}