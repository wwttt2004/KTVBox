package com.yiqiding.ktvbox.http;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpCore {
	
	private static HttpCore instance = null;
	
	private HttpClient httpClient = null;
	
	private HttpCore()
	{
		this.httpClient = this.createHttpClient();
	}
	
	public static HttpCore getInstance()
	{
		if (instance==null) {
			instance = new HttpCore();
		}
		
		return instance;
	}
	
	private HttpClient createHttpClient() {
		int timeoutConnection = 3000;
		int timeoutSocket = 5000;

		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		HttpProtocolParams.setUseExpectContinue(params, true);

		// Set the timeout in milliseconds until a connection is established.
		HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT) // in milliseconds which
		// is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(params, timeoutSocket);

		HttpConnectionParams.setSocketBufferSize(params, 8192);
		
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory
				.getSocketFactory(), 443));
		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
				params, schemeRegistry);

		return new DefaultHttpClient(conMgr, params);
	}
	
	public HttpClient getHttpClient() {
		return httpClient;
	}
	
	/**
	 * @brief Shut down http client
	 */
	public void shutdownHttpClient() {
		if (httpClient != null && httpClient.getConnectionManager() != null) {
			httpClient.getConnectionManager().shutdown();
		}
	}
}
