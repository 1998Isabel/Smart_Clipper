package com.example.kuan_hao.smart_note_taker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kuan_hao.smart_note_taker.Fragment_Journal.parameter_json_keyword;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuan-Hao on 2018/4/1.
 */

public class Challenging_answer extends AppCompatActivity{

    private EditText editText;
    private Button mButton;
    private String student_answer;
    private String LOG_TAG = Challenging_answer.class.getSimpleName();
    private static List<parameter_json_keyword> result = new ArrayList<parameter_json_keyword>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_challenge);
        editText = (EditText)findViewById(R.id.IDEditText);
        mButton = (Button) findViewById(R.id.button_press_challenge);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL_here = "http://163.28.17.68:5000/student/upload/";
                Send_answer task01 = new Send_answer();
                student_answer  = editText.getText().toString();
                task01.execute(URL_here + student_answer);
//                Http_Request http_request = new Http_Request();
//                try {
//                    Log.d("FUckkkkkkk",  URL_here+student_answer);
//                    Log.d("LAAAAAAAAAAAAAAA~~~~~~", student_answer);
//                    http_request.send_data(URL_here+student_answer);
//                    Log.d(LOG_TAG,  URL_here+student_answer);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        });
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
//
//        InputMethodManager inn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        inn.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
    private class Send_answer extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null ){
                return null;
            }
            urls[0] = urls[0];
            //urls[1] = urls[1] + currentLatitude +'/' + currentLongitude;
            Log.i(LOG_TAG, urls[0]);
            //Log.i(LOG_TAG, urls[1]);
            Http_Request http_request = new Http_Request();
            try {
                http_request.send_data(urls[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            Log.d("HAHAHAHAHAHAHHAHAAA", String.valueOf(result));
            String Ha = "Ha";
            return Ha;
        }

//        @Override
//        protected void onPostExecute(List<parameter_json_keyword> data) {
////            mAdapter.clear();
////            if (data != null && !data.isEmpty()){
////                mAdapter.addAll(data);
////        }
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
////                    new Scrolling_adapterActivity.keywordAsyncTask().execute(KEY_WORD_REQUEST_URL);
////                    mAdapter.notifyDataSetChanged();
//                }
//            }, 1*1000);
//        }
    }



}
