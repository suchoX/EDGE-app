package com.geekonix.edge;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class EventContentActivity extends Activity {

    View headerView,contentView;
    ImageView headerImageView, call1View, call2View;
    TextView eventInfoView, eventTimeView, eventDurationView, eventDateView, contactName1View, contactName2View,eventpdfView;
    LinearLayout divider;
    String contactNumber1,contactNumber1Temp="";
    String contactNumber2,contactNumber2Temp="";
    String imageLink;
    int dataResID;
    List<String> eventData;

    String eventName,eventdesc,eventdescTemp="",eventpdf=null,eventname1,eventname1Temp="",eventname2,eventname2Temp="",temp;

    RequestQueue requestQueue;
    String URL,ID;
    int first;

    SharedPreferences dataPreference;
    SharedPreferences.Editor editor;

    String startTime = "NULL", length = "NULL", date = "NULL";
    boolean twoContacts = true;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        dataResID = getIntent().getIntExtra("Event Data", 0);

        //eventpdf = "http://edg.co.in/content/";
        dataPreference = this.getSharedPreferences("EventData", 0);
        editor = dataPreference.edit();

        headerView = getLayoutInflater().inflate(R.layout.event_header, null);
        headerImageView = (ImageView)headerView.findViewById(R.id.image_header);

        contentView = getLayoutInflater().inflate(R.layout.event_scrollview,null);
        eventInfoView = (TextView)contentView.findViewById(R.id.eventcontent_info);
        eventTimeView = (TextView)contentView.findViewById(R.id.eventcontent_time);
        eventDurationView = (TextView)contentView.findViewById(R.id.eventcontent_duration);
        eventDateView = (TextView)contentView.findViewById(R.id.eventcontent_date);
        contactName1View = (TextView)contentView.findViewById(R.id.contact_name1);
        contactName2View = (TextView)contentView.findViewById(R.id.contact_name2);
        eventpdfView = (TextView)contentView.findViewById(R.id.eventcontent_pdf);
        call1View = (ImageView)contentView.findViewById(R.id.contact_call1);
        call2View = (ImageView)contentView.findViewById(R.id.contact_call2);
        divider = (LinearLayout)contentView.findViewById(R.id.divider);

        eventData = Arrays.asList(getResources().getStringArray(dataResID));

        twoContacts = dataPreference.getBoolean("two contacts",true);

        eventdesc = eventData.get(0);
        ID = eventData.get(1);
        URL = "http://edg.co.in/details.php?event_id=" + ID;
        first = dataPreference.getInt("first_"+ID,0);

        eventName = eventData.get(2);
        imageLink = eventData.get(3);
        imageLink = imageLink.concat(".png");
        eventname1 = eventData.get(4);
        contactNumber1 = eventData.get(5);
        eventname2 = eventData.get(6);
        contactNumber2 = eventData.get(7);

        if(first == 0 && isNetworkAvailable())
            progressDialog = ProgressDialog.show(this, "Loading", "Loading Event Information", true);

        else if(first == 0 && !isNetworkAvailable()) {
            putDataUI();
            Toast.makeText(this,"Internet is needed the first time to load updated Event Info",Toast.LENGTH_SHORT).show();
        }

        if(first == 1)
        {
            updateViaSharedPreference();
            putDataUI();
        }
        if(isNetworkAvailable())
        {
            getJSON();
        }

        call1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contactNumber1.equals("NULL"))
                    Toast.makeText(EventContentActivity.this,"The Contact Number is not available now,\nTry Later",Toast.LENGTH_SHORT).show();
                else
                {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + contactNumber1));
                    startActivity(callIntent);
                }
            }
        });

        call2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(contactNumber2.equals("NULL"))
                    Toast.makeText(EventContentActivity.this,"The Contact Number is not available now,\nTry Later",Toast.LENGTH_SHORT).show();
                else
                {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + contactNumber2));
                    startActivity(callIntent);
                }
            }
        });

        eventpdfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eventpdf.charAt(eventpdf.length()-1) != 'f')
                    Toast.makeText(EventContentActivity.this,"The Rules are not available now,\nTry Later",Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://edg.co.in/content/"+eventpdf)));
            }
        });

        Picasso.with(this).load(imageLink).placeholder(R.drawable.temp_header).into(headerImageView);

        FadingActionBarHelper helper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.actionbar_background)
                .headerView(headerView)
                .contentView(contentView);
        setContentView(helper.createView(this));
        helper.initActionBar(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_menu:
                String temp_shr="Check Out the Event " + eventName + " at EDGE, Kolkata's Largest Techno-Management Fest. Download our Official App from \n*Link* \nor go to our website\n http://edg.co.in";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, eventName+" at EDGE");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, temp_shr);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void getJSON()
    {
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Log.d("Volley","Got Response");
                            eventdescTemp = response.getString("desc");
                            eventpdf = response.getString("file");
                            JSONObject jsonObject = response.getJSONObject("contact1");
                            eventname1Temp = jsonObject.getString("name");
                            contactNumber1Temp = jsonObject.getString("phone");
                            if(response.length() >= 5)
                            {
                                jsonObject = response.getJSONObject("contact2");
                                twoContacts = true;
                                editor.putBoolean("two contacts",true);
                                editor.apply();
                                eventname2Temp = jsonObject.getString("name");
                                contactNumber2Temp = jsonObject.getString("phone");
                            }
                            else {
                                editor.putBoolean("two contacts",false);
                                editor.apply();
                            }
                            //startTime = response.getString("start_time");
                            //length = response.getString("length");
                            //date = response.getString("date");
                            editor.putInt("first_"+ID,1);
                            editor.apply();
                            if(first == 0)
                                progressDialog.dismiss();
                            updateAfterJson();
                            putDataUI();
                        }catch(JSONException e)
                        {
                            e.printStackTrace();
                            if(first == 0)
                                progressDialog.dismiss();
                            putDataUI();
                        }
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

    private void updateAfterJson()
    {
        if(eventdescTemp.length() > 2 )
        {
            eventdesc = eventdescTemp;
            editor.putString("desc_"+ID,eventdesc);
            editor.apply();
        }
        if(eventname1Temp.length() > 2 )
        {
            eventname1 = eventname1Temp;
            editor.putString("name1_"+ID,eventname1);
            editor.apply();
        }
        if(eventname2Temp.length() > 2)
        {
            eventname2 = eventname2Temp;
            editor.putString("name2_"+ID,eventname2);
            editor.apply();
        }
        if(contactNumber1Temp.length() > 2) {
            contactNumber1 = contactNumber1Temp;
            editor.putString("number1_"+ID,contactNumber1);
            editor.apply();
        }
        if(contactNumber2Temp.length() > 2)
        {
            contactNumber2 = contactNumber2Temp;
            editor.putString("number2_"+ID,contactNumber2);
            editor.apply();
        }

        editor.putString("pdf_"+ID,eventpdf);
        editor.apply();
        editor.putString("starttime_" + ID, startTime);
        editor.apply();
        editor.putString("length_" + ID, length);
        editor.apply();
        editor.putString("date_" + ID, date);
        editor.apply();
    }

    private void updateViaSharedPreference()
    {
        eventdesc = dataPreference.getString("desc_"+ID,eventdesc);
        eventname1 = dataPreference.getString("name1_"+ID,eventname1);
        eventname2 = dataPreference.getString("name2_"+ID,eventname2);
        contactNumber1 = dataPreference.getString("number1_"+ID,contactNumber1);
        contactNumber2 = dataPreference.getString("number2_"+ID,contactNumber2);
        eventpdf = dataPreference.getString("pdf_"+ID,eventpdf);
        startTime = dataPreference.getString("starttime_"+ID,startTime);
        length = dataPreference.getString("length_"+ID,startTime);
    }

    private void putDataUI()
    {
        if(eventdesc.equals("NULL"))
            eventInfoView.setText(getResources().getText(R.string.updated_soon_desc));
        else
            eventInfoView.setText(eventdesc);

        if(startTime.equals("NULL"))
            eventTimeView.setText("Start: " + getResources().getText(R.string.updated_soon_others));
        else
            eventTimeView.setText("Start: " + startTime);

        if(length.equals("NULL"))
            eventDurationView.setText("Duration: " + getResources().getText(R.string.updated_soon_others));
        else
            eventDurationView.setText("Duration: " + length);

        if(date.equals("NULL"))
            eventDateView.setText(getResources().getText(R.string.updated_soon_others));
        else
            eventDateView.setText(date + " April");

        if(eventname1.equals("NULL"))
            contactName1View.setText(getResources().getText(R.string.updated_soon_others));
        else
            contactName1View.setText(eventname1);

        if(twoContacts)
        {
            if (eventname2.equals("NULL"))
                contactName2View.setText(getResources().getText(R.string.updated_soon_others));
            else
                contactName2View.setText(eventname2);
        }
        else
        {
            contactName2View.setVisibility(View.GONE);
            call2View.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
        }
    }
}
