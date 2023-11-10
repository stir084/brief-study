package com.example.java;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();

    private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : " + payload);

        if(numSet.size()>=3){
            System.out.println("사이즈 3개넘김");
            WebSocketSession oldSession = numSet.iterator().next();
            oldSession.sendMessage(new TextMessage("채팅이 종료되었습니다."));
            numSet.remove(numSet.iterator().next());
            System.out.println("사이즈 줄임" + numSet.size());
        }

        boolean isSessionAlive = false;

        for(WebSocketSession sess: numSet) {
            if(sess.getId().equals(session.getId())){
                isSessionAlive = true;
            }
        }
        if(isSessionAlive == true){
            for(WebSocketSession sess: numSet) {
                sess.sendMessage(message);
            }
        }

    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
       // session.getHandshakeHeaders().set("Sec-WebSocket-Protocol", "dfdf");
        log.info("클라이언트 접속 완료");
        int size = numSet.size();

        numSet.add(session);
    }


    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + " 클라이언트 접속 해제");
        numSet.remove(session);
    }
}