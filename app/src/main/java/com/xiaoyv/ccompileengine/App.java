package com.xiaoyv.ccompileengine;

import android.app.Application;

import com.xiaoyv.ccompile.CCppEngine;

/**
 * @author 王怀玉
 * @since 2020/5/14
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CCppEngine.install(this);

    }
}
