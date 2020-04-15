package net.gfeng.example2;

import org.apache.camel.builder.RouteBuilder;


public class FileCopyRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("file:input_dir?noop=true")
		.to("file:output_dir");
		
		System.out.println("File is moved");
	}

}
