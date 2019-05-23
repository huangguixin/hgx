package com.hgx;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * websocket与servlet的区别：
 * websocket是多实例的，因为websocket是实时链接的，需要知道每个链接是谁
 * servlet是单实例对象
 *
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@ServerEndpoint(value = "/websocket/{name}")
public class Websocket {

    /**
     * Name
     */
    private String name;
    //用于记录，当前websocket是谁

    /**
     * Session
     */
    private Session session;
    //链接，用于记录当前链接


    /**
     * 用于记录每个链接对应的websocket
     */
    private static Map<String, Websocket> allClients = new HashMap<String, Websocket>();

    /**
     * 当建立连接时，调用此方法
     *
     * @param name    地址参数中的name，用于区别链接是谁
     * @param session 当前建立的链接
     * @author : huangguixin / 2019-05-01
     */
    @OnOpen
    public void onOpen(@PathParam("name") String name, Session session) {
        this.name = name;
        this.session = session;
        allClients.put(name, this);
    }

    /**
     * 服务器接受消息触发
     * 接受客户端发送的请求，具体干嘛，实体情况决定
     * 比如聊天：
     * 这里就需要把消息发送到接受者哪里
     * 服务器要怎么知道发送给谁呢，to：zhan messge：nihao，data：2018-12-12
     *
     * @param session the session
     * @param message the message
     * @author : huangguixin / 2019-05-01
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        //解析发送过来的消息，该消息双方确定好
        System.out.println(message);
       /* Gson gson = new Gson();
        Message parseMessage = gson.fromJson(message, Message.class);
        Websocket websocket = allClients.get(parseMessage.getToUser());
        if (websocket != null) {
            Session remoteSession = websocket.getSession();
            if (remoteSession.isOpen()) {
                remoteSession.getAsyncRemote().sendText(parseMessage.getToMessage());
            }
        } else {
            //正常情况，缓存该消息，等用户上线时，发送给用户
            session.getAsyncRemote().sendText("对方不在线，请晚点再发");
        }*/
    }

    /**
     * 当出现异常或错误是触发
     *
     * @param session   the session
     * @param throwable the throwable
     * @author : huangguixin / 2019-05-01
     */
    @OnError
    public void onError(Session session, Throwable throwable) {

    }

    /**
     * 当关闭连接时触发
     *
     * @param session the session
     * @author : huangguixin / 2019-05-01
     */
    @OnClose
    public void onClose(Session session) {

    }

    public Session getSession() {
        return session;
    }
}
