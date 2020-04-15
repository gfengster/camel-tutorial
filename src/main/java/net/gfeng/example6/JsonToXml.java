package net.gfeng.example6;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.json.JSONObject;
import org.json.XML;


public class JsonToXml {

	public static void main(String[] args) throws Exception {

		
		try (CamelContext context = new DefaultCamelContext();){
			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {

					from("direct:start").process(new Processor() {
						public void process(Exchange exchange) throws Exception {

							String json = exchange.getIn().getBody(String.class);

							JSONObject jsonObject = new JSONObject(json);
							String xml = XML.toString(jsonObject);

							exchange.getMessage().setBody(xml);
						}
					}).to("seda:end");
				}
			});

			context.start();

			String json = "{\"employee\": {\r\n" + "    \"name\": \"Dinesh Krishnan\",\r\n" + "    \"id\": 101,\r\n"
					+ "    \"age\": 20\r\n" + "}}";

			System.out.println(json);

			ProducerTemplate producerTemplate = context.createProducerTemplate();
			producerTemplate.sendBody("direct:start", json);

			ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
			String xml = consumerTemplate.receiveBody("seda:end", String.class);

			System.out.println("-----------------JSON to XML---------------------");
			System.out.println(xml);

			context.stop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
