package com.oyx.example.presenter;

import com.oyx.example.app.AppConstant;
import com.oyx.example.baserx.RxSubscriber;
import com.oyx.example.bean.ChannelData;
import com.oyx.example.contract.MainContract;

import java.util.List;

import rx.functions.Action1;

/**
 * des:
 * Created by xsf
 * on 2016.09.17:43
 */
public class MainPresenter extends MainContract.Presenter{

    @Override
    public void onStart() {
        super.onStart();
        //监听频道变化刷新
        mRxManage.on(AppConstant.CHANNEL_CHANGED, new Action1<List<ChannelData>>() {

            @Override
            public void call(List<ChannelData> mChannelData) {
                if(mChannelData!=null){
                    mView.returnMineChannels(mChannelData);
                }
            }
        });
    }

    @Override
    public void lodeMineChannelsRequest() {
        mRxManage.add(mModel.lodeMineChannels().subscribe(new RxSubscriber<List<ChannelData>>(mContext,false) {
            @Override
            protected void _onNext(List<ChannelData> mChannelData) {
                mView.returnMineChannels(mChannelData);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }
}
