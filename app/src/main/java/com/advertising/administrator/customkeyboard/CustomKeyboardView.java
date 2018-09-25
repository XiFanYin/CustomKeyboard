package com.advertising.administrator.customkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;

import com.advertising.administrator.customkeyboard.R;

import java.util.List;

/**
 * 从写这个类，是为了改变部分按钮的背景颜色
 */
public class CustomKeyboardView extends KeyboardView {

    private Paint mtextPaint;

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mtextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mtextPaint.setTextAlign(Paint.Align.CENTER);
        mtextPaint.setTextSize(46);
        mtextPaint.setColor(Color.WHITE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Keyboard.Key> keys = getKeyboard().getKeys();

        for (Keyboard.Key key : keys) {
            if (key.codes[0] == -4) {
                //获取drawable资源
                Drawable drawable = getContext().getResources().getDrawable(R.drawable.srue_bg_key);
                //把真正的状态设置给上层绘制的key键
                drawable.setState(key.getCurrentDrawableState());
                //rawable将在被绘制在canvas的哪个矩形区域内（这个矩形区域的坐标是以canvas左上角为坐标原点的）
                drawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                //开始绘制
                drawable.draw(canvas);
                //绘制文字
                if (key.label != null) {
                    Paint.FontMetrics fontMetrics = mtextPaint.getFontMetrics();
                    //计算基线
                    Float baseLine = key.y + key.height / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                    //绘制文字
                    canvas.drawText(key.label.toString(), key.x + (key.width / 2), baseLine, mtextPaint);
                }

            }
        }


    }
}
