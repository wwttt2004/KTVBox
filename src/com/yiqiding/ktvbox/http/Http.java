package com.yiqiding.ktvbox.http;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;

import android.util.Log;

/**
 * 
 * @brief This class is used for Http connect, request and response
 * 
 * @author WilliamShi, [11-09-20]
 * 
 * @see
 * 
 * @remarks
 * 
 */

public class Http {

	/**
	 * Used for DDMS logging
	 */
	private static final String TAG = "Http";
	
	/**
	 * @brief Implement get method of http
	 * @param httpUrl
	 * @return
	 */
	public static HttpEntity get(String httpUrl){
		
		// Check input
		if (httpUrl==null || httpUrl.equals("") || !httpUrl.contains("http://")) {
			Log.v(TAG, "http url incorrect");
			return null;
		}
		
		// start get request
		HttpClient httpClient = HttpCore.getInstance().getHttpClient();
		HttpGet httpRequestGet = new HttpGet(httpUrl);
		try {
			HttpResponse httpResponse = httpClient.execute(httpRequestGet);
			
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				Log.v(TAG, "http get request success");
				return httpResponse.getEntity();
			}
			else if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_REQUEST_TIMEOUT)
			{
				Log.v(TAG, "http get request timeout");
				return null;
			}
			else {
				Log.v(TAG, "http get request fail");
				return null;
			}
		} catch (Exception e) {
			Log.v(TAG, "http get request occur exception");
			return null;
		}
	}
	
	/**
	 * @brief Implement post method of http
	 * @param httpUrl
	 * @param params
	 * @return
	 */
	protected HttpEntity post(String httpUrl, List<NameValuePair> params)
	{
		// Check input
		if (httpUrl==null || httpUrl.equals("") || !httpUrl.contains("http://")) {
			Log.v(TAG, "http url incorrect");
			return null;
		}
		
		// start post request
		HttpClient httpClient = HttpCore.getInstance().getHttpClient();
		HttpPost httpRequestPost = new HttpPost(httpUrl);
		
		try {
			httpRequestPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpResponse httpResponse = httpClient.execute(httpRequestPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return httpResponse.getEntity();
			}
			else if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_REQUEST_TIMEOUT)
			{
				Log.v(TAG, "http post request timeout");
				return null;
			}
			else {
				Log.v(TAG, "http post request fail");
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			Log.v(TAG, "utf_8 is unsupportedEncoding");
			return null;
		} catch (Exception e) {
			Log.v(TAG, "http post request occur exception");
			return null;
		}
		
	}
}
