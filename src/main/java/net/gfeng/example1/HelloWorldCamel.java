package net.gfeng.example1;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class HelloWorldCamel {

	public static void main(String[] args) {

		
		
		HelloWorldRouter router = new HelloWorldRouter();

		try (CamelContext context = new DefaultCamelContext();){

			context.addRoutes(router);
			
			context.start();
			
			context.stop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
