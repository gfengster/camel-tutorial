package net.gfeng.example8;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class PollGithub {

	public static void main(String[] args) throws Exception {		

		try (CamelContext context = new DefaultCamelContext();){

			// adding the routes to the camel
			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {

					from("github:commit?userName=<your_use_name>&password=<your_password>&repoName=<repo_name>&branchName=<branch_name>&repoOwner=<repo_owner_user_name>")
							.to("class:com.dineshkrish.GithubService?method=doSomething"); 

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
