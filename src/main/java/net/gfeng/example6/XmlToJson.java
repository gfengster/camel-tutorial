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


public class XmlToJson {

	public static void main(String[] args) throws Exception {

		try (CamelContext context = new DefaultCamelContext();) {
			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {

					from("direct:start")
						.process(new Processor() {
							public void process(Exchange exchange) throws Exception {
								
								String xml = exchange.getIn().getBody(String.class);
								
								byte[] encoded = xml.getBytes();
								JSONObject xmlJSONObj = XML.toJSONObject(new String(encoded));
								String json = xmlJSONObj.toString(4);
								
								// set the content
								exchange.getMessage().setBody(json);
								
								System.out.println("Router: " + Thread.currentThread().getId());
							}
						})
						.to("seda:end");
				}
			});

			context.start();

			System.out.println("Main: " + Thread.currentThread().getName());
			String xml = "<employee>\r\n" + "	<id>101</id>\r\n" + "	<name>Dinesh Krishnan</name>\r\n"
					+ "	<age>20</age>\r\n" + "</employee>";
			
			System.out.println(xml);

			ProducerTemplate producerTemplate = context.createProducerTemplate();
			producerTemplate.sendBody("direct:start", xml);
			
			ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
			String json = consumerTemplate.receiveBody("seda:end", String.class);
			
			System.out.println("------------XML to JSON-------------");
			System.out.println(json);

			context.stop();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
