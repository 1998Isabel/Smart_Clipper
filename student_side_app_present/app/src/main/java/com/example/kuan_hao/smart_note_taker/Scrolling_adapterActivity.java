package com.example.kuan_hao.smart_note_taker;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

//import com.example.kuan_hao.smart_note_taker.Fragment_Journal.Internet_Fragment;
import com.example.kuan_hao.smart_note_taker.Fragment_Journal.QueryUtils_json_parser_data;
import com.example.kuan_hao.smart_note_taker.Fragment_Journal.parameter_json_keyword;
import com.example.kuan_hao.smart_note_taker.Utils.SectionPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuan-Hao on 2018/3/10.
 */

public class Scrolling_adapterActivity extends AppCompatActivity {

    public enum appBarState {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private appBarState mCurrentState = appBarState.IDLE;
    private static final String LOG_TAG = Scrolling_adapterActivity.class.getSimpleName();
    private json_keyword_adaptor mAdapter = new json_keyword_adaptor();
    private static List<parameter_json_keyword> result = new ArrayList<parameter_json_keyword>();
    private String KEY_WORD_REQUEST_URL = "http://163.28.17.68:5000/";
    private static Context context;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Scrolling_adapterActivity.context = getApplicationContext();
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Journal);
        setSupportActionBar(toolbar);

//        initInstanceDrawer( );
//        setupViewPager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle) ;
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final EditText editText = (EditText) findViewById(R.id.input_search);
//        autoCompleteTextView.setOnClickListener(mAutocompleteClickListener);
        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Scrolling_adapterActivity.this, Challenging_answer.class);
                startActivity(intent);
            }
        });
        Button button_check = (Button) findViewById(R.id.ID_Button_to_find);
        button_check.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String answer = "";
                String URL_STRING = "http://163.28.17.68:5000/variable/";
                answer = editText.getText().toString();
                updateKeyword task2 = new updateKeyword();
                task2.execute(URL_STRING+answer);
            }
        });


        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        keywordAsyncTask task01 = new keywordAsyncTask();
        task01.execute(KEY_WORD_REQUEST_URL);
        mAdapter.setOnItemClickListener(new json_keyword_adaptor.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(Scrolling_adapterActivity.this, "You just pressed", Toast.LENGTH_SHORT).show();
                String Internet_name = result.get(position).getmInternet_name();
                String Internet_context = result.get(position).getInternet_description();
                String Internet_url = result.get(position).getInternet_url_reference();
                Intent intent = new Intent();
                intent.setClass(Scrolling_adapterActivity.this, Data_Second_Layer.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", Internet_name);
                bundle.putString("content", Internet_context);
                bundle.putString("url_ref", Internet_url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            hideSoftKeyboard();
        }
    };

    public static Context getAppContext() {
        return Scrolling_adapterActivity.context;
    }

    private void setupViewPager(){
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new Internet_Fragment());       // index 0
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                switch (position){
//                    case 0:
//                        fab.show();
//                        break;
//                    case 1:
//                        fab.show();
//                        break;
//                    case 2:
//                        fab.show();
//                        break;
//                    default:
//                        fab.hide();
//                        break;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//
//        tabLayout.getTabAt(0).setText("Internet");
//        tabLayout.getTabAt(1).setText("Dictionary");
    }

    public static class json_keyword_adaptor extends RecyclerView.Adapter<json_keyword_adaptor.MyHolder> implements View.OnClickListener{

        private Integer previous_size = 0;
        private OnItemClickListener mOnItemClickListener = null;
        public  interface OnItemClickListener {
            void onItemClick(View view , int position);
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
//            public View getView(int position, View convertView, ViewGroup parent){
//            View listItemView = convertView;
//            if(listItemView == null){
//                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.data_list_item, parent, false);
//            }
//            parameter_json_keyword current_row_data = getItem(position);
//
//            TextView keywordTitle = listItemView.findViewById(R.id.IDTitle);
//            keywordTitle.setText(current_row_data.getmInternet_name());
//
//            TextView keywordContent = listItemView.findViewById(R.id.IDdefinitionContent);
//            keywordContent.setText(current_row_data.getInternet_description());
//
//            TextView keywordUrl = listItemView.findViewById(R.id.IDURL);
//            keywordUrl.setText(current_row_data.getInternet_url_reference());
//            return listItemView;
//        }
            MyHolder vh = new MyHolder(view);
            view.setOnClickListener(this);
            return vh;
        }


        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.mTitle.setText(result.get(position).getmInternet_name());
//            holder.mDefinitionTitle.setText("Definition");
//            holder.mDefinitionContent.setText("jasd;fjasd;fjsa;lfjas;fa");
            holder.mDefTitle.setText("Def:");
            String new_content = result.get(position).getInternet_description().replace("\n", "").replace("\r", "").replace("\r\n", "");
            holder.mDefContent.setText(new_content.substring(0, 40) + " .....");
            holder.itemView.setTag(position);
        }


        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, (int)v.getTag());
            }
        }

        public void setOnItemClickListener(OnItemClickListener listener){
            this.mOnItemClickListener = listener;
        }

        @Override
        public int getItemCount() {
            if(previous_size != result.size()){
                Toast.makeText(Scrolling_adapterActivity.getAppContext()  , "New note added!", Toast.LENGTH_SHORT).show();
            }
            previous_size = result.size();
            return result.size();
        }

        //        public View getView(int position, View convertView, ViewGroup parent){
