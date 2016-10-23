package com.example.android.smartrail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trains extends AppCompatActivity implements Vizadapter.ClickListner {
    private RecyclerView recyclerview;
    private Vizadapter Adapter;
    Bundle data;
    ArrayList <TrainDetails> T;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains);
       // Bundle bundle = getIntent().getExtras();
        T=new ArrayList<TrainDetails>();
        Bundle bundle = getIntent().getExtras();
        Log.d("test","train called");
       // T=(TrainDetails)bundle.getStringArrayList("train");
       // T = (ArrayList<TrainDetails>) getIntent().getParcelableExtra("train");
       // Log.d("test",String.valueOf(T.size()));
        recyclerview = (RecyclerView) findViewById(R.id.list1);
        Adapter = new Vizadapter(this,getdata());
        Log.d("test","vizfinished");
        recyclerview.setAdapter(Adapter);
        Adapter.setClicklistner(this);
        Log.d("test","adapterset");
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        Log.d("test","layoutset");

    }
    public   List<TrainDetails> getdata() {
       /* List<TrainDetails> data = new ArrayList<>();
        //int[] icons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        //String[] title = {"event 1", "event2", "event3", "event4"};
        for (int i = 0; i < 10; i++) {
            TrainDetails current = new TrainDetails();
            current.name =
            current.title = title[i % 4];
            data.add(current);
        }
        return data;*/
        List<TrainDetails> k = new ArrayList<TrainDetails>();

        TrainDetails current = new TrainDetails();

        current.name = "BGTKT-BSP EXP";
        current.number= "18244";
        current.dtime="07:45";
        current.atime="04:55";
        current.ttime="21:10";
        current.to = "NAGPUR";
        current.from = "JAIPUR";
        k.add(current);


        Log.d("test",String.valueOf(k.size()));
        return k;


    }

    @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(view.getContext(), "Smart click called", Toast.LENGTH_LONG).show();

    }
}







