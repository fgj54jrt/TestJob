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
package com.oyx.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * des:新闻消息实体类
 * Created by xsf
 * on 2016.06.13:05
 */
public class NewsSummary implements Parcelable {
    private int objectId;//
    private String title;//
    private String subTitle;//
    private String imgUrl;//图片地址
    private int type;//类型  1：图文 2：图片

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static Creator<NewsSummary> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.objectId);

        dest.writeString(this.title);
        dest.writeString(this.subTitle);
        dest.writeString(this.imgUrl);
        dest.writeInt(this.type);


    }

    public NewsSummary() {
    }

    protected NewsSummary(Parcel in) {
        this.objectId = in.readInt();
        this.title = in.readString();
        this.subTitle = in.readString();
        this.imgUrl = in.readString();
        this.type = in.readInt();
    }

    public static final Parcelable.Creator<NewsSummary> CREATOR = new Parcelable.Creator<NewsSummary>() {
        @Override
        public NewsSummary createFromParcel(Parcel source) {
            return new NewsSummary(source);
        }

        @Override
        public NewsSummary[] newArray(int size) {
            return new NewsSummary[size];
        }
    };
}
