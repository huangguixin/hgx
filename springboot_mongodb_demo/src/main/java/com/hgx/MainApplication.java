package com.hgx;

import com.hgx.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@SpringBootApplication
public class MainApplication {

    /**
     * @param args the input arguments
     * @author : huangguixin / 2019-03-12
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
