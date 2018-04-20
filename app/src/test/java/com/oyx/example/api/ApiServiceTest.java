package com.oyx.example.api;

import com.oyx.example.bean.NewsSummary;
import com.oyx.util.MockRetrofitHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import rx.observers.TestSubscriber;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/4/19.
 */
public class ApiServiceTest {
    ApiService mApiService;
    MockRetrofitHelper retrofit;

    @Before
    public void setUp() throws Exception {
        retrofit = new MockRetrofitHelper();
        mApiService = retrofit.create(ApiService.class);
    }

    @Test
    public void getNewsList() throws Exception {
        retrofit.setPath("app/src/test/java/com/oyx/example/api/news");

        TestSubscriber<Map<String, List<NewsSummary>>> testSubscriber = new TestSubscriber<>();
        String id = "12321";
        mApiService.getNewsList("max-age=0", id, 0)
                .toBlocking()
                .subscribe(testSubscriber);

        Map<String, List<NewsSummary>> user = testSubscriber.getOnNextEvents()
                .get(0);
        assertEquals(user.get("results").size(),3);

    }

}