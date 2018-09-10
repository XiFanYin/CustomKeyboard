package com.advertising.administrator.customkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout rootView = findViewById(R.id.rootView);
        edit = findViewById(R.id.edit);

        KeyboardViewManager
                .builder()
                .setKeyModel(KeyboardViewManager.NUMBERXML)
                .hideSystemSoftKeyboard(edit)
                .build(this)
                .addKeyboardView(rootView);

    }
}
