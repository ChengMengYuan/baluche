package com.baluche.util;

/**
 * 文 件 名: StatusBarUtil<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/8 15:58<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:沉浸式状态栏工具类<p>
 */
public final class StatusBarUtil {
    private static final int INVALID_VAL = -1;
    private static final int COLOR_DEFAULT = android.graphics.Color.parseColor("#20000000");

    @android.annotation.TargetApi(android.os.Build.VERSION_CODES.LOLLIPOP)
    public static void compat(android.app.Activity activity, int statusColor) {

        //当前手机版本为5.0及以上
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VAL) {
                activity.getWindow().setStatusBarColor(statusColor);
            }
            return;
        }

        //当前手机版本为4.4
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            int color = COLOR_DEFAULT;
            android.view.ViewGroup contentView = (android.view.ViewGroup) activity.findViewById(android.R.id.content);
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            android.view.View statusBarView = new android.view.View(activity);
            android.view.ViewGroup.LayoutParams lp = new android.view.ViewGroup.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, lp);
        }

    }

    public static void compat(android.app.Activity activity) {
        compat(activity, INVALID_VAL);
    }


    public static int getStatusBarHeight(android.content.Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

