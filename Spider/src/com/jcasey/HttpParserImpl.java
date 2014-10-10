package com.jcasey;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class HttpParserImpl implements HttpParser{
	public List<URI> parseLinks(final String content, final String host)
	{
		final List<URI> urls = new ArrayList <URI> ();
		
		//TODO parse HTML content using JSoup or Jerry HTML Parsers
		
		return Collections.unmodifiableList(urls);
	}
}
