package com.hly.net;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (NetStatue.isConnected(this)) {
            Toast.makeText(this, "网络可以使用", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "网络不可以使用", Toast.LENGTH_SHORT).show();
        }

    }
}
