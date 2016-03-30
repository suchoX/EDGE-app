package com.geekonix.edge;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.geekonix.edge.adapters.TeamListAdapter;
import com.geekonix.edge.others.SponsorObjects;
import com.geekonix.edge.others.TeamObjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SponsorsActivity extends AppCompatActivity {

    Toolbar mToolbar;
    private StaggeredGridLayoutManager gridLayoutManager;

    RequestQueue requestQueue;
    String URL;

    List<SponsorObjects> sponsorList;
    SponsorListAdapter rcAdapter;

    SharedPreferences sponsorPreference;
    SharedPreferences.Editor editor;
    boolean first;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        initToolbar();

        URL = "http://edg.co.in/getsponsor.php";
        requestQueue = Volley.newRequestQueue(this);

        sponsorPreference = this.getSharedPreferences("Sponsor List", 0);
        editor = sponsorPreference.edit();

        first = sponsorPreference.getBoolean("First",true);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        sponsorList = new ArrayList<SponsorObjects>();
        if(!first)
        {
            num = sponsorPreference.getInt("Number of Sponsors",0);
            for(int i=0 ; i<num ; i++)
                sponsorList.add(new SponsorObjects(sponsorPreference.getString("Sponsor Name"+i,""),sponsorPreference.getString("Sponsor Image"+i,""),sponsorPreference.getString("Sponsor url"+i,"0")));
        }
        getSponsors();

        rcAdapter = new SponsorListAdapter(this, sponsorList);
        recyclerView.setAdapter(rcAdapter);
    }

    private void getSponsors()
    {
        JsonArrayRequest jor = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        editor.putInt("Number of Sponsors",response.length());
                        editor.apply();
                        sponsorList.clear();
                        for(int i=0 ; i<response.length() ; i++)
                        {
                            try {
                                JSONObject temp = response.getJSONObject(i);
                                sponsorList.add(new SponsorObjects(temp.getString("title"), temp.getString("logo_url"),temp.getString("url")));
                                editor.putString("Sponsor Name" + i, temp.getString("title"));
                                editor.apply();
                                editor.putString("Sponsor Image" + i, temp.getString("logo_url"));
                                editor.apply();
                                editor.putString("Sponsor url"+i, temp.getString("url"));
                                editor.apply();

                                //editor.putBoolean("First",false);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        editor.putBoolean("First",false);
                        editor.apply();
                        rcAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("Volley", "Error");

                    }
                }
        );
        requestQueue.add(jor);
        requestQueue.start();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Sponsors");
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
