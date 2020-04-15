package net.gfeng.example9;


public class MyRestService {
	private String previous = null;
	
	public void doSomething(String response) {
		if (!response.equals(previous)) {
			System.out.println(response);
			previous= response;
		} else {
			System.out.println("No news");
		}
	}
}
