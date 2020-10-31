package com.java.prj.smartcity.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.java.prj.smartcity.AlertDetailsActivity;
import com.java.prj.smartcity.Items.AlertItem;
import com.java.prj.smartcity.R;

import java.util.ArrayList;

public class AlertsRecyclerAdapter extends RecyclerView.Adapter<AlertsRecyclerAdapter.ViewHolder> {

    ArrayList<AlertItem> arrayList;
    Context context;

    public AlertsRecyclerAdapter(ArrayList<AlertItem> arrayList,Context context)
    {
        this.arrayList=arrayList;
        this.context=context;
    }

    @Override
    public AlertsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alerts_item, parent, false);
        return new AlertsRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlertsRecyclerAdapter.ViewHolder holder,final int position) {
        holder.date.setText(arrayList.get(position).date);
        holder.time.setText(arrayList.get(position).time);
        holder.title.setText(arrayList.get(position).title);
        holder.area.setText(arrayList.get(position).area);

        if (arrayList.get(position).type.equals("1"))
        {
            int theme= Color.parseColor("#FFA500");
            holder.type.setBackgroundColor(theme);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, AlertDetailsActivity.class);
                intent.putExtra("alert_id",arrayList.get(position).alert_id);
                intent.putExtra("date",arrayList.get(position).date);
                intent.putExtra("time",arrayList.get(position).time);
                intent.putExtra("title",arrayList.get(position).title);
                intent.putExtra("area",arrayList.get(position).area);
                intent.putExtra("type",arrayList.get(position).type);
                intent.putExtra("description",arrayList.get(position).description);
                intent.putExtra("guidelines",arrayList.get(position).guidelines);
                intent.putExtra("helpline",arrayList.get(position).helpline);
                intent.putExtra("disclaimer",arrayList.get(position).disclaimer);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView date,time,title,area;
        View type;

        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.alerts_item_cardview);
            date=itemView.findViewById(R.id.alerts_item_date);
            time=itemView.findViewById(R.id.alerts_item_time);
            title=itemView.findViewById(R.id.alerts_item_title);
            area=itemView.findViewById(R.id.alerts_item_area);
            type=itemView.findViewById(R.id.alerts_item_category);
        }
    }


}
