package com.advertising.administrator.customkeyboard.Test1;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.advertising.administrator.customkeyboard.R;

/**
 * 键盘入门代码
 */

public class Test1Activity extends AppCompatActivity implements KeyboardView.OnKeyboardActionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        //找到键盘控件
        KeyboardView keyboardView = findViewById(R.id.keyboardView);
        //创建keyboard,设置我们写的布局文件
        Keyboard keyboard = new Keyboard(this, R.xml.keyboard_number_abc);
        //把创建的键盘布局设置给控件
        keyboardView.setKeyboard(keyboard);
        //给键盘设置监听
        keyboardView.setOnKeyboardActionListener(this);
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int i, int[] ints) {

    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
