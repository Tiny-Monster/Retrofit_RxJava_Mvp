package com.tinymonster.retrofit_rxjava_mvp.presenter;

import android.util.Log;

import com.tinymonster.retrofit_rxjava_mvp.entity.Book;
import com.tinymonster.retrofit_rxjava_mvp.model.Model;
import com.tinymonster.retrofit_rxjava_mvp.view.BookView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * book presenter
 * Created by TinyMonster on 14/11/2018.
 */

public class BookPresenter extends IPresenter<BookView>{
    private BookView bookView;
    private CompositeDisposable compositeDisposable;
    public BookPresenter(){
        compositeDisposable=new CompositeDisposable();
    }
    @Override
    public void attachView(BookView View) {
        this.bookView=View;
    }

    @Override
    public void detachView() {
        compositeDisposable.clear();
        this.bookView=null;
    }
    public void getData(String name,String tag,int start,int count){
        bookView.showLoading();
        Observable observable=Model.getInstance().getBook(name,tag,start,count);
        observable.subscribeOn(Schedulers.io())
                .delay(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>(){
                    Book book=null;
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("BookPresenter","onSubscribe");
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Book o) {
                        Log.e("BookPresenter","onNext");
                        book=o;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("BookPresenter","onError");
                        e.printStackTrace();
                        bookView.onError(e.toString());
                        bookView.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        Log.e("BookPresenter","onComplete");
                        bookView.onSuccess(book);
                        bookView.hideLoading();
                    }
                });
    }
    public void ClearSubscribe(){
        compositeDisposable.clear();
    }
}
