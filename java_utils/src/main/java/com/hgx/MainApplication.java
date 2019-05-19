package com.hgx;

import com.hgx.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class,args);
//        IdWorker idWorker = new IdWorker(1, 1);
//        System.out.println(idWorker.nextId());
    }

}
