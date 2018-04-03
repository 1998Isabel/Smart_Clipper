package com.example.kuan_hao.smart_note_taker.Fragment_Journal;

/**
 * Created by Kuan-Hao on 2018/3/29.
 */

public class parameter_json_keyword {
    private String mInternet_name,mIinternet_description, mInternet_url_reference;

    public parameter_json_keyword( String a, String b, String c){
        mInternet_name = a;
        mIinternet_description = b;
        mInternet_url_reference = c;
    }

    public String getmInternet_name(){
        return mInternet_name;
    }

    public String getInternet_description() {
        return mIinternet_description;
    }
    public String getInternet_url_reference(){
        return mInternet_url_reference;
    }

    public void setmInternet_name(String internet_name){
        this.mInternet_name = internet_name;
    }
    public void setInternet_description(String internet_description ){
        this.mIinternet_description = internet_description;
    }
    public void setInternet_url_reference(String internet_url_reference){
        this.mInternet_url_reference = internet_url_reference;
    }

}
