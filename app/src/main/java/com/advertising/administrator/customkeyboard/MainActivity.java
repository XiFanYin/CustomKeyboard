package com.advertising.administrator.customkeyboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private KeyboardViewManager keyboardViewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout rootView = findViewById(R.id.rootView);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);

        keyboardViewManager = KeyboardViewManager
                .builder()
                .bindEditText(edit1, edit2, edit3)
                .bindEditTextCallBack(edit1, new KeyboardViewManager.onSureClickListener() {
                    @Override
                    public void onSureClick() {

                    }
                })
                .bindEditTextCallBack(edit2, new KeyboardViewManager.onSureClickListener() {
                    @Override
                    public void onSureClick() {

                    }
                })
                .build(this)
                .addKeyboardView(rootView);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        keyboardViewManager.hideSoftKeyboard();
        return super.onTouchEvent(event);
    }
}
