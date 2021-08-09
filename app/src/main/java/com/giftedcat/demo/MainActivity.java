package com.giftedcat.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.giftedcat.justifylib.utils.LogUtil;
import com.giftedcat.justifylib.utils.TextUtil;
import com.giftedcat.justifylib.view.JustifyTextView;

public class MainActivity extends AppCompatActivity {

    JustifyTextView tvTitle1, tvTitle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle1 = findViewById(R.id.tv_titl1);
        tvTitle2 = findViewById(R.id.tv_titl2);

        tvTitle1.setText("你好");
        tvTitle1.setExtraWords(":");
        tvTitle1.setNumberWord(11);

        LogUtil.d("tvTitle1的realText为" + tvTitle1.getRealText());
        LogUtil.d("tvTitle1的extraWords为" + tvTitle1.getExtraWords());
        LogUtil.d("tvTitle1的numberWord为" + tvTitle1.getNumberWord());
    }
}