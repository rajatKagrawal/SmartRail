package com.example.android.smartrail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 10-06-2016.
 */
public class Vizadapter extends RecyclerView.Adapter<Vizadapter.MyViewHolder> {

    LayoutInflater inflater;
    List<TrainDetails> data = Collections.EMPTY_LIST;
    private ClickListner clicklistner;

    public Vizadapter(Context context, List<TrainDetails> data) {
        this.data = data;
        Log.d("test","kk");
        inflater = LayoutInflater.from(context);
        Log.d("test",String.valueOf(data)+"kk");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.rowlayout, parent, false);

        MyViewHolder holder = new MyViewHolder(view, viewType);
        return holder;

    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

            TrainDetails current = data.get(position);
            holder.name.setText(current.name);
            holder.number.setText(current.number);
            holder.dpt_tm.setText(current.dtime);
         holder.arr_tm.setText(current.atime);
        holder.t_tm.setText(current.ttime);
        holder.to.setText(current.to);
        holder.from.setText(current.from);
        holder.day.setText("SL-A \n 3A-A");
       // holder.day.setText(current.d.toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClicklistner(ClickListner clicklistner) {
        this.clicklistner = clicklistner;
    }

    public interface ClickListner {
        public void itemClicked(View view, int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,number,dpt_tm,arr_tm,to,from,t_tm,day;


        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            itemView.setOnClickListener(this);

                name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);
            dpt_tm = (TextView) itemView.findViewById(R.id.departure);
            arr_tm = (TextView) itemView.findViewById(R.id.arrival);
            to = (TextView) itemView.findViewById(R.id.To);
            from = (TextView) itemView.findViewById(R.id.from);
            t_tm = (TextView) itemView.findViewById(R.id.tvltm);
            day = (TextView) itemView.findViewById(R.id.Days);
        }

        @Override
        public void onClick(View v) {
            clicklistner.itemClicked(v, getAdapterPosition());
        }
    }


    }


