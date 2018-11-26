package com.tinymonster.retrofit_rxjava_mvp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tinymonster.retrofit_rxjava_mvp.Activity.BaseActivity;
import com.tinymonster.retrofit_rxjava_mvp.R;
import com.tinymonster.retrofit_rxjava_mvp.entity.Book;
import com.tinymonster.retrofit_rxjava_mvp.presenter.BookPresenter;
import com.tinymonster.retrofit_rxjava_mvp.view.BookView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 主页面 实现点击按钮请求数据，请求成功显示数据。
 */
public class MainActivity extends BaseActivity implements BookView{
    BookPresenter bookPresenter=null;
    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookPresenter=new BookPresenter();
        bookPresenter.attachView(this);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookPresenter.getData("金瓶梅",null,0,1);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Book book) {
        textView.setText(book.getBooks().get(0).getTitle());
    }

    @Override
    public void onFail(String msg) {
        textView.setText(msg);
    }

    @Override
    public void onError(String msg) {
        textView.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookPresenter.detachView();
        bookPresenter.ClearSubscribe();
    }
}
