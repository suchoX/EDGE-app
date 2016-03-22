package com.geekonix.edge;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Locale;


public class MainActivity extends AppCompatActivity
{
    private FragmentManager fragmentManager;

    RelativeLayout eventsLayout,megaeventsLayout,campusambassadorLayout,teamLayout,sponsorsLayout;
    Toolbar mToolbar;
    TextView mToolBarTextView;

    Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();



        eventsLayout = (RelativeLayout)findViewById(R.id.event_layout);
        megaeventsLayout = (RelativeLayout)findViewById(R.id.megaevent_layout);
        campusambassadorLayout = (RelativeLayout)findViewById(R.id.campusambassador_layout);
        teamLayout = (RelativeLayout)findViewById(R.id.team_layout);
        sponsorsLayout = (RelativeLayout)findViewById(R.id.sponsor_layout);

        eventsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });

        sponsorsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UpcomingActivity.class));
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
                if (isNetworkAvailable()) {
                    Intent intent = new Intent(MainActivity.this, WebviewActivity.class);
                    intent.putExtra("Heading", "Campus Ambassador");
                    intent.putExtra("URL", "https://docs.google.com/forms/d/1fYtuK08jRcSTFwK1EIo3SiSsjx9QBjhfjLtj_kXYI_Y/viewform");
                    startActivity(intent);
                } else
                    Toast.makeText(MainActivity.this, "We need Internet for Registration", Toast.LENGTH_SHORT).show();
            }
        });

        initToolbar();
        initDrawer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        mToolBarTextView.setText("EDGE X");
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
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.logo_edge)
                .withHeaderBackgroundScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(header)
                .addDrawerItems(faceboook, youtube, twitter, instagram, website, new DividerDrawerItem(),location,accomodation,aboutus)
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
                                        startActivity(new Intent(Intent.ACTION_VIEW, uri));;
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
