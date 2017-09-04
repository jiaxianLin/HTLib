package com.ht.htlib.di.component;

import com.ht.htlib.MainActivity;
import com.ht.htlib.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

	void inject(MainActivity mainActivity);
}
