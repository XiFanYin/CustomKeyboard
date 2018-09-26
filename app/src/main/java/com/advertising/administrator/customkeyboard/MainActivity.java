package com.advertising.administrator.customkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

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
                .bindEditText(edit1,edit2)
                .bindEditTextCallBack(edit1, new KeyboardViewManager.onSureClickListener() {
                    @Override
                    public void onSureClick() {
                        Toast.makeText(MainActivity.this, "edit1", Toast.LENGTH_SHORT).show();
                    }
                })
                .bindEditTextCallBack(edit2, new KeyboardViewManager.onSureClickListener() {
                    @Override
                    public void onSureClick() {
                        Toast.makeText(MainActivity.this, "edit2", Toast.LENGTH_SHORT).show();
                    }
                })
                .build(this)
                .addKeyboardView(rootView);



    }


}
