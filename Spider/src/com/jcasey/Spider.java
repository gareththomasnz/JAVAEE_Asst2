package com.jcasey;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Spider {
	
	private HttpClient client;
	private HttpParser parser;
	
	private Set<URI> alreadyVisited = new HashSet<URI>();
	
	public Spider(HttpClient client, HttpParser parser)
	{
		this.client = client;
		this.parser = parser;
	}
	
	public void startCrawl()
	{
		try {
			crawl(new URI("http://www.unitec.ac.nz/"),20);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public void crawl(URI uri, int depth)
	{		

	}
}
