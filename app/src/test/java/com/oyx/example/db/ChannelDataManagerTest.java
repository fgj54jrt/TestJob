package com.oyx.example.db;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.ServiceTestCase;
import android.test.mock.MockContext;

import com.oyx.example.bean.ChannelData;

import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/4/19.
 */
public class ChannelDataManagerTest {
    @Test
    public void loadNewsChannelsStatic() throws Exception {

        ChannelDataManager manager=new ChannelDataManager();

        List<ChannelData> channelList =manager.loadNewsChannelsStatic();
        assertNotNull(channelList);
    }

}