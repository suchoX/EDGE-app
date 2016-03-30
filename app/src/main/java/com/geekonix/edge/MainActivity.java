package com.geekonix.edge;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;


public class MainActivity extends AppCompatActivity
{

    RelativeLayout eventsLayout,megaeventsLayout,funeventsLayout,campusambassadorLayout,teamLayout, sponsorsLayout;
    Toolbar mToolbar;

    Drawer drawer;

    SharedPreferences schedulePreference;
    SharedPreferences.Editor editor;

    SharedPreferences infoPreference;
    SharedPreferences.Editor editor2;

    String scheduleLink,URL;
    Long scheduleDate, scheduleDatePresent;
    RequestQueue requestQueue;
    SecondaryDrawerItem schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventsLayout = (RelativeLayout)findViewById(R.id.event_layout);
        megaeventsLayout = (RelativeLayout)findViewById(R.id.megaevent_layout);
        campusambassadorLayout = (RelativeLayout)findViewById(R.id.campusambassador_layout);
        teamLayout = (RelativeLayout)findViewById(R.id.team_layout);
        funeventsLayout = (RelativeLayout)findViewById(R.id.funevent_layout);
        sponsorsLayout = (RelativeLayout)findViewById(R.id.sponsors_layout);

        schedulePreference = this.getSharedPreferences("ScheduleData", 0);
        editor = schedulePreference.edit();
        scheduleLink = schedulePreference.getString("Schedule Link", "false");
        scheduleDate = schedulePreference.getLong("Schedule Date", 0);
        URL = "http://edg.co.in/details.php?event_id=2";
        //URL = "https://api.myjson.com/bins/31mri";
        //URL = "https://api.myjson.com/bins/1ju1a";

        infoPreference = this.getSharedPreferences("Info", 0);
        editor2 = infoPreference.edit();
        if(!infoPreference.getBoolean("Info Shown",false)) {
            Toast.makeText(this, "The Images in the app are downloaded only once. Once downloaded, the images are loaded from memory", Toast.LENGTH_LONG).show();
            editor2.putBoolean("Info Shown",true);
            editor2.apply();
        }

        initToolbar();
        initDrawer();

        if (isNetworkAvailable())
            getScheduleJSON();

        eventsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });

        megaeventsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MegaEventsActivity.class);
                startActivity(intent);
            }
        });

        funeventsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FunEventsActivity.class);
                startActivity(intent);
            }
        });


        teamLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TeamActivity.class));
            }
        });

        campusambassadorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Campus Ambassador");
                alert.setMessage(" What we expect from our Campus Ambassador?\n" +
                        "- Should be responsible enough to handle the pressure of this grand endeavor.\n" +
                        "- Good communication skills.\n" +
                        "- Must have a good network in college to be able to promote and publicize EDGE X 2016 throughout your college.\n" +
                        "- Should be an active member of any student committee.\n" +
                        "\n" +
                        "Benefits for our Campus Ambassador?\n" +
                        "- Certificate of appreciation from team Geekonix.\n" +
                        "- A chance to win exciting goodies.\n" +
                        "- A chance to gain free entry in our grand festival, EDGE X 2016.\n" +
                        "- Gather experience on professional level and enhance your skills in leadership, marketing, convincing, communication, social media marketing and team work!" +
                        "\n\n");
                alert.setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isNetworkAvailable()) {
                            Intent intent = new Intent(MainActivity.this, WebviewActivity.class);
                            intent.putExtra("Heading", "Campus Ambassador");
                            intent.putExtra("URL", "https://docs.google.com/forms/d/1fYtuK08jRcSTFwK1EIo3SiSsjx9QBjhfjLtj_kXYI_Y/viewform");
                            startActivity(intent);
                        } else
                            Toast.makeText(MainActivity.this, "We need Internet for Registration", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        });

        sponsorsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SponsorsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getScheduleJSON()
    {
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            scheduleLink = response.getString("schedule");
                            editor.putString("Schedule Link",scheduleLink);
                            editor.apply();
                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        try {
                            scheduleDatePresent = response.getLong("last_updated");
                            if(scheduleDatePresent > scheduleDate && !scheduleLink.equals("false"))
                            {
                                schedule.withBadge("Updated");
                                Toast.makeText(MainActivity.this,"Schedule has been updated\nPlease download new Schedule",Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", error.getMessage());
                    }
                }
        );
        requestQueue.add(jor);
        requestQueue.start();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Edge X");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    private void initDrawer()
    {
        SecondaryDrawerItem faceboook = new SecondaryDrawerItem().withName(R.string.facebook).withIcon(R.drawable.fab_facebook).withIdentifier(1);
        SecondaryDrawerItem youtube = new SecondaryDrawerItem().withName(R.string.youtube).withIcon(R.drawable.fab_youtube).withIdentifier(2);
        SecondaryDrawerItem twitter = new SecondaryDrawerItem().withName(R.string.twitter).withIcon(R.drawable.fab_twitter).withIdentifier(3);
        SecondaryDrawerItem instagram = new SecondaryDrawerItem().withName(R.string.instagram).withIcon(R.drawable.fab_instagram).withIdentifier(4);
        SecondaryDrawerItem website = new SecondaryDrawerItem().withName(R.string.website).withIcon(R.drawable.fab_web).withIdentifier(5);
        SecondaryDrawerItem location = new SecondaryDrawerItem().withName(R.string.location).withIcon(R.drawable.icn_location).withIdentifier(6);
        SecondaryDrawerItem accomodation = new SecondaryDrawerItem().withName(R.string.accomodation).withIcon(R.drawable.icn_accomodation).withIdentifier(7);
        SecondaryDrawerItem aboutus = new SecondaryDrawerItem().withName(R.string.aboutus).withIcon(R.drawable.icn_aboutus).withIdentifier(8);
        schedule = new SecondaryDrawerItem().withName(R.string.schedule).withIcon(R.drawable.icn_schedule).withIdentifier(9);
        SecondaryDrawerItem registration = new SecondaryDrawerItem().withName(R.string.registration).withIcon(R.drawable.icn_registrationdetails).withIdentifier(10);
        SecondaryDrawerItem feedback = new SecondaryDrawerItem().withName(R.string.feedback).withIcon(R.drawable.icn_feedback).withIdentifier(11);
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.temp_header)
                .withHeaderBackgroundScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(header)
                .addDrawerItems(faceboook, youtube, twitter, instagram, website, new DividerDrawerItem(),schedule,registration,location,accomodation,feedback,aboutus)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem)
                    {
                        if(drawerItem!=null)
                        {
                            if(drawerItem.getIdentifier() == 1)
                            {
                                String facebookUrl = "https://www.facebook.com/Gx.Edg";
                                try {
                                    int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                                    if (versionCode >= 3002850) {
                                        Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                                    } else {
                                        // open the Facebook app using the old method (fb://profile/id or fb://page/id)
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/336227679757310")));
                                    }
                                } catch (PackageManager.NameNotFoundException e) {
                                    // Facebook is not installed. Open the browser
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
                                }
                            }
                            else if(drawerItem.getIdentifier() == 2)
                            {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("https://www.youtube.com/channel/UCSwFemGqe1XRmVlg1jRNJYw"));
                                startActivity(intent);
                            }
                            else if(drawerItem.getIdentifier() == 3)
                            {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/geekonixedge")));
                            }
                            else if(drawerItem.getIdentifier() == 4)
                            {
                                Uri uri = Uri.parse("http://instagram.com/_u/geekonix");
                                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                                likeIng.setPackage("com.instagram.android");

                                try {
                                    startActivity(likeIng);
                                } catch (ActivityNotFoundException e) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/geekonix")));
                                }
                            }
                            else if(drawerItem.getIdentifier() == 5)
                            {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://edg.co.in")));
                            }
                            else if(drawerItem.getIdentifier() == 6)
                            {
                                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 22.5749326, 88.4330177);
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                startActivity(intent);
                            }
                            else if(drawerItem.getIdentifier() == 7)
                            {
                                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                                alert.setTitle("Accomodation");
                                alert.setMessage("Accomodation is being made available for participants from outside Kolkata, on a first-come-first-serve basis. If you want to avail accomodation please fill up the form by clicking \"OK\".\n" +
                                        "If you have any questions regarding the same, please contact the undersigned.\n\n\n" +
                                        "Mayank Choudhary : 8443823443\n");
                                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (isNetworkAvailable()) {
                                            Intent intent = new Intent(MainActivity.this, WebviewActivity.class);
                                            intent.putExtra("Heading", "Accomodation");
                                            intent.putExtra("URL", "https://docs.google.com/forms/d/1KJ7vrl-v2a62euWmmuEHHaQFbuR5OXNy2w1hY2F8rxQ/viewform");
                                            startActivity(intent);
                                        } else
                                            Toast.makeText(MainActivity.this, "We need Internet for Registration", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                alert.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alert.show();
                            }
                            else if(drawerItem.getIdentifier() == 8)
                            {
                                startActivity(new Intent(MainActivity.this,AboutUsActivity.class));
                            }
                            else if(drawerItem.getIdentifier() == 9)
                            {
                                if(isNetworkAvailable())
                                {
                                    if(scheduleLink.equals("false"))
                                    {
                                        Toast.makeText(MainActivity.this,"The Schedule is not available now,\nPlease Try Later",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        if (scheduleDatePresent > scheduleDate) {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://edg.co.in/content/pdfs/schedule.pdf")));
                                            editor.putLong("Schedule Date",scheduleDatePresent);
                                            editor.apply();
                                            scheduleDate = scheduleDatePresent;
                                        } else {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                                            alert.setTitle("Download Schedule");
                                            alert.setMessage("You already have downloaded the latest schedule\n\nDownload Again?");
                                            alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://edg.co.in/content/pdfs/schedule.pdf")));
                                                    editor.putLong("Schedule Date",scheduleDatePresent);
                                                    editor.apply();
                                                    scheduleDate = scheduleDatePresent;
                                                }
                                            });
                                            alert.setNegativeButton("NO",new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            });
                                            alert.show();
                                        }
                                    }

                                }
                                else
                                    Toast.makeText(MainActivity.this,"Internet Connection is required to download Schedule",Toast.LENGTH_SHORT).show();

                            }
                            else if(drawerItem.getIdentifier() == 10)
                            {
                                if(isNetworkAvailable())
                                {
                                    Intent intent = new Intent(MainActivity.this, WebviewActivity.class);
                                    intent.putExtra("Heading", "Registration Details");
                                    intent.putExtra("URL", "http://edg.co.in/register.php");
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(MainActivity.this,"Internet Connection is required to view Registration Details",Toast.LENGTH_SHORT).show();
                            }
                            else if(drawerItem.getIdentifier() == 11)
                            {
                                if(isNetworkAvailable())
                                {
                                    Intent intent = new Intent(MainActivity.this, WebviewActivity.class);
                                    intent.putExtra("Heading", "Feedback");
                                    intent.putExtra("URL", "https://docs.google.com/forms/d/1SZHYXp2uzL1qZmxAWSOfS29BeUaYxBBpvIXKTohQ06E/viewform?c=0&w=1");
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(MainActivity.this,"Internet Connection is required to give Feedback",Toast.LENGTH_SHORT).show();
                            }
                        }
                        return false;
                    }
                })
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.share_menu:
                String temp_shr="Check out the official app for EDGE, Kolkata's Largest Techno-Management Fest. Download the app and get Live Updates and updates from us!\nhttp://play.google.com/store/apps/details?id=com.geekonix.edge";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Official EDGE App");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, temp_shr);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
