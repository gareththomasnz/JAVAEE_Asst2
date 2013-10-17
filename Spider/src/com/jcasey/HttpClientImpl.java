package com.jcasey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpRequest;

import sun.misc.IOUtils;

public class HttpClientImpl implements HttpClient
{

	
	@Override
	public boolean get(URI uri, StringBuilder response)
	{
		return false;
	}

}
