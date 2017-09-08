package com.ht.htlib.contract;

import com.ht.htlib.bean.ListData;
import com.ht.htlibrary.base.BasePresenter;
import com.ht.htlibrary.base.BaseView;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class DemoListContract {

	public interface Presenter extends BasePresenter {
		void loadingData(String rp, String page);
	}

	public interface View extends BaseView<Presenter> {
		void showData(ListData data);
	}
}
