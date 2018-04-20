package com.oyx.example.db;

import com.oyx.example.bean.ChannelData;
import com.oyx.example.bean.NewsSummary;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/4/19.
 */
public class NewsListlDataManagerTest {
    @Test
    public void loadNewsLiistStatic() throws Exception {
        NewsListlDataManager manager=new NewsListlDataManager();

        List<NewsSummary> listdata =NewsListlDataManager.loadNewsLiistStatic();
        assertNotNull(listdata);
    }

}