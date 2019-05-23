package com.hgx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@Slf4j
public class TextMessageHandler extends TextWebSocketHandler {

    /**
     * All clients
     */
    private static Map<String, WebSocketSession> allClients = new HashMap<String, WebSocketSession>();

    /**
     * 当建立链接时调用
     *
     * @param session the session
     * @throws Exception the exception
     * @author : huangguixin / 2019-05-02
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        addUserMap(session);
    }

    /**
     * 发生异常时调用
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String name = removeUserMap(session);
        closeSession(session, name);
        log.info("发生异常" + "session" + session.getId() + "name" + name);
    }

    /**
     * Handle text message.
     *
     * @param session the session
     * @param message the message
     * @throws Exception the exception
     * @author : huangguixin / 2019-05-02
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        TextMessage textMessage = new TextMessage(message.asBytes());
        session.sendMessage(textMessage);
    }

    /**
     * 当关闭链接时调用
     *
     * @param session the session
     * @param status  the status
     * @throws Exception the exception
     * @author : huangguixin / 2019-05-02
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String name = removeUserMap(session);
        closeSession(session, name);
        log.info("链接关闭" + "session" + session.getId() + "name" + name);
    }

    /**
     * 移除用户与sesssion的关联
     */
    private String removeUserMap(WebSocketSession session) {
        String name = (String) session.getAttributes().get("name");
        allClients.remove(name);
        log.info("移除用户与sesssion的关联" + name + "session" + session.getId());
        return name;
    }

    /**
     * 添加用户与session的关联
     *
     * @param session
     */
    private String addUserMap(WebSocketSession session) {
        String name = (String) session.getAttributes().get("name");
        if (!StringUtils.isEmpty(name)) {
            allClients.put(name, session);
        }
        log.info("建立链接" + name + "session" + session.getId());
        return name;
    }

    /**
     * 关闭session
     *
     * @param session
     */
    private void closeSession(WebSocketSession session, String name) {
        if (session != null) {
            try {
                session.close();
            } catch (IOException e) {
                log.error("关闭session异常" + name);
            }
        }
    }
}
