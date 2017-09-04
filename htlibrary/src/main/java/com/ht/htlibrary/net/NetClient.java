package com.ht.htlibrary.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class NetClient {

	Retrofit mRetrofit;

	String successCode = "0";

	private static NetClient mNetClient = new NetClient();

	protected static OkHttpClient okHttpClient = new OkHttpClient();

	public static NetClient init(){
		return mNetClient;
	}

	public NetClient setSuccessCode(String successCode){
		return this;
	}

	public String getSuccessCode() {
		return successCode;
	}

	protected static final Interceptor sLoggingInterceptor = new Interceptor() {
		@Override
		public Response intercept(Chain chain) throws IOException {
			final Request request = chain.request();
			Buffer requestBuffer = new Buffer();

			if(request.body() != null){
				request.body().writeTo(requestBuffer);
			} else {

			}

			System.out.println(request.url() + "\n" + request.body());

			HttpUrl.Builder builder = request.url()
					.newBuilder()
					.scheme(request.url().host())
					.host(request.url().host());

			Request newRequest = request.newBuilder()
					.method(request.method(), request.body())
					.url(builder.build())
					.build();

			Response response = chain.proceed(newRequest);

			return response;
		}
	};


}
