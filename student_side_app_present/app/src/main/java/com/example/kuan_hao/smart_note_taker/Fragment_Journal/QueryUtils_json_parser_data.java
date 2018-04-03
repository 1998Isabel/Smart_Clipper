package com.example.kuan_hao.smart_note_taker.Fragment_Journal;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Howard on 2017/7/15.
 */

//the helper function relating to requesting nad receiving the data from Azure
public final class QueryUtils_json_parser_data {

    private static final String LOG_TAG = QueryUtils_json_parser_data.class.getSimpleName();
    private QueryUtils_json_parser_data(){
    }

    public static List<parameter_json_keyword> request_parameter_data(String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try{
            jsonResponse = makeHTTPRequest(url);
        }
        catch (IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        List<parameter_json_keyword> parameter_json_keywords = extractFeatureFormJson(jsonResponse);
        return  parameter_json_keywords;
    }

    private  static  URL createUrl (String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        }
        catch (MalformedURLException e){
            Log.e(LOG_TAG, "Problem building the URL", e);
        }
        return url;
    }

    private static String makeHTTPRequest(URL url) throws IOException{
        String jsonResponse = "";
        if (url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000000);
            urlConnection.setConnectTimeout(1500000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Log.e(LOG_TAG, "Error response code:"+ urlConnection.getResponseCode());
            }
        }
        catch (IOException e ){
            Log.e(LOG_TAG, "Problem retrieving from the JSON results", e);
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static List<parameter_json_keyword> extractFeatureFormJson (String jsonResponse){
        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        List<parameter_json_keyword> key_words = new ArrayList<parameter_json_keyword>();
        try {
            JSONArray root = new JSONArray(jsonResponse);
            for ( int i = 0; i < root.length(); i++ ){
                JSONObject elementsWrapper = root.getJSONObject(i);
                JSONObject Internet = elementsWrapper.getJSONObject("Int");
                String Internet_keyword_name = Internet.getString("name");
                String Internet_description = Internet.getString("description");
                String Internet_url = Internet.getString("url");
                parameter_json_keyword parameterJsonKeyword = new parameter_json_keyword(Internet_keyword_name, Internet_description, Internet_url);
                key_words.add(parameterJsonKeyword);
            }
            Log.d(LOG_TAG, "\n Successful in paring JSON file \n");
//            List<Parameter_Police> Police = new ArrayList<Parameter_Police>();
//            JSONArray police = root.getJSONArray("police");
//            for ( int i = 0; i < police.length(); i++ ){
//                JSONObject elementsWrapper = fireDep.getJSONObject(i);
//                String police_lat = elementsWrapper.getString("lat");
//                String police_lng = elementsWrapper.getString("lng");
//                String police_name = elementsWrapper.getString("單位");
//                String police_address = elementsWrapper.getString("地址");
//                String police_zipCode = elementsWrapper.getString("郵遞區號");
//                String police_phone = elementsWrapper.getString("電話");
//                Parameter_Police parameter_Police = new Parameter_Police( police_lat, police_lng, police_name, police_address, police_zipCode, police_phone);
//                Police.add(parameter_Police);
//            }

        }catch (JSONException e ){
            Log.e(LOG_TAG, "Problem parsing the JSON results" + e);
        }
        return key_words;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while ( line !=  null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return  output.toString();
    }
}
