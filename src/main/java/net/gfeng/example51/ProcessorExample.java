package net.gfeng.example51;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class ProcessorExample {

	public static void main(String[] args) throws Exception {

		// create the camel context
		CamelContext context = new DefaultCamelContext();

		// adding route to camel context
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start")
					.process(new MyProcessor())
					.to("seda:end");
			}
		});

		context.start();
		
		ProducerTemplate pTemplate = context.createProducerTemplate();
		pTemplate.sendBody("direct:start", "Dinesh Krishnan");
		
		ConsumerTemplate cTemplate = context.createConsumerTemplate();
		String message = cTemplate.receiveBody("seda:end", String.class);
		
		System.out.println(message);

		context.close();

	}

}
