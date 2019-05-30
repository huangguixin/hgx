package com.hgx;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
public class MainApplication {

	/**
	 * @param args the input arguments
	 * @throws IOException the io exception
	 * @author : huangguixin / 2019-05-27
	 */
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("provider.xml");
		ioc.start();
		
		System.in.read();
	}

}