//            View listItemView = convertView;
//            if(listItemView == null){
//                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.data_list_item, parent, false);
//            }
//            parameter_json_keyword current_row_data = getItem(position);
//
//            TextView keywordTitle = listItemView.findViewById(R.id.IDTitle);
//            keywordTitle.setText(current_row_data.getmInternet_name());
//
//            TextView keywordContent = listItemView.findViewById(R.id.IDdefinitionContent);
//            keywordContent.setText(current_row_data.getInternet_description());
//
//            TextView keywordUrl = listItemView.findViewById(R.id.IDURL);
//            keywordUrl.setText(current_row_data.getInternet_url_reference());
//            return listItemView;
//        }
        class MyHolder extends RecyclerView.ViewHolder{
            //        View
//        TextView keywordTitle = listItemView.findViewById(R.id.IDTitle);
//        keywordTitle.setText(current_row_data.getmInternet_name());
//        TextView keywordContent = listItemView.findViewById(R.id.IDdefinitionContent);
//        keywordContent.setText(current_row_data.getInternet_description());
//
//        TextView keywordUrl = listItemView.findViewById(R.id.IDURL);
//        keywordUrl.setText(current_row_data.getInternet_url_reference());
            public TextView mTitle;
//            public TextView mDefinitionTitle;
//            public TextView mDefinitionContent;
            public TextView mDefTitle;
            public TextView mDefContent;
            public  MyHolder(View itemView){
                super(itemView);
                mTitle = (TextView) itemView.findViewById(R.id.IDTitle);
//                mDefinitionTitle = (TextView) itemView.findViewById(R.id.IDdefinitionTitle);
//                mDefinitionContent = (TextView) itemView.findViewById(R.id.IDdefinitionContent);
                mDefTitle = (TextView)itemView.findViewById(R.id.IDDEFTITLE);
                mDefContent = (TextView)itemView.findViewById(R.id.IDDEFContent);
            }
        }
    }

        private class keywordAsyncTask extends AsyncTask<String, Void, List<parameter_json_keyword>> {
        @Override
        protected List<parameter_json_keyword> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null ){
                return null;
            }
            String internet_description;
            String internet_url_reference;
            urls[0] = urls[0] + "data/json";
            //urls[1] = urls[1] + currentLatitude +'/' + currentLongitude;
            Log.i(LOG_TAG, urls[0]);
            //Log.i(LOG_TAG, urls[1]);
            result = QueryUtils_json_parser_data.request_parameter_data(urls[0]);
            Log.d("HAHAHAHAHAHAHHAHAAA", String.valueOf(result));
            return result;
        }

        @Override
        protected void onPostExecute(List<parameter_json_keyword> data) {
//            mAdapter.clear();
//            if (data != null && !data.isEmpty()){
//                mAdapter.addAll(data);
//        }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new keywordAsyncTask().execute(KEY_WORD_REQUEST_URL);
                    mAdapter.notifyDataSetChanged();
                }
            }, 1*1000);
        }
    }
    private class updateKeyword extends AsyncTask<String, Void, List<parameter_json_keyword>> {
        @Override
        protected List<parameter_json_keyword> doInBackground(String... urls) {
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
            Log.d("HAHAHAHAHAHAHHAHAAA", String.valueOf(result));
            return result;
        }

        @Override
        protected void onPostExecute(List<parameter_json_keyword> data) {
//            mAdapter.clear();
//            if (data != null && !data.isEmpty()){
//                mAdapter.addAll(data);
//        }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new keywordAsyncTask().execute(KEY_WORD_REQUEST_URL);
                    mAdapter.notifyDataSetChanged();
                }
            }, 1*1000);
        }
    }



}
