package com.java.prj.smartcity.RecyclerAdapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.java.prj.smartcity.HelperClasses.AppConstants;
import com.java.prj.smartcity.HelperClasses.RequestHandler;
import com.java.prj.smartcity.Items.PollsItem;
import com.java.prj.smartcity.R;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Boolean.TRUE;

public class PollsRecyclerAdapter extends RecyclerView.Adapter<PollsRecyclerAdapter.ViewHolder> {

    ArrayList<PollsItem> arrayList;
    Context context;

    public PollsRecyclerAdapter(ArrayList<PollsItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public PollsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.polls_format, parent, false);
        return new PollsRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PollsRecyclerAdapter.ViewHolder holder, final int position) {

        holder.question_textview.setText(arrayList.get(position).question);

        if (arrayList.get(position).ans.equals("1")) {
            holder.radioButtonYes.setChecked(TRUE);
            holder.radioButtonNo.setVisibility(View.GONE);
            holder.radioButtonCantsay.setVisibility(View.GONE);
            holder.submitButton.setVisibility(View.GONE);
        } else if (arrayList.get(position).ans.equals("2")) {
            holder.radioButtonNo.setChecked(TRUE);
            holder.radioButtonYes.setVisibility(View.GONE);
            holder.radioButtonCantsay.setVisibility(View.GONE);
            holder.submitButton.setVisibility(View.GONE);
        } else if (arrayList.get(position).ans.equals("3")) {
            holder.radioButtonCantsay.setChecked(TRUE);
            holder.radioButtonYes.setVisibility(View.GONE);
            holder.radioButtonNo.setVisibility(View.GONE);
            holder.submitButton.setVisibility(View.GONE);
        }

        /*
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do intents etc.
            }
        });*/

        // Empty Listeners
        holder.radioButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.radioButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.radioButtonCantsay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.radioButtonYes.isChecked()) {
                    submitAnswer(1, position, holder);
                } else if (holder.radioButtonNo.isChecked()) {
                    submitAnswer(2, position, holder);
                } else if (holder.radioButtonCantsay.isChecked()) {
                    submitAnswer(3, position, holder);
                } else {
                    Toast.makeText(context, "Select one option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView question_textview;
        CardView cardView;
        RadioButton radioButtonYes, radioButtonNo, radioButtonCantsay;
        Button submitButton;

        public ViewHolder(View itemView) {
            super(itemView);

            question_textview = itemView.findViewById(R.id.polls_question);
            cardView = itemView.findViewById(R.id.polls_format_cardview);
            radioButtonYes = itemView.findViewById(R.id.polls_yes);
            radioButtonNo = itemView.findViewById(R.id.polls_no);
            radioButtonCantsay = itemView.findViewById(R.id.polls_cantsay);
            submitButton = itemView.findViewById(R.id.polls_submit_button);
        }
    }

    private void submitAnswer(final int answer, final int position, final ViewHolder holder) {
        class SubmitAnswer extends AsyncTask<Void, Void, String> {

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(PollsRecyclerAdapter.this.context, "Submitting Answer", "Please Wait...", false,
                        false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                if (Integer.parseInt(s) == 2) {
                    // To remove other options from the card
                    if (holder.radioButtonYes.isChecked()) {
                        holder.radioButtonNo.setVisibility(View.GONE);
                        holder.radioButtonCantsay.setVisibility(View.GONE);
                        holder.submitButton.setVisibility(View.GONE);
                    } else if (holder.radioButtonNo.isChecked()) {
                        holder.radioButtonYes.setVisibility(View.GONE);
                        holder.radioButtonCantsay.setVisibility(View.GONE);
                        holder.submitButton.setVisibility(View.GONE);
                    } else if (holder.radioButtonCantsay.isChecked()) {
                        holder.radioButtonYes.setVisibility(View.GONE);
                        holder.radioButtonNo.setVisibility(View.GONE);
                        holder.submitButton.setVisibility(View.GONE);
                    } else {
                        // To remove that card. Normally shldnt be called as submit button shldnt be available in this case
                        PollsRecyclerAdapter.this.arrayList.remove(position);
                        PollsRecyclerAdapter.this.notifyItemRemoved(position);
                    }
                    Toast.makeText(context, "Submitted", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(s) == 1) {
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Already submitted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                SharedPreferences prefs = context.getSharedPreferences(AppConstants.CURRENT_USER, MODE_PRIVATE);
                String user_id = prefs.getString("user_id", "0");

                HashMap<String, String> args = new HashMap<>();
                args.put("poll_id", arrayList.get(position).id);
                args.put("user_id", user_id);
                args.put("poll_ans", String.valueOf(answer));

                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(AppConstants.save_poll_answers, args);
            }
        }
        SubmitAnswer a = new SubmitAnswer();
        a.execute();
    }
}