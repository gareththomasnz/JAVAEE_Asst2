package com.jcasey;

import java.net.URI;
import java.util.List;

public interface HttpParser {
	public List<URI> parseLinks(String host, String content);
}
