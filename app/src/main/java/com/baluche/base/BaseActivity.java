package com.baluche.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baluche.R;
import com.baluche.app.MApplication;
import com.baluche.util.StatusBarUtil;

import java.util.Objects;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public abstract class BaseActivity
        extends AppCompatActivity
        implements
        IBaseView,
        View.OnClickListener {
    /**
     * 判断最后一次点击的时间
     */
    private long lastClick = 0;
    /**
     * 是否输出日志信息
     **/
    private boolean isDebug;
    private String CLASS_NAME;
    final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
//        Objects.requireNonNull(getSupportActionBar()).hide();
        //沉浸式状态栏
        setStatusBar();
        MApplication.getInstance().addActivity(this);
        isDebug = MApplication.isDebug;
        CLASS_NAME = getClass().getName();
        LogD(TAG + "-->onCreate()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogD(TAG + "--->onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogD(TAG + "--->onDestroy()");
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initView();
        initData();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();
    }

    /**
     * [初始化控件]
     */
    public abstract void initView();

    /**
     * [初始化数据]
     */
    public abstract void initData();

    /**
     * View点击事件(防止快速点击引起的Bug)
     **/
    public abstract void widgetClick(View view);

    @Override
    public void onClick(View view) {
        if (isFastClick())
            widgetClick(view);
    }

    /**
     * 设置沉浸式状态栏
     */
    public void setStatusBar() {
        StatusBarUtil.compat(this, Color.rgb(44, 177, 84));
    }

    /**
     * [页面跳转]
     *
     * @param clz clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
        //页面跳转的动画效果
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz    clz
     * @param bundle bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    /**
     * toast提示
     *
     * @param text 想要toast显示的内容
     */
    @SuppressLint("ShowToast")
    public void toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
    }


    /**
     * [日志输出]
     *
     * @param msg 想要输出的消息内容
     */
    protected void LogD(String msg) {
        if (isDebug) {
            Log.d(CLASS_NAME, msg);
        }
    }

    /**
     * [防止快速点击]
     *
     * @return 是否是快速点击
     */
    private boolean isFastClick() {
        if (System.currentTimeMillis() - lastClick <= 500) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

//    /**
//     * [隐藏软件盘]
//     */
//    public void hideSoftInput() {
//        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        if (ge  tCurrentFocus() != null) {
//            assert imm != null;
//            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        }
//    }

    /**
     * [点击软键盘之外的空白处，隐藏软件盘]
     *
     * @param ev ev
     * @return onTouchEvent
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        return false;
    }

    /**
     * [显示软键盘]
     */
    public void showInputMethod() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
        }
    }

    MaterialDialog waitDialog;//等待的dialog

    /**
     * 提示正在注册
     */
    public void showWaitDioLog() {
        waitDialog = new MaterialDialog.Builder(this)
                .title("请稍候")
                .content("正在处理,请稍后")
                .progress(true, 0)
                .show();
    }

    /**
     * 取消等待登录的提示
     */
    public void dismissWaitDioLog() {
        waitDialog.dismiss();
    }
}
