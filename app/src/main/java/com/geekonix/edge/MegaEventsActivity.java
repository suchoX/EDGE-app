package com.geekonix.edge;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class MegaEventsActivity extends AppCompatActivity {

    Toolbar mToolbar;
    LinearLayout call1,call2;
    ImageView mega1,mega2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mega_events);
        initToolbar();

        call1 = (LinearLayout)findViewById(R.id.call1_layout);
        call2 = (LinearLayout)findViewById(R.id.call2_layout);

        mega1 = (ImageView)findViewById(R.id.megaevent_photo1);
        mega2 = (ImageView)findViewById(R.id.megaevent_photo2);
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "9903091208"));
                startActivity(callIntent);
            }
        });

        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "8017174045"));
                startActivity(callIntent);
            }
        });
        Picasso.with(this).load(R.drawable.edgnight).into(mega1);
        Picasso.with(this).load(R.drawable.edgtalk).into(mega2);
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
