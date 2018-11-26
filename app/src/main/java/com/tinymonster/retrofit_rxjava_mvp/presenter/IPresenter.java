package com.tinymonster.retrofit_rxjava_mvp.presenter;

import com.tinymonster.retrofit_rxjava_mvp.view.IView;

/**
 * presenter基类
 * Created by TinyMonster on 14/11/2018.
 */

public abstract class IPresenter<T extends IView> {
    abstract void attachView(T View);
    abstract void detachView();
}
