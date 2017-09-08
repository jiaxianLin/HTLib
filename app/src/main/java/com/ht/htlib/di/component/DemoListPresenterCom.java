package com.ht.htlib.di.component;

import com.ht.htlib.DemoListActivity;
import com.ht.htlib.di.module.model.DemoListModelModule;
import com.ht.htlib.di.module.view.DemoListViewModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/9/7 0007.
 */
@Component(modules = {DemoListModelModule.class, DemoListViewModule.class})
public interface DemoListPresenterCom {

	void inject(DemoListActivity activity);
}
