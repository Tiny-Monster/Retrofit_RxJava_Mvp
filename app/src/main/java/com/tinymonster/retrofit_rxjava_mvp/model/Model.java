package com.tinymonster.retrofit_rxjava_mvp.model;

import com.tinymonster.retrofit_rxjava_mvp.entity.Book;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;


/**
 * 用户获取数据
 * Created by TinyMonster on 14/11/2018.
 */

public class Model {
    private RetrofitHelper retrofitHelper=RetrofitHelper.getInstance();
    private BookServer bookServer;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    private static Model instance=null;
    public static Model getInstance(){
        if(instance==null){
            instance=new Model();
        }
        return instance;
    }
    private Model(){

    }
    public Observable getBook(String name, String tag, int start, int count){
        if(bookServer==null){
            bookServer=retrofitHelper.getBookServer();
        }
        Observable<Book> observable=bookServer.getBook(name,tag,start,count);
        return observable;
    }
}
