package com.herbwen.myjnitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("JniTest");
        System.loadLibrary("JniAttack");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_1 = (Button) findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string1 = getFirString();
                Toast.makeText(MainActivity.this, string1, Toast.LENGTH_SHORT).show();
            }
        });
        Button button_2 = (Button) findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string2 = getSecString();
                Toast.makeText(MainActivity.this, string2, Toast.LENGTH_SHORT).show();
            }
        });
        Button button_3 = (Button) findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string3 = getThirdString();
                Toast.makeText(MainActivity.this, string3, Toast.LENGTH_SHORT).show();
            }
        });
        Button button_4 = (Button) findViewById(R.id.button_4);
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long cores = getCores();
                String string4 = String.valueOf(cores);
                Toast.makeText(MainActivity.this, string4, Toast.LENGTH_SHORT).show();
            }
        });
        Button button_5 = (Button) findViewById(R.id.button_5);
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindToCPU();
                Toast.makeText(MainActivity.this, "check the logcat", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public native String getFirString();
    public static native String getSecString();
    public native String getThirdString();
    public native long getCores();
    public native void bindToCPU();
}
