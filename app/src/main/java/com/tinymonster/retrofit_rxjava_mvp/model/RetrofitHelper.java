package com.tinymonster.retrofit_rxjava_mvp.model;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 单例模式
 * Created by TinyMonster on 14/11/2018.
 */

public class RetrofitHelper {
    OkHttpClient client=new OkHttpClient();
    private static RetrofitHelper instance=null;
    private Retrofit retrofit=null;
    public static RetrofitHelper getInstance(){
        if(instance==null){
            instance=new RetrofitHelper();
        }
        return instance;
    }
    private RetrofitHelper(){
        init();
    }
    private void init(){
        retrofit=new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public BookServer getBookServer(){
        return retrofit.create(BookServer.class);
    }
}
