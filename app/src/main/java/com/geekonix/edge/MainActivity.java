package com.geekonix.edge;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
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

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
{
    private FragmentManager fragmentManager;

    RelativeLayout eventsLayout,megaeventsLayout,campusambassadorLayout,teamLayout,sponsorsLayout;
    Toolbar mToolbar;
    TextView mToolBarTextView;
    ImageView fabMain;

    FloatingActionButton buttonMain;

    boolean menuOpen=false;

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

        initFab();

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initFab()
    {
        fabMain = new ImageView(this);
        fabMain.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab_main));

        buttonMain = new FloatingActionButton.Builder(this)
                .setContentView(fabMain)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView fabFacebook = new ImageView(this);
        fabFacebook.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab_facebook));
        SubActionButton buttonFacebook = itemBuilder.setContentView(fabFacebook).build();
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        ImageView fabYoutube = new ImageView(this);
        fabFacebook.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab_youtube));
        SubActionButton buttonYoutube = itemBuilder.setContentView(fabYoutube).build();
        buttonYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/channel/UCSwFemGqe1XRmVlg1jRNJYw"));
                startActivity(intent);
            }
        });

        ImageView fabTwitter = new ImageView(this);
        fabFacebook.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab_twitter));
        SubActionButton buttonTwitter = itemBuilder.setContentView(fabTwitter).build();
        buttonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/geekonixedge")));
            }
        });

        ImageView fabInstagram = new ImageView(this);
        fabFacebook.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab_instagram));
        SubActionButton buttonInstagram = itemBuilder.setContentView(fabInstagram).build();
        buttonInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/geekonix");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/geekonix")));
                }
            }
        });

        ImageView fabWeb = new ImageView(this);
        fabFacebook.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab_web));
        SubActionButton buttonWeb = itemBuilder.setContentView(fabWeb).build();
        buttonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://edg.co.in")));
            }
        });

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonFacebook)
                .addSubActionView(buttonYoutube)
                .addSubActionView(buttonTwitter)
                .addSubActionView(buttonInstagram)
                .addSubActionView(buttonWeb)
                .attachTo(buttonMain)
                .build();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolBarTextView.setText("EDGE X");
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
