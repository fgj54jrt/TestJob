package com.oyx.example.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.oyx.example.R;
import com.oyx.example.adapter.NewListAdapter;
import com.oyx.example.app.AppConstant;
import com.oyx.example.base.BaseFragment;
import com.oyx.example.bean.NewsSummary;
import com.oyx.example.contract.NewsListContract;
import com.oyx.example.model.NewsListModel;
import com.oyx.example.presenter.NewsListPresenter;
import com.oyx.example.widget.LoadingTip;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * des:新闻fragment
 * Created by xsf
 * on 2016.09.17:30
 */
public class NewsFrament extends BaseFragment<NewsListPresenter, NewsListModel> implements NewsListContract.View, OnRefreshListener, OnLoadMoreListener {
    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    private NewListAdapter newListAdapter;
    private List<NewsSummary> datas = new ArrayList<>();

    private String mNewsId;
    private int mStartPage=0;

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean isVisible;

    @Override
    protected int getLayoutResource() {
        return R.layout.framents_news;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            mNewsId = getArguments().getString(AppConstant.NEWS_ID);
        }
        irc.setLayoutManager(new LinearLayoutManager(getContext()));
        datas.clear();
        newListAdapter = new NewListAdapter(getContext(), datas);
        newListAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(newListAdapter);
        irc.setOnRefreshListener(this);
        irc.setOnLoadMoreListener(this);
        //数据为空才重新发起请求
        if(newListAdapter.getSize()<=0) {
            mStartPage = 0;
            mPresenter.getNewsListDataLocal(mNewsId, mStartPage);
        }
    }


    @Override
    public void returnNewsListData(List<NewsSummary> newsSummaries) {
        if (newsSummaries != null) {
            mStartPage += 20;
            if (newListAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                newListAdapter.replaceAll(newsSummaries);
            } else {
                if (newsSummaries.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    newListAdapter.addAll(newsSummaries);
                } else {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }
    }

    /**
     * 返回顶部
     */
    @Override
    public void scrolltoTop() {
        irc.smoothScrollToPosition(0);
    }

    @Override
    public void onRefresh() {
        newListAdapter.getPageBean().setRefresh(true);
        mStartPage = 0;
        //发起请求
        irc.setRefreshing(true);
        mPresenter.getNewsListDataRequest( mNewsId, mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        newListAdapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getNewsListDataRequest( mNewsId, mStartPage);
    }

    @Override
    public void showLoading(String title) {
        if( newListAdapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
        }
    }

    @Override
    public void stopLoading() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void showErrorTip(String msg) {
        if( newListAdapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
            loadedTip.setTips(msg);
            irc.setRefreshing(false);
        }else{
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
        }
    }

}
