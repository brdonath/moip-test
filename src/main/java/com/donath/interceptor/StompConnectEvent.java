package com.donath.interceptor;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

public class StompConnectEvent implements ApplicationListener<SessionConnectEvent> {

    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());

        String  company = sha.getNativeHeader("company").get(0);
        System.out.println("Connect event [sessionId: " + sha.getSessionId() +"; company: "+ company + " ]");
    }
}