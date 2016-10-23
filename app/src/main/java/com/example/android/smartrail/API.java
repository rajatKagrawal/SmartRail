package com.example.android.smartrail;

import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by pooja on 23/10/16.
 */
public class API {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String FEED_URL1 = "http://api.railwayapi.com/between/source/";
    private String FEED_URL2 = "/dest/";
    private String FEED_URL3 = "/date/";
    private String FEED_URL4 = "/apikey/5punodlf";
    private String URL;
    ArrayList<SeatClass> s;

    String train_no, st1, st2, date;

    public API(String train_no, String st1, String st2, String date) {
        this.train_no = train_no;
        this.date = date;
        this.st1 = st1;
        this.st2 = st2;
    }


    public ArrayList<SeatClass> exe() {
        URL = FEED_URL1 + st1 + FEED_URL2 + st2 + FEED_URL3 + date + FEED_URL4;
        Log.d(TAG, URL);
        ArrayList<SeatClass> s= new ArrayList<>();
        new AsyncHttpTask().execute(URL);
        return null;
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, ArrayList<SeatClass>> {

        @Override
        protected ArrayList<SeatClass> doInBackground(String... params) {
            Integer result = 0;
            try {
                // Create Apache HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse = httpclient.execute(new HttpGet(params[0]));
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    String response = streamToString(httpResponse.getEntity().getContent());
                    s = new ArrayList<SeatClass>();
                    s = parseResult(response);
                   // result = 1; // Successful
                } else {
                    result = 0; //"Failed
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return s;
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
    private ArrayList<SeatClass> parseResult(String result) {
        try {
            Log.d(TAG, result);
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("train");

            for (int i1 = 0; i1 < posts.length(); i1++) {
                JSONObject post = posts.optJSONObject(i1);

                JSONArray post3 = post.optJSONArray("classes");
                s = new ArrayList<SeatClass>();
                for (int j = 0; j < post3.length(); j++) {
                    JSONObject post4 = post3.optJSONObject(j);
                    String a = post4.optString("class-code");
                    String b = post4.optString("available");
                    SeatClass seatClass = new SeatClass(a, b);
                    s.add(seatClass);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }
}