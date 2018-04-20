package com.oyx.example.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.oyx.example.R;
import com.oyx.example.app.AppConstant;
import com.oyx.example.base.BaseActivity;
import com.oyx.example.base.BaseFragmentAdapter;
import com.oyx.example.bean.ChannelData;
import com.oyx.example.contract.MainContract;
import com.oyx.example.model.MainModel;
import com.oyx.example.presenter.MainPresenter;
import com.oyx.example.ui.fragment.NewsFrament;
import com.oyx.example.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View {
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private BaseFragmentAdapter fragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        mPresenter.lodeMineChannelsRequest();
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void returnMineChannels(List<ChannelData> newsChannelsMine) {
        if(newsChannelsMine!=null) {
            List<String> channelNames = new ArrayList<>();
            List<Fragment> mNewsFragmentList = new ArrayList<>();
            for (int i = 0; i < newsChannelsMine.size(); i++) {
                channelNames.add(newsChannelsMine.get(i).getChannelName());
                mNewsFragmentList.add(createListFragments(newsChannelsMine.get(i)));
            }
            if(fragmentAdapter==null) {
                fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mNewsFragmentList, channelNames);
            }else{
                //刷新fragment
                fragmentAdapter.setFragments(getSupportFragmentManager(),mNewsFragmentList,channelNames);
            }
            viewPager.setAdapter(fragmentAdapter);
            tabs.setupWithViewPager(viewPager);
            MyUtils.dynamicSetTabLayoutMode(tabs);
            setPageChangeListener();
        }
    }
    private void setPageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private NewsFrament createListFragments(ChannelData newsChannel) {
        NewsFrament fragment = new NewsFrament();
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstant.NEWS_ID, newsChannel.getChannelId());

        fragment.setArguments(bundle);
        return fragment;
    }
}
