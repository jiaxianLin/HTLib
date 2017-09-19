package com.ht.htlib.di.component;

import com.ht.htlib.DemoLoginActivity;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
//@Component(modules = {LoginModelModule.class, LoginViewModule.class})
public interface LoginPresenterCom {

	void inject(DemoLoginActivity loginActivity);
}
