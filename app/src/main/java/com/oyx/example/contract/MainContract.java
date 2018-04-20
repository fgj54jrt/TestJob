package com.oyx.example.contract;


import com.oyx.example.base.BaseModel;
import com.oyx.example.base.BasePresenter;
import com.oyx.example.base.BaseView;
import com.oyx.example.bean.ChannelData;

import java.util.List;

import rx.Observable;

/**
 * des:
 * Created by xsf
 * on 2016.09.11:53
 */
public interface MainContract {

    interface Model extends BaseModel {
        Observable<List<ChannelData>> lodeMineChannels();
    }

    interface View extends BaseView {
        void returnMineChannels(List<ChannelData> newsChannelsMine);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void lodeMineChannelsRequest();
    }
}
