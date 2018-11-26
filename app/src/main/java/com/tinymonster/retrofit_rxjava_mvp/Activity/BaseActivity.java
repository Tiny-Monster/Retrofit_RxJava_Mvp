package com.tinymonster.retrofit_rxjava_mvp.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tinymonster.retrofit_rxjava_mvp.R;
import com.tinymonster.retrofit_rxjava_mvp.view.IView;

/**
 * Activity基类 封装了ProgressDialog的显示了隐藏，提供了设置content布局的接口
 * Created by TinyMonster on 14/11/2018.
 */

public abstract class BaseActivity extends Activity implements IView{
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading...");
    }

    @Override
    public void showLoading() {
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    public abstract int getLayoutId();
}
