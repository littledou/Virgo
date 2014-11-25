package com.cafe.virgo.util;

import java.net.HttpURLConnection;
import java.net.URL;

import com.cafe.virgo.BaseApplication;
import com.cafe.virgo.config.VirgoConstant;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkUrlFactory;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * @author 侯银博 
 * cllanjim@gmail.com
 */
public class Httputils {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	private static int DEFAULT_HTTP_CONNECT_TIMEOUT = 5000;
	private static int DEFAULT_HTTP_READ_TIMEOUT = 5000;

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String getHttp(String url){
		if(VirgoConstant.IS_ONLINE){
			try {
				Request request = new Request.Builder().url(url).get().build();
				Response response = BaseApplication.getOkHttpClient().newCall(request).execute();
				return response.body().string();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String getCahceHttp(String url){
		try {
			Request request = new Request.Builder().url(url).get().build();
			Response response = BaseApplication.getOkHttpClient().newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String urlHttp(String url){
		if(VirgoConstant.IS_ONLINE){
			try {
				Request request = new Request.Builder().url(url).build();
				Response response = BaseApplication.getOkHttpClient().newCall(request).execute();
				return response.body().string();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static HttpURLConnection openHttpConnection(String url){
		if(VirgoConstant.IS_ONLINE){
			try {
				OkUrlFactory okUrlFactory = new OkUrlFactory(BaseApplication.getOkHttpClient());
				HttpURLConnection httpURLConnection = okUrlFactory.open(new URL(url));
				httpURLConnection.setConnectTimeout(DEFAULT_HTTP_CONNECT_TIMEOUT);
				httpURLConnection.setReadTimeout(DEFAULT_HTTP_READ_TIMEOUT);
				return httpURLConnection;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static HttpURLConnection openHttpConnection(URL url){
		if(VirgoConstant.IS_ONLINE){
			try {
				OkUrlFactory okUrlFactory = new OkUrlFactory(BaseApplication.getOkHttpClient());
				HttpURLConnection httpURLConnection = okUrlFactory.open(url);
				httpURLConnection.setConnectTimeout(DEFAULT_HTTP_CONNECT_TIMEOUT);
				httpURLConnection.setReadTimeout(DEFAULT_HTTP_READ_TIMEOUT);
				return httpURLConnection;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String post(String url){
		if(VirgoConstant.IS_ONLINE){
			try {
				Request request = new Request.Builder().url(url).post(null).build();
				Response response = BaseApplication.getOkHttpClient().newCall(request).execute();
				return response.body().string();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String postHttp(String url, String json) {
		if(VirgoConstant.IS_ONLINE){
			try {
				RequestBody body = RequestBody.create(JSON, json);
				Request request = new Request.Builder().url(url).post(body).build();
				Response response = BaseApplication.getOkHttpClient().newCall(request).execute();
				return response.body().string();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String put(String url, String json) {
		if(VirgoConstant.IS_ONLINE){
			try {
				RequestBody body = RequestBody.create(JSON, json);
				Request request = new Request.Builder().url(url).put(body).build();
				Response response = BaseApplication.getOkHttpClient().newCall(request).execute();
				return response.body().string();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String put(String url) {
		if(VirgoConstant.IS_ONLINE){
			try {
				Request request = new Request.Builder().url(url).put(null).build();
				Response response = BaseApplication.getOkHttpClient().newCall(request).execute();
				return response.body().string();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String deleteHttp(String url) {
		if(VirgoConstant.IS_ONLINE){
			try {
				Request request = new Request.Builder().url(url).delete().build();
				Response response = BaseApplication.getOkHttpClient().newCall(request).execute();
				return response.body().string();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}