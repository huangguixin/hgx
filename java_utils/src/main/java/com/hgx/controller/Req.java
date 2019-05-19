package com.hgx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Req {

    @GetMapping
    public void req(HttpServletRequest request) {
        System.out.println("request.getContextPath()---->" + request.getContextPath());
        System.out.println("request.getMethod()---->" + request.getMethod());
        System.out.println("request.getPathInfo()---->" + request.getPathInfo());
        System.out.println("request.getQueryString()---->" + request.getQueryString());
        System.out.println("request.getRemoteUser()---->" + request.getRemoteUser());
        System.out.println("request.getRequestedSessionId()---->" + request.getRequestedSessionId());
        System.out.println("request.getServletPath()---->" + request.getServletPath());
        System.out.println("request.getRequestURI()---->" + request.getRequestURI());
        System.out.println("request.getSession()---->" + request.getSession());
        System.out.println("request.getCookies()---->" + request.getCookies());
        System.out.println("request.getRequestURL()---->" + request.getRequestURL());
        System.out.println("request.getRemoteAddr()---->" + request.getRemoteAddr());
        System.out.println("request.getRemoteHost()---->" + request.getRemoteHost());
        System.out.println("request.getAuthType()---->" + request.getAuthType());
    }


}
