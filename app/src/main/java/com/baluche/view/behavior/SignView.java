package com.baluche.view.behavior;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class SignView extends View {


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 在控件大小发生改变时调用。初始化会被调用一次
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 计算每个小球的位置
     *
     * @param viewData
     */
    private void calculateCirclePoints(List<String> viewData) {

    }

    /**
     * 属性动画的形式绘制进度条,暂时未启用,效果预览可以的
     *
     * @param canvas
     */
    private void drawSignInPbRectWithAnim(final Canvas canvas) {

    }

    /**
     * 绘制签到进度的方法,如果想看动画的可以自己把onDraw中的方法替换成上面的即可
     */
    private void drawSignInPbRectNoAnim() {

    }

    /**
     * 绘制选中的签到状态下的圆圈以及白色的对勾路径
     */
    private void drawSingInCheckCircle(Canvas canvas) {
        
    }
}
