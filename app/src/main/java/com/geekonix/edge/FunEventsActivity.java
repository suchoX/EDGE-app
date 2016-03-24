package com.geekonix.edge;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class FunEventsActivity extends AppCompatActivity {

    ImageView paintball,bodyzorbing,archery,lasertag;

    LinearLayout call1,call2;

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_events);

        paintball = (ImageView)findViewById(R.id.paintball_image);
        bodyzorbing = (ImageView)findViewById(R.id.bodyzorbing_image);
        archery = (ImageView)findViewById(R.id.archery_image);
        lasertag = (ImageView)findViewById(R.id.lasertag_image);

        call1 = (LinearLayout)findViewById(R.id.call1_layout);
        call2 = (LinearLayout)findViewById(R.id.call2_layout);

        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "8017174045"));
                startActivity(callIntent);
            }
        });

        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "9804479352"));
                startActivity(callIntent);
            }
        });

        initToolbar();

        Picasso.with(this).load("http://imgur.com/GAldYXh" + ".png").fit().centerCrop().placeholder(R.drawable.temp_header).into(paintball);
        Picasso.with(this).load("http://imgur.com/mQFRscP" + ".png").fit().centerCrop().placeholder(R.drawable.temp_header).into(bodyzorbing);
        Picasso.with(this).load("http://imgur.com/uJgKA5d" + ".png").fit().centerCrop().placeholder(R.drawable.temp_header).into(archery);
        Picasso.with(this).load("http://imgur.com/cL0S9Lf" + ".png").fit().centerCrop().placeholder(R.drawable.temp_header).into(lasertag);

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Fun Events");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
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
}
