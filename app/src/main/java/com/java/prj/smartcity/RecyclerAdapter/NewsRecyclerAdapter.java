package com.java.prj.smartcity.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.java.prj.smartcity.Items.NewsItem;
import com.java.prj.smartcity.R;

import java.util.ArrayList;


public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

    ArrayList<NewsItem> arrayList;
    Context context;



    public NewsRecyclerAdapter(ArrayList<NewsItem> arrayList, Context context)
    {
        this.arrayList=arrayList;
        this.context=context;
    }

    @Override
    public NewsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_newsheadlines, parent, false);
        return new NewsRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsRecyclerAdapter.ViewHolder holder, final int position) {


        holder.heading.setText(arrayList.get(position).news_heading);
        holder.date.setText(arrayList.get(position).date);
        holder.content.setText(arrayList.get(position).news_content);

        // holder.question_textview.setText(arrayList.get(position).question);

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView heading;
        CardView cardView;
        TextView date;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);

            heading=itemView.findViewById(R.id.heading);
            cardView=itemView.findViewById(R.id.list_newheadlines_cardview);
            date=itemView.findViewById(R.id.date);
            content=itemView.findViewById(R.id.content);
        }
    }


}





