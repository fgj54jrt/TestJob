package com.oyx.example.model;


import com.oyx.example.baserx.RxSchedulers;
import com.oyx.example.bean.ChannelData;
import com.oyx.example.contract.MainContract;
import com.oyx.example.db.ChannelDataManager;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * des:
 * Created by xsf
 * on 2016.09.17:05
 */
public class MainModel implements MainContract.Model {
    @Override
    public Observable<List<ChannelData>> lodeMineChannels() {

        return Observable.create(new Observable.OnSubscribe<List<ChannelData>>() {
            @Override
            public void call(Subscriber<? super List<ChannelData>> subscriber) {
                ArrayList<ChannelData> ChannelDataList = (ArrayList<ChannelData>) ChannelDataManager.loadNewsChannelsStatic();
                subscriber.onNext(ChannelDataList);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<List<ChannelData>>io_main());
    }


}
