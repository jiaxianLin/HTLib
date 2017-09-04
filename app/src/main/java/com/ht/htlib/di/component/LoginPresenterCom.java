package com.ht.htlib.di.component;

import com.ht.htlib.DemoLoginActivity;
import com.ht.htlib.di.module.model.LoginModelModule;
import com.ht.htlib.di.module.view.LoginViewModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
@Component(modules = {LoginModelModule.class, LoginViewModule.class})
public interface LoginPresenterCom {

	void inject(DemoLoginActivity loginActivity);
}
