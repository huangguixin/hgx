package com.hgx;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "hgx.queue", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "hgx.exchange", ignoreDeclarationExceptions = "true",
                    type = "direct", durable = "true", autoDelete = "false"),
            key = {"message"}
    ))
    public void consumerMessage(String message) {
        System.out.println(message);
    }


}
