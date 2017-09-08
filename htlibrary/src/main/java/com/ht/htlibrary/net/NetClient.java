package com.ht.htlibrary.net;

import android.accounts.NetworkErrorException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class NetClient {

	protected static Retrofit mRetrofit;

	public static final String SUCCESS_CODE = "0";

	private static final int DEFAULT_TIME = 10;    //默认超时时间

	private static NetClient mNetClient = new NetClient();

	public static void init(String baseurl){
		//创建okHttpClient
		if (null == mRetrofit) {
			OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
			builder.readTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
			builder.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);

			//设置拦截器
//			builder.addInterceptor(new BasicParamsInterceptor.Builder().addParamsMap(getCommonMap()).build());
//			builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
			OkHttpClient okHttpClient = builder.build();
			mRetrofit = new Retrofit.Builder()
					.baseUrl(baseurl)
					.client(okHttpClient)
					.addConverterFactory(MyGsonConverterFactory.create())
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.build();
		}
	}


//	/**
//	 * 考虑到多个baseurl的情况
//	 * @param BaseUrl
//	 */
//	public NetClient (String BaseUrl){
//		//创建okHttpClient
//		if (null == mRetrofit) {
//			OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//			builder.readTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
//			builder.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
//
//			//设置拦截器
////			builder.addInterceptor(new BasicParamsInterceptor.Builder().addParamsMap(getCommonMap()).build());
////			builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
//			OkHttpClient okHttpClient = builder.build();
//			mRetrofit = new Retrofit.Builder()
//					.baseUrl(BaseUrl)
//					.client(okHttpClient)
//					.addConverterFactory(GsonConverterFactory.create())
//					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//					.build();
//		}
//	}

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

	protected <T> void toSubscribe(Observable<T> observable, Observer<T> observer) {
		observable.subscribeOn(Schedulers.io())    // 指定subscribe()发生在IO线程
				.observeOn(AndroidSchedulers.mainThread())  // 指定Subscriber的回调发生在main线程
				.timeout(DEFAULT_TIME, TimeUnit.SECONDS)    //重连间隔时间
				.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
					private int mRetryCount;
					@Override
					public Observable<?> call(Observable<? extends Throwable> observable) {

						return observable.flatMap(new Func1<Throwable, Observable<?>>() {
							@Override
							public Observable<?> call(Throwable throwable) {
								if ((throwable instanceof NetworkErrorException
										|| throwable instanceof ConnectException
										|| throwable instanceof SocketTimeoutException
										|| throwable instanceof TimeoutException) && mRetryCount < 3) {
									mRetryCount++;
									return Observable.timer(2000, TimeUnit.MILLISECONDS);
								}
								return Observable.error(throwable);
							}
						});
					}
				})
				.subscribe(observer);   //订阅
	}




}
