package com.oyx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MockRetrofitHelper {
    public <T> T create(Class<T> clazz) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new MockInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://120.25.200.167/")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    private class MockInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            // 模拟网络数据
            MockAssest assest = new MockAssest();
            String content = assest.readFile(path);

            ResponseBody body = ResponseBody.create(MediaType.parse("application/x-www-form-urlencoded"), content);

            Response response = new Response.Builder().request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .body(body)
                    .build();
            return response;
        }
    }

    class MockAssest {

        private String BASE_PATH = "app/src/test/java/cn/com/xxx/xxx/base/mocks/data";

        //User API对应的模拟json数据的文件路径
        String USER_DATA = BASE_PATH + "/userJson_test";

        //通过文件路径，读取Json数据
        public String readFile(String path) {
            String content = null;
            try {
                content = file2String(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        //kotlin丰富的I/O API,我们可以通过file.readText（charset）直接获取结果
        String file2String(String fileName) throws IOException {
            File file = new File(fileName);

            BufferedReader bf = new BufferedReader(new FileReader(file));

            String content = "";
            StringBuilder sb = new StringBuilder();

            while (content != null) {
                content = bf.readLine();

                if (content == null) {
                    break;
                }

                sb.append(content.trim());
            }

            bf.close();
            return sb.toString();
        }
    }
}
