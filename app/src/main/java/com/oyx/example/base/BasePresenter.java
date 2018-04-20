package com.oyx.example.base;

import android.content.Context;

import com.oyx.example.baserx.RxManager;


/**
 * des:基类presenter
 * Created by ouyangxin
 * on 2016.07.11:55
 */
public abstract class BasePresenter<T, E> {
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
    }

    ;

    public void onDestroy() {
        mRxManage.clear();
    }
}
