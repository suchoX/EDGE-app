package com.geekonix.edge;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.squareup.picasso.Picasso;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;

import java.util.Arrays;
import java.util.List;

public class EventContentActivity extends Activity {

    LayoutInflater inflater;
    View headerView,contentView;
    ImageView headerImage, facebook1, facebook2, call1, call2;
    TextView eventInfo, eventTime, eventDuration, contactName1, contactName2;
    String facebookLink1, contactNumber1;
    String facebookLink2, contactNumber2;
    List<String> eventData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        headerView = getLayoutInflater().inflate(R.layout.event_header,null);
        headerImage = (ImageView)headerView.findViewById(R.id.image_header);

        contentView = getLayoutInflater().inflate(R.layout.event_scrollview,null);
        eventInfo = (TextView)contentView.findViewById(R.id.eventcontent_info);
        eventTime = (TextView)contentView.findViewById(R.id.eventcontent_time);
        eventDuration = (TextView)contentView.findViewById(R.id.eventcontent_duration);
        contactName1 = (TextView)contentView.findViewById(R.id.contact_name1);
        contactName2 = (TextView)contentView.findViewById(R.id.contact_name2);

        eventData = Arrays.asList(getResources().getStringArray(R.array.flawless_data));

        eventInfo.setText(eventData.get(0));
        contactName1.setText(eventData.get(1));
        contactNumber1 = eventData.get(2);
        contactNumber1 = "tel:" + contactNumber1;
        facebookLink1 = eventData.get(3);

        contactName2.setText(eventData.get(4));
        contactNumber2 = eventData.get(5);
        contactNumber2= "tel:" + contactNumber2;
        facebookLink2 = eventData.get(6);

        facebook1 = (ImageView)contentView.findViewById(R.id.contact_facebook1);
        facebook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facebookUrl = facebookLink1;
                try {
                    int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                    if (versionCode >= 3002850) {
                        Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                        ;
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
        call1 = (ImageView)contentView.findViewById(R.id.contact_call1);
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(contactNumber1));
                startActivity(callIntent);
            }
        });

        facebook2 = (ImageView)contentView.findViewById(R.id.contact_facebook2);
        facebook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String facebookUrl = facebookLink2;
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
        call2 = (ImageView)contentView.findViewById(R.id.contact_call2);
        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(contactNumber2));
                startActivity(callIntent);
            }
        });

        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").placeholder(R.drawable.temp_header).into(headerImage);

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
                String temp_shr="Check Out the Event *Event Name* at EDGE, Kolkata's Largest Techno-Management Fest. Download our Official App from \n*Link* \nor go to our website\n edg.co.in";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "*Event Name* at EDGE");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, temp_shr);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
