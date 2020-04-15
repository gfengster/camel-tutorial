package net.gfeng.example2;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class FileCopyCamel {

	public static void main(String[] args) {
		

		FileCopyRouter router = new FileCopyRouter();

		try (CamelContext context = new DefaultCamelContext();){

			context.addRoutes(router);

			while(true) {
				context.start();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
