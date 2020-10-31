package com.java.prj.smartcity.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.java.prj.smartcity.Items.MainFragmentAlertItem;
import com.java.prj.smartcity.R;

import java.util.ArrayList;
public class MainFragmentAlertListAdapter extends ArrayAdapter<MainFragmentAlertItem> {

    ArrayList<MainFragmentAlertItem> arrayList;
    Context context;

    public MainFragmentAlertListAdapter(@NonNull Context context, ArrayList<MainFragmentAlertItem> arrayList) {
        super(context, R.layout.alert_item_main_fragment,arrayList);

        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.alert_item_main_fragment,parent,false);

        TextView alert_text = (TextView) rowView.findViewById(R.id.alert_item_main_fragment_alert_title);

        alert_text.setText(arrayList.get(position).alert_text);
        return rowView;
    }
}
