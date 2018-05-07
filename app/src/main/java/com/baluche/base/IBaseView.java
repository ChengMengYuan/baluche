package com.baluche.base;

import android.os.Bundle;

/**
 * 文 件 名: IBaseView<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/3 10:24<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:V层的接口,所有V层都应该实现这个接口<p>
 */
public interface IBaseView {
    String TAG = null;

    /**
     * [页面跳转]
     *
     * @param clz clz
     */
    void startActivity(Class<?> clz);

    /**
     * [携带数据的页面跳转]
     *
     * @param clz    clz
     * @param bundle bundle
     */
    void startActivity(Class<?> clz, Bundle bundle);

    /**
     * toast提示
     *
     * @param text text
     */
    void toast(String text);

}
