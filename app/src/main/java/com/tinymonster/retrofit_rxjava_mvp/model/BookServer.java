package com.tinymonster.retrofit_rxjava_mvp.model;

import com.tinymonster.retrofit_rxjava_mvp.entity.Book;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 获得book对应的observable
 * Created by TinyMonster on 14/11/2018.
 */

public interface BookServer {
    @GET("book/search")
    Observable<Book> getBook(@Query("q") String name, @Query("tag") String tag, @Query("start") int start, @Query("count") int count);
}
