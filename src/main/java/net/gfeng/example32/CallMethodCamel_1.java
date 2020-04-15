package net.gfeng.example32;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class CallMethodCamel_1 {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("direct:start").to("class:com.dineshkrish.example32.MyService?method=doSomething");
			}
		});

		context.start();

		ProducerTemplate template = context.createProducerTemplate();

		template.sendBody("direct:start", "Your message goes here....");

		context.close();

	}
}
