package com.example.kuan_hao.smart_note_taker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Kuan-Hao on 2018/3/30.
 */

public class Data_Second_Layer extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_inside);
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("name");
        String content = bundle.getString("content");
        String url = bundle.getString("url_ref");

        content = content.replace("\n", "").replace("\r", "").replace("\r\n", "");
        url = url.replace("\n", "").replace("\r", "").replace("\r\n", "");

        TextView textView_title = (TextView) findViewById(R.id.IDTitle);
        textView_title.setText(title);
        TextView textView_content = (TextView) findViewById(R.id.IDdefinitionContent);
        textView_content.setText(content);
        TextView textView_url = (TextView) findViewById(R.id.IDURLContent_inside);
        textView_url.setText(url);
        textView_url.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
