package com.yb.privatenote.base;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * Created by yb on 2016/12/28 .
 */

public abstract class BaseAct extends AppCompatActivity {

    public Context mContext;
    public ProgressDialog pd;
    public AlertDialog.Builder builder;
    public ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //全屏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        if (initLayoutId() != -1) {
            setContentView(initLayoutId());
        }
    }

    @Override
    public void setContentView(int resId) {
        super.setContentView(resId);
        mContext = this;
        ButterKnife.bind(this);
        if (pd == null)
            pd = new ProgressDialog(mContext);
        pd.setMessage("正在拼命加载中...");
        if (builder == null)
            builder = new AlertDialog.Builder(mContext);
        actionBar = getActionBar();
        initUi();

    }

    public abstract int initLayoutId();

    public abstract void initUi();

    /**
     * 让edittext失去焦点
     * 2016年12月15日16:54:10
     *
     * @param view
     */
    public void setEtFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    /**
     * 设置加载中  进度条
     *
     * @param flag
     */
    public void setPd(boolean flag) {
        if (pd != null) {
            if (flag)
                pd.show();
            else
                pd.cancel();
        }

    }
    public void setPd(boolean flag, String msg) {
        if (flag) {
            pd.setMessage(msg);
            pd.show();
        } else
            pd.cancel();
        pd.setCanceledOnTouchOutside(false);
    }

    public void setPd(boolean flag, String msg, boolean dismis) {
        if (flag) {
            pd.setMessage(msg);
            pd.show();
        } else
            pd.cancel();
        pd.setCanceledOnTouchOutside(dismis);
    }
    /**
     * 不带参数的跳转Activity
     * 2016年12月15日16:04:39
     */
    public void startAct(Class clazz) {
        startActivity(new Intent(mContext, clazz));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        pd.dismiss();
        pd = null;
        builder = null;
    }
}
