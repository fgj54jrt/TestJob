/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.oyx.example.db;


import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.oyx.example.R;
import com.oyx.example.app.BaseApplication;
import com.oyx.example.bean.ChannelData;
import com.oyx.example.bean.LocalChannelData;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChannelDataManager {
    private static final String channelData="{'results':[" +
            "{'channelId':123,'channelName':'CITY_GUIDE'}," +
            "{'channelId':234,'channelName':'SHOP'}," +
            "{'channelId':345,'channelName':'EAT'}" +
            "]}";
    /**
     * 加载频道
     *
     * @return
     */
    public static List<ChannelData> loadNewsChannelsStatic() {
        Gson gson=new Gson();
        List<ChannelData> newsChannelTables = gson.fromJson(channelData, LocalChannelData.class).results;

        return newsChannelTables;
    }
}
