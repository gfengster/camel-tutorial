package net.gfeng.example4;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;


public class FileToActiveMQ {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		ActiveMQComponent connectionFactory = new ActiveMQComponent(context);
		
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory.getConnectionFactory()));

		try {
			context.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("file:input_dir?noop=true")
						.to("activemq:queue:my_queue");
				}
			});

			while (true) {
				context.start();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			connectionFactory.close();
		}

	}
}
