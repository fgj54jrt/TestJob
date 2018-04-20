package com.oyx.example.model;


import com.oyx.example.api.Api;
import com.oyx.example.baserx.RxHelper;
import com.oyx.example.baserx.RxSchedulers;
import com.oyx.example.bean.ChannelData;
import com.oyx.example.bean.NewsSummary;
import com.oyx.example.contract.NewsListContract;
import com.oyx.example.db.ChannelDataManager;
import com.oyx.example.db.NewsListlDataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * des:新闻列表model
 * Created by xsf
 * on 2016.09.14:54
 */
public class NewsListModel implements NewsListContract.Model {
    /**
     * 获取新闻列
     *
     * @param id
     * @param startPage
     * @return
     */
    @Override
    public Observable<List<NewsSummary>> getNewsListData(final String id, final int startPage) {
        return Api.getDefault().getNewsList(Api.getCacheControl(), id, startPage)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>() {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> map) {

                        return Observable.from(map.get("results"));
                    }
                })
                .map(new Func1<NewsSummary, NewsSummary>() {
                    @Override
                    public NewsSummary call(NewsSummary newsSummary) {
                        return newsSummary;
                    }
                })
                .distinct()//去重
                .toSortedList(new Func2<NewsSummary, NewsSummary, Integer>() {
                    @Override
                    public Integer call(NewsSummary newsSummary, NewsSummary newsSummary2) {
                        return newsSummary.getObjectId() - newsSummary2.getObjectId();
                    }
                })
                //声明线程调度
                .compose(RxSchedulers.<List<NewsSummary>>io_main());
    }

    @Override
    public Observable<List<NewsSummary>> getNewsListDataLocal(String id) {
        return Observable.create(new Observable.OnSubscribe<List<NewsSummary>>() {
            @Override
            public void call(Subscriber<? super List<NewsSummary>> subscriber) {
                ArrayList<NewsSummary> ChannelDataList = (ArrayList<NewsSummary>) NewsListlDataManager.loadNewsLiistStatic();

                subscriber.onNext(ChannelDataList);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<List<NewsSummary>>io_main());
    }
}
