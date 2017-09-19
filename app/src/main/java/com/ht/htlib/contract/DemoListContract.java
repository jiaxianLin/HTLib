package com.ht.htlib.contract;

import com.ht.htlib.bean.ListData;
import com.ht.htlibrary.base.BaseView;
import com.ht.htlibrary.base.IModel;
import com.ht.htlibrary.net.BaseResponse;

import rx.Observable;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class DemoListContract {

	public interface Model extends IModel {
		Observable<BaseResponse<ListData>> loadData(String rp, String page);
	}

	public interface View extends BaseView {
		void showData(ListData data);
	}
}
