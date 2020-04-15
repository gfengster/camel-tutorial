package net.gfeng.example7;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class PollRSSFeed {

	public static void main(String[] args) throws Exception {

		try (CamelContext context = new DefaultCamelContext();) {

			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {

					from("rss:http://www.feedforall.com/sample.xml?alt=rss&splitEntries=false&consumer.delay=1000")
							.to("seda:end");
				}
			});

			ConsumerTemplate template = context.createConsumerTemplate();

			while (true) {
				context.start();

				// receiving the RSS feed
				String rssFeed = template.receiveBody("seda:end", String.class);
				System.out.println(rssFeed);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
