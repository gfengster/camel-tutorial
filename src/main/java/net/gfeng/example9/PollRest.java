package net.gfeng.example9;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class PollRest {

	public static void main(String[] args) throws Exception {

		try (CamelContext context = new DefaultCamelContext();) {

			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {

					MyRestService service = new MyRestService();
					
					from("timer://poll?fixedRate=true&delay=0&period=500000") // start immediately and poll every 5 second
						.to("https://www.bbc.co.uk/news") 
						.bean(service, "doSomething");
				}
			});

			while (true) {
				context.start();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
