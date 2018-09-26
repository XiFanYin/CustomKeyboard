package com.advertising.administrator.customkeyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KeyboardViewManager implements KeyboardView.OnKeyboardActionListener {
    //键盘的根布局
    private final FrameLayout frameLayout;
    //英文键盘和数字键盘标记
    public static Integer NUMBERXML = R.xml.keyboard_number_abc;
    public static Integer ENGLISHXML = R.xml.keyboard_english;

    private Map<EditText, onSureClickListener> editList;
    private EditText currentEditText;
    private EditText focusReplace;
    private final Context context;
    private final Keyboard keyboardEnglish;
    private final Keyboard keyboardNumber;
    //标识英文键盘大小写切换
    private boolean isCapital = false;
    private final KeyboardView keyboardView;
    //标识数字键盘和英文键盘的切换
    private boolean isShift;
    private boolean closeKeyboard;

    private KeyboardViewManager(Context context, Map<EditText, onSureClickListener> editText, boolean closeKeyboard) {
        this.context = context;
        this.editList = editText;
        this.closeKeyboard = closeKeyboard;
        //创建打气筒
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //把键盘布局解析成对象
        frameLayout = (FrameLayout) layoutInflater.inflate(R.layout.content_keyboardview, null);
        keyboardView = frameLayout.findViewById(R.id.keyboardView);
        //创建keyboard
        keyboardNumber = new Keyboard(context, NUMBERXML);
        keyboardEnglish = new Keyboard(context, ENGLISHXML);
        //把创建的键盘布局设置给控件
        keyboardView.setKeyboard(keyboardNumber);
        //给键盘设置监听
        keyboardView.setOnKeyboardActionListener(this);
        for (EditText key : editText.keySet()) {
            key.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        currentEditText = (EditText) view;
                        showSoftKeyboard();
                    }
                }
            });
        }
    }

    //显示键盘
    private void showSoftKeyboard() {
        //根据设置的输入类型，动态切换键盘
        int inputType = currentEditText.getInputType();
        if (inputType == 2) {
            keyboardView.setKeyboard(keyboardNumber);
            isShift = true;
        } else {
            keyboardView.setKeyboard(keyboardEnglish);
            isShift = false;
        }

        if (closeKeyboard || frameLayout.getVisibility() == View.VISIBLE) {
            frameLayout.setVisibility(View.VISIBLE);
        } else {
            Animation show = AnimationUtils.loadAnimation(context, R.anim.down_to_up);
            frameLayout.startAnimation(show);
            show.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    frameLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }


    }

    private void hideSoftKeyboard() {
        if (closeKeyboard) {
            frameLayout.setVisibility(View.GONE);
        } else {
            //设置隐藏动画
            Animation hide = AnimationUtils.loadAnimation(context, R.anim.up_to_hide);
            frameLayout.startAnimation(hide);
            hide.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    frameLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }

    }


    /**
     * 添加键盘到布局中去，这里应该去调用隐藏系统键盘
     *
     * @param rootView
     * @return
     */
    public KeyboardViewManager addKeyboardView(FrameLayout rootView) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM);
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(0, 0, Gravity.BOTTOM);
        rootView.addView(frameLayout, params);
        focusReplace = new EditText(context);
        rootView.addView(focusReplace, params2);
        return this;
    }


    //================================键盘监听事件回调==============================================

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int primaryCode, int[] ints) {
        //获取文本内容
        Editable editable = currentEditText.getText();
        //  获取光标位置
        int start = currentEditText.getSelectionStart();
        switch (primaryCode) {

            case -1://切换大小写按钮
                shiftEnglish();
                keyboardView.setKeyboard(keyboardEnglish);
                break;
            case -2://字母和数字切换按钮
                shiftKeyboard();
                break;

            case -4://完成按钮
                //光标不显示
                currentEditText.setCursorVisible(false);
                hideSoftKeyboard();
                focusReplace.requestFocus();
                if (editList.get(currentEditText) != null) {
                    editList.get(currentEditText).onSureClick();
                }
                break;


            case -5://删除光标前字符
                if (!TextUtils.isEmpty(editable)) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
                break;
            default://普通的按键就直接去把字符串设置到EditText上即可
                //在光标处插入字符
                editable.insert(start, Character.toString((char) primaryCode));
                break;
        }


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


    //===========================Builder模式==============================================
    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {


        private boolean closeKeyboard;
        private Map<EditText, onSureClickListener> editList;

        private Builder() {
            editList = new HashMap<>();
        }


        //如果页面有Eidttist，解决键盘冲突，这个方法必须写
        public Builder bindEditText(EditText... editText) {
            for (int i = 0; i < editText.length; i++) {
                //隐藏系统软键盘冲突，需要配合清单文件一起使用: android:windowSoftInputMode="stateHidden|stateUnchanged"
                SystemSoftKeyUtils.hideSystemSoftKeyboard(editText[i]);
                editList.put(editText[i], null);
            }

            return this;
        }

        public Builder bindEditTextCallBack(EditText editText, onSureClickListener onSurelistener) {
            editList.put(editText, onSurelistener);
            return this;
        }


        public Builder closeKeyboardAnimation(boolean closeKeyboard) {
            this.closeKeyboard = closeKeyboard;
            return this;
        }


        public KeyboardViewManager build(Context context) {
            return new KeyboardViewManager(context, editList, closeKeyboard);
        }

    }

    //==============================================================================================

    /**
     * 英文键盘大小写切换
     */
    private void shiftEnglish() {
        //获取所有的key
        List<Keyboard.Key> keyList = keyboardEnglish.getKeys();
        for (Keyboard.Key key : keyList) {
            if (key.label != null && isKey(key.label.toString())) {
                if (isCapital) {
                    key.label = key.label.toString().toLowerCase();
                    key.codes[0] = key.codes[0] + 32;
                } else {
                    key.label = key.label.toString().toUpperCase();
                    key.codes[0] = key.codes[0] - 32;
                }
            }
        }
        isCapital = !isCapital;
    }


    /**
     * 判断此key是否正确，且存在
     *
     * @param key
     * @return
     */
    private boolean isKey(String key) {
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        if (lowercase.indexOf(key.toLowerCase()) > -1) {
            return true;
        }
        return false;
    }


    //==========================切换键盘=================================================

    /**
     * 切换键盘
     */
    private void shiftKeyboard() {
        if (isShift) {
            keyboardView.setKeyboard(keyboardEnglish);
            keyboardView.invalidate();
        } else {
            keyboardView.setKeyboard(keyboardNumber);
            keyboardView.invalidate();
        }
        isShift = !isShift;
    }


    //===================================点击确定回调==============================================

    public interface onSureClickListener {
        void onSureClick();
    }


}
