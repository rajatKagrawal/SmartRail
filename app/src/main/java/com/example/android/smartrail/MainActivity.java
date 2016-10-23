package com.example.android.smartrail;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    int t=0;
    int t1=0;
    int day=0,month=0,Year=0;
    Spinner src;
    Spinner dst;
    TextView txt1;
    EditText Date;
    DatePickerDialog datePickerDialog;

    private static final String TAG = MainActivity.class.getSimpleName();

    String station1, station2, date;
    private String FEED_URL1 = "http://api.railwayapi.com/between/source/";
    private String FEED_URL2 = "/dest/";
    private String FEED_URL3 = "/date/";
    private String FEED_URL4 = "/apikey/2rcolsob";
    private String URL;
    private ArrayList<TrainDetails> Train;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        src=(Spinner)findViewById(R.id.source_spinner);
        txt1=(TextView)findViewById(R.id.srcstn);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.sources_array, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         src.setAdapter(adapter);
        src.setOnItemSelectedListener(this);


        dst=(Spinner)findViewById(R.id.dst_spinner);
        dst.setAdapter(adapter);
        dst.setOnItemSelectedListener(this);

         //date= (DatePicker)findViewById(R.id.simpleDatePicker);
        Date = (EditText) findViewById(R.id.date);
        Date.setInputType(InputType.TYPE_NULL);
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                Year = c.get(Calendar.YEAR);
                month=c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                Date.setText(day + "/"+ (month + 1) + "/" + Year);
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                // set day of month , month and year value in the edit text

                                day=dayOfMonth;
                                month=monthOfYear;
                                Year=year;

                                Date.setText(day + "/"+ (month + 1) + "/" + Year);
                            }

                        }, Year, month, day);
                datePickerDialog.show();


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner Spin = (Spinner)adapterView;
        if (Spin.getId() == R.id.source_spinner) {
            if (t > 0) {
                TextView v = (TextView) view;;
                Log.d("test", v.getText().toString());
                s1=v.getText().toString();
            }
            t++;
        }

        if (Spin.getId() == R.id.dst_spinner){
            if(t1 > 0){
                TextView v= (TextView)view;
                Log.d("test",v.getText().toString());
                s2=v.getText().toString();
            }
            t1++;
        }

    }

    public void submit(View v){
        //day = date.getDayOfMonth();
       // month = date.getMonth();
        Log.d("test",""+day);


        Log.d(TAG,"yo" +s1+ s2+date);
        date=""+day+"-"+month;
        URL= FEED_URL1+s1+FEED_URL2+s2+FEED_URL3+date+FEED_URL4;
        Log.d("test", URL);
        new AsyncHttpTask().execute(URL);

       startActivity(new Intent(getApplicationContext(), Trains.class));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            try {
                // Create Apache HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse = httpclient.execute(new HttpGet(params[0]));
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    Log.d("test","print");
                    String response = streamToString(httpResponse.getEntity().getContent());
                    Log.d("test","status");
                    parseResult(response);
                    Log.d("test","parsse");
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Lets update UI
            Log.d("test",result.toString());
            if (result == 1) {
                // mGridAdapter.setGridData(mGridData);
               // Intent i = new Intent(getApplicationContext(),Trains.class);
                //i.putExtra("train",Train);
                //finish();
                //startActivity(i);
            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }

            //Hide progressbar
            //mProgressBar.setVisibility(View.GONE);
        }
    }


    String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        // Close stream
        if (null != stream) {
            stream.close();
        }
        return result;
    }

    /**
     * Parsing the feed results and get the list
     *
     * @param result
     */
    private void parseResult(String result) {
        try {
            Train = new ArrayList<TrainDetails>();
            Log.d("test", result);
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("train");
            TrainDetails item;
            Log.d("test",String.valueOf(posts.length()));
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                item = new TrainDetails();
                item.setName(post.optString("name"));
                item.setNumber(post.optString("number"));
                item.setDtime(post.optString("src_departure_time"));
                item.setAtime(post.optString("dest_arrival_time"));
                item.setTtime(post.optString("travel_time"));
                JSONObject post1 = post.optJSONObject("from");
                item.setFrom(post1.optString("name"));
                JSONObject post2 = post.optJSONObject("to");
                item.setTo(post2.optString("name"));

                JSONArray post3 = post.optJSONArray("classes");
                item.s = new ArrayList<SeatClass>();
                for(int j=0; j< post3.length(); j++) {
                    JSONObject post4 = post3.optJSONObject(j);
                    String a= post4.optString("class-code");
                    String b= post4.optString("available");
                    SeatClass seatClass= new SeatClass(a, b);

                    item.s.add(seatClass);
                    seatClass.print(item.s.get(j));
                }

                JSONArray post5 = post.optJSONArray("days");
                item.d = new ArrayList<DayClass>();
                for(int j=0; j< post5.length(); j++) {
                    JSONObject post6= post5.optJSONObject(j);
                    String a= post6.optString("day-code");
                    String b= post6.optString("runs");
                    DayClass dayClass= new DayClass(a,b);
                    item.d.add(dayClass);
                }


               // item.print(item);
                Train.add(item);
                Log.d("test","extra");

              //  item.print(Train.get(i));
              //Log.d("test", String.valueOf((Train.size())));


//                /*JSONArray attachments = post.getJSONArray("attachments");
//                if (null != attachments && attachments.length() > 0) {
//                    JSONObject attachment = attachments.getJSONObject(0);
//                    if (attachment != null) */
                // item.setImage(attachment.getString("url"));
//            }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}


