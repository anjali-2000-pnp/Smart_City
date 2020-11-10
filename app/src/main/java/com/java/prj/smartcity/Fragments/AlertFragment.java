package com.java.prj.smartcity.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.java.prj.smartcity.HelperClasses.AppConstants;
import com.java.prj.smartcity.HelperClasses.HttpHandler;
import com.java.prj.smartcity.Items.AlertItem;
import com.java.prj.smartcity.ProjectType1DetailsActivity;
import com.java.prj.smartcity.R;
import com.java.prj.smartcity.RecyclerAdapter.AlertsRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlertFragment extends Fragment {

    public AlertFragment() {
        // Required empty public constructor
    }

    ArrayList<AlertItem> items;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alerts_detail, container, false);

        items=new ArrayList<>();

        //recyclerView=(RecyclerView)view.findViewById(R.id.fragment_alerts_recyclerview);
//        recyclerView.setHasFixedSize(true);
  //      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      //  new getAlertList().execute();

        return view;
    }

/*    private class getAlertList extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("Fetching data");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            HttpHandler sh = new HttpHandler();

            /*String jsonStr = sh.makeServiceCall(AppConstants.get_alerts_list);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray feeds = jsonObj.getJSONArray("result");

                    for (int i = 0; i < feeds.length(); i++) {
                        JSONObject c = feeds.getJSONObject(i);

                        String alert_id = c.getString("alert_id");
                        String date = c.getString("date");
                        String time = c.getString("time");
                        String title = c.getString("title");
                        String area = c.getString("area");
                        String type = c.getString("type");
                        String description = c.getString("description");
                        String guidelines = c.getString("guidelines");
                        String helpline = c.getString("helpline");
                        String disclaimer = c.getString("disclaimer");

                        AlertItem object = new AlertItem(alert_id,date,time,title,area,type,description,
                                guidelines,helpline,disclaimer);
                        items.add(object);
                    }

                } catch (final JSONException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),"Couldn't get data from Server. Please try again later", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            } else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_LONG).show();
                    }
                });

            }*/
          //  return null;
       // }

        protected void onPostExecute(Void aVoid) {
          //  super.onPostExecute(aVoid);

            if (progressDialog.isShowing())
            {
                progressDialog.hide();
            }
//            recyclerView.setAdapter(new AlertsRecyclerAdapter(items,getActivity()));
        }
    }



