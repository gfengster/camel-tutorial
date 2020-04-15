package net.gfeng.example1;

import org.apache.camel.builder.RouteBuilder;


public class HelloWorldRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		System.out.println("Hello World...");
	}
	
}
