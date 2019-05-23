package com.hgx;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@Data
public class Message {

    /**
     * 发送给谁
     */
    private String toUser;

    /**
     * 发送的消息
     */
    private String toMessage;

    /**
     * 发送的日期时间
     */
    private LocalDateTime localDateTime;

}
