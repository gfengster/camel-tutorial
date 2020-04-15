package net.gfeng.example4;

import java.util.Date;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;


public class ObjectToActiveMQ {
	
	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		ActiveMQComponent connectionFactory = new ActiveMQComponent(context);
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory.getConnectionFactory()));

		try {
			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {

					from("direct:start")
						.to("activemq:queue:my_queue");
				}
			});

			context.start();

			ProducerTemplate template = context.createProducerTemplate();
			
			Date date = new Date();
			System.out.println("sent : " + date);
			
			template.sendBody("direct:start", date);

		} catch (Exception e) {
			context.stop();
			e.printStackTrace();
		} finally {
			connectionFactory.close();
		}

	}
}
