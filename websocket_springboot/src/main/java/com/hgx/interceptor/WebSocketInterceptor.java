package com.hgx.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * websokcet握手的拦截器，检查握手的请求与响应，对websockethandler传递属性，用于区别websocket
 *
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@Slf4j
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        //我们规定的请求websocket的路径是restful风格的，且最后路径信息为姓名
        String url = request.getURI().toString();
        String name = url.substring(url.lastIndexOf("/") + 1);
        attributes.put("name", name);
        log.info("握手前" + name);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info("握手后");
    }

}
