package net.gfeng.example51;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class MyProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		// get message from body
		String message = exchange.getIn().getBody(String.class);

		// apply your changes
		message = "Hello " + message;

		// set new message to body
		exchange.getIn().setBody(message);
	}

}
