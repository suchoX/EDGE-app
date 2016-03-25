package com.geekonix.edge;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MegaEventsActivity extends AppCompatActivity {

    Toolbar mToolbar;
    RequestQueue requestQueue;
    String URL1, URL2;

    String eventdescN, eventdescTempN,imagelinkN,imagelinkTempN;
    String eventdescT, eventdescTempT,imagelinkT,imagelinkTempT;

    ImageView imageN,imageT;
    TextView descN,descT;

    SharedPreferences dataPreference;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mega_events);
        initToolbar();
        URL1 = "http://edg.co.in/details.php?event_id=50";
        URL2 = "http://edg.co.in/details.php?event_id=51";

        dataPreference = this.getSharedPreferences("MegaEventData", 0);
        editor = dataPreference.edit();

        eventdescN = dataPreference.getString("EDG Nights Desc", "NULL");
        imagelinkN = dataPreference.getString("EDG Nights Image", "NULL");

        eventdescT = dataPreference.getString("EDG Talks Desc", "NULL");
        imagelinkT = dataPreference.getString("EDG Talks Image", "NULL");

        imageN = (ImageView)findViewById(R.id.megaevent_photo1);
        imageT = (ImageView)findViewById(R.id.megaevent_photo2);

        descN  = (TextView)findViewById(R.id.megaevent_desc1);
        descT  = (TextView)findViewById(R.id.megaevent_desc2);

        edgNightsJSON();
        edgTalksJSON();

        requestQueue.start();

        putDataUIN();
        putDataUIT();

    }

    private void edgNightsJSON()
    {
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, URL1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Volley", "Got Response");
                        try {
                            eventdescTempN = response.getString("desc");
                            editor.putString("EDG Nights Desc",eventdescTempN);
                            editor.apply();
                            eventdescN = eventdescTempN;
                        }catch(JSONException e)
                        {
                            eventdescTempN = "NULL";
                        }
                        try{
                            imagelinkTempN = response.getString("file");
                            editor.putString("EDG Nights Image",imagelinkTempN);
                            editor.apply();
                            imagelinkN = imagelinkTempN;
                        }catch(JSONException e)
                        {
                            imagelinkTempN = "NULL";
                        }
                        putDataUIN();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("Volley", "Error");
                        eventdescTempN = "NULL";
                        imagelinkTempN = "NULL";
                    }
                }
        );
        requestQueue.add(jor);
    }

    private void edgTalksJSON()
    {
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, URL2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Volley", "Got Response");
                        try {
                            eventdescTempT = response.getString("desc");
                            editor.putString("EDG Talks Desc",eventdescTempT);
                            editor.apply();
                            eventdescT = eventdescTempT;
                        }catch(JSONException e)
                        {
                            eventdescTempT = "NULL";
                        }
                        try{
                            imagelinkTempT = response.getString("file");
                            editor.putString("EDG Talks Image",imagelinkTempT);
                            editor.apply();
                            imagelinkT = imagelinkTempT;
                        }catch(JSONException e)
                        {
                            imagelinkTempT = "NULL";
                        }
                        putDataUIT();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("Volley", "Error");
                        eventdescTempT = "NULL";
                        imagelinkTempT = "NULL";
                    }
                }
        );
        requestQueue.add(jor);
    }

    private void putDataUIN()
    {
        if(imagelinkN != "NULL")
            Picasso.with(this).load("http://edg.co.in/"+imagelinkN).fit().centerCrop().placeholder(R.drawable.main_megaevents).into(imageN);
        if(eventdescN != "NULL")
            descN.setText(eventdescN);
    }

    private void putDataUIT()
    {
        if(imagelinkT != "NULL")
            Picasso.with(this).load("http://edg.co.in/"+imagelinkT).fit().centerCrop().placeholder(R.drawable.main_megaevents).into(imageT);
        if(eventdescT != "NULL")
            descT.setText(eventdescT);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Mega Events");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
