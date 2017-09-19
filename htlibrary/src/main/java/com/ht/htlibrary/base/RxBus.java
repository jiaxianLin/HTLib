package com.ht.htlibrary.base;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class RxBus {

	private static RxBus instance = new RxBus();

	private Subject<Object, Object> mRxBusObserverable = new SerializedSubject<>(PublishSubject.create());

	public static void send(Object object) {
		if (instance.mRxBusObserverable.hasObservers()) {
			instance.mRxBusObserverable.onNext(object);
		}
	}

	public static Observable<Object> toObserverable() {
		return instance.mRxBusObserverable;
	}



}
