package com.advertising.administrator.customkeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.advertising.administrator.customkeyboard.Test1.Test1Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    private EditText edit;
//    FrameLayout rootView = findViewById(R.id.rootView);
//    edit = findViewById(R.id.edit);
//
//        KeyboardViewManager
//                .builder()
//                .setKeyModel(KeyboardViewManager.NUMBERXML)
//                .hideSystemSoftKeyboard(edit)
//                .build(this)
//                .addKeyboardView(rootView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn1:
                Intent intent = new Intent(this, Test1Activity.class);
                startActivity(intent);
                break;

        }
    }
}
