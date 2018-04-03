//package com.example.kuan_hao.smart_note_taker.Fragment_Journal;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
//import com.example.kuan_hao.smart_note_taker.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by chaokuanhao on 12/10/2017.
// */
//
//public class Internet_Fragment extends Fragment{
//    /**
//     * Called to have the fragment instantiate its user interface view.
//     * This is optional, and non-graphical fragments can return null (which
//     * is the default implementation).  This will be called between
//     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
//     * <p>
//     * <p>If you return a View from here, you will later be called in
//     * {@link #onDestroyView} when the view is being released.
//     *
//     * @param inflater           The LayoutInflater object that can be used to inflate
//     *                           any views in the fragment,
//     * @param container          If non-null, this is the parent view that the fragment's
//     *                           UI should be attached to.  The fragment should not add the view itself,
//     *                           but this can be used to generate the LayoutParams of the view.
//     * @param savedInstanceState If non-null, this fragment is being re-constructed
//     *                           from a previous saved state as given here.
//     * @return Return the View for the fragment's UI, or null.
//     */
//    private static final String LOG_TAG = Internet_Fragment.class.getSimpleName();
//    private List<parameter_json_keyword> result = new ArrayList<parameter_json_keyword>();
//    private parameter_json_keyword mAdapter;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_internet, container, false);
//        mAdapter = new json_keyword_adaptor(this, new ArrayList<parameter_json_keyword>());
//        return view;
//    }
//
//    private class keywordAsyncTask extends AsyncTask<String, Void, List<parameter_json_keyword>>{
//        @Override
//        protected List<parameter_json_keyword> doInBackground(String... urls) {
//            if (urls.length < 1 || urls[0] == null ){
//                return null;
//            }
//            String internet_description;
//            String internet_url_reference;
//            urls[0] = urls[0] + "data/json";
//            //urls[1] = urls[1] + currentLatitude +'/' + currentLongitude;
//            Log.i(LOG_TAG, urls[0]);
//            //Log.i(LOG_TAG, urls[1]);
//            result = QueryUtils_json_parser_data.request_parameter_data(urls[0]);
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(List<parameter_json_keyword> data) {
//            mAdapter.clear();
//            if (data != null && !data.isEmpty()){
//                mAdapter.addAll(data);
//        }
//    }
//}
