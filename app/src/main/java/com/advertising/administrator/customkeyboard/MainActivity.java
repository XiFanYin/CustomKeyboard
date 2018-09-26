package com.advertising.administrator.customkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity{

    private EditText edit1;
    private EditText edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout rootView = findViewById(R.id.rootView);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);

        KeyboardViewManager
                .builder()
//                .setKeyModel(KeyboardViewManager.NUMBERXML)
                .hideSystemSoftKeyboard(edit1,edit2)
                .build(this)
                .addKeyboardView(rootView);



    }


}
