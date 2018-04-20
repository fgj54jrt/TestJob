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


import com.google.gson.Gson;
import com.oyx.example.R;
import com.oyx.example.app.BaseApplication;
import com.oyx.example.bean.ChannelData;
import com.oyx.example.bean.LocalNewsData;
import com.oyx.example.bean.NewsSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsListlDataManager {
    private static final String newsList = "{\"results\":[{\"objectId\":1,\"title\":\"蔡英文赠友邦5头牛\",\"subTitle\":\"解放军18日在台湾海峡举行军事演习，就在岛内一些人渲染“第四次台海危机”可能来临之际，台湾地区领导人蔡英文却跑到万里之外\",\"imgUrl\":\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1522090028,2055551737&fm=173&app=25&f=JPEG?w=218&h=146&s=810047B05A9908CA781C79370300B0E0\",\"type\":1}," +
            "{\"objectId\":12,\"title\":\"\",\"subTitle\":\"\",\"imgUrl\":\"http://a.hiphotos.baidu.com/image/pic/item/58ee3d6d55fbb2fb2394a775434a20a44623dc1e.jpg\",\"type\":2}," +
            "{\"objectId\":13,\"title\":\"戛纳电影节张震\",\"subTitle\":\"第71届戛纳电影节18日公布竞赛片评审团名单，台湾演员张震被列入其中，成为本届评审团中唯一一位华人评审\",\"imgUrl\":\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1995264620,2301901180&fm=173&app=25&f=JPEG?w=218&h=146&s=F7A43B675CF3A29052005D670300E060\",\"type\":1}]}";

    /**
     * 加载频道
     *
     * @return
     */
    public static List<NewsSummary> loadNewsLiistStatic() {
        Gson gson = new Gson();
        LocalNewsData listData = gson.fromJson(newsList, LocalNewsData.class);

        return listData.results;
    }
}
