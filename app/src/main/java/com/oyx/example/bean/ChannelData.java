package com.oyx.example.bean;


import java.io.Serializable;
/*
 * 频道数据
 */
public class ChannelData implements Serializable {

    private int channelId;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    private String channelName;

    public ChannelData() {
    }

    public ChannelData(String channelName) {
        this.channelName = channelName;
    }

    public ChannelData(int channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }

}
