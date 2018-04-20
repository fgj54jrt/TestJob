package com.oyx.example.contract;



import com.oyx.example.base.BaseModel;
import com.oyx.example.base.BasePresenter;
import com.oyx.example.base.BaseView;
import com.oyx.example.bean.NewsSummary;

import java.util.List;

import rx.Observable;

/**
 * des:新闻列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface NewsListContract {
    interface Model extends BaseModel {
        //请求获取新闻
        Observable <List<NewsSummary>> getNewsListData( final String id, int startPage);
        //请求获取新闻
        Observable <List<NewsSummary>> getNewsListDataLocal( final String id);
    }

    interface View extends BaseView {
        //返回获取的新闻
        void returnNewsListData(List<NewsSummary> newsSummaries);
        //返回顶部
        void scrolltoTop();
    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        //发起获取新闻请求
        public abstract void getNewsListDataRequest( final String id, int startPage);
        //获取本地新闻
        public abstract void getNewsListDataLocal( final String id, int startPage);
    }
}
