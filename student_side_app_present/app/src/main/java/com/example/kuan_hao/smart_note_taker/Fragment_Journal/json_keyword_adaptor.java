package com.example.kuan_hao.smart_note_taker.Fragment_Journal;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kuan_hao.smart_note_taker.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Kuan-Hao on 2018/3/29.
 */

//class json_keyword_adaptor extends RecyclerView.Adapter<json_keyword_adaptor.MyHolder>{
//
//
//    @Override
//    public json_keyword_adaptor onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = View.inflate( get, R.layout.item_layout, null);
//        return new json_keyword_adaptor(view);
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent){
//        View listItemView = convertView;
//        if(listItemView == null){
//            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.data_list_item, parent, false);
//        }
//        parameter_json_keyword current_row_data = getItem(position);
//
//        TextView keywordTitle = listItemView.findViewById(R.id.IDTitle);
//        keywordTitle.setText(current_row_data.getmInternet_name());
//
//        TextView keywordContent = listItemView.findViewById(R.id.IDdefinitionContent);
//        keywordContent.setText(current_row_data.getInternet_description());
//
//        TextView keywordUrl = listItemView.findViewById(R.id.IDURL);
//        keywordUrl.setText(current_row_data.getInternet_url_reference());
//        return listItemView;
//    }
//}
//
//class MyHolder extends RecyclerView.ViewHolder{
//    public  MyHolder(View itemView){
//        super(itemView);
//    }
//}