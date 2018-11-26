package com.tinymonster.retrofit_rxjava_mvp.view;

import com.tinymonster.retrofit_rxjava_mvp.entity.Book;

/**
 * BookView
 * Created by TinyMonster on 14/11/2018.
 */

public interface BookView extends IView{
    void onSuccess(Book book);
    void onFail(String msg);
    void onError(String msg);
}
