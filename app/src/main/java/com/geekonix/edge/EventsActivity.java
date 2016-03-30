package com.geekonix.edge;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekonix.edge.adapters.EventListAdapter;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class EventsActivity extends AppCompatActivity{

    Toolbar mToolbar;

    LinearLayout tempLayout;

    ListView eventsList;
    EventListAdapter eventListAdapter;
    List<String> computeaidEvents,roboticsEvents,foodforfunEvents,cybercrusadeEvents,moneymattersEvents,newronEvents,infocusEvents,createitEvents,justlikethatEvents,innovatiEvents,elevationEvents;
    List<String> presentList;

    int category = 0;

    Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventsList = (ListView)findViewById(R.id.event_list);
        tempLayout = (LinearLayout)findViewById(R.id.temp_layout);

        computeaidEvents = Arrays.asList(getResources().getStringArray(R.array.computeaid_events));
        roboticsEvents = Arrays.asList(getResources().getStringArray(R.array.robotics_events));
        foodforfunEvents = Arrays.asList(getResources().getStringArray(R.array.foodforfun_events));
        cybercrusadeEvents = Arrays.asList(getResources().getStringArray(R.array.cybercrusade_events));
        moneymattersEvents = Arrays.asList(getResources().getStringArray(R.array.moneymatters_events));
        newronEvents = Arrays.asList(getResources().getStringArray(R.array.newron_events));
        infocusEvents = Arrays.asList(getResources().getStringArray(R.array.infocus_events));
        createitEvents = Arrays.asList(getResources().getStringArray(R.array.createit_events));
        justlikethatEvents = Arrays.asList(getResources().getStringArray(R.array.justlikethat_events));
        innovatiEvents = Arrays.asList(getResources().getStringArray(R.array.innovati_events));
        elevationEvents = Arrays.asList(getResources().getStringArray(R.array.elevation_events));

        presentList = computeaidEvents;

        eventListAdapter = new EventListAdapter(this,presentList,0);
        eventsList.setAdapter(eventListAdapter);

        initToolbar();
        initDrawer();
    }

    private void initDrawer()
    {
        SecondaryDrawerItem computeaid = new SecondaryDrawerItem().withName(R.string.compute_aid).withIcon(R.drawable.icn_computeaid).withIdentifier(1);
        SecondaryDrawerItem robotics = new SecondaryDrawerItem().withName(R.string.robotics).withIcon(R.drawable.icn_robotics).withIdentifier(2);
        SecondaryDrawerItem cybercrusade = new SecondaryDrawerItem().withName(R.string.cyber_crusade).withIcon(R.drawable.icn_cybercrusade).withIdentifier(3);
        SecondaryDrawerItem foodforfun = new SecondaryDrawerItem().withName(R.string.food_for_fun).withIcon(R.drawable.icn_foodforfun).withIdentifier(4);
        SecondaryDrawerItem moneymatters = new SecondaryDrawerItem().withName(R.string.money_matters).withIcon(R.drawable.icn_moneymatters).withIdentifier(5);
        SecondaryDrawerItem infocus = new SecondaryDrawerItem().withName(R.string.infocus).withIcon(R.drawable.icn_infocus).withIdentifier(6);
        SecondaryDrawerItem newron = new SecondaryDrawerItem().withName(R.string.newron).withIcon(R.drawable.icn_newron).withIdentifier(7);
        SecondaryDrawerItem innovati = new SecondaryDrawerItem().withName(R.string.innovati).withIcon(R.drawable.icn_innovati).withIdentifier(8);
        SecondaryDrawerItem createit = new SecondaryDrawerItem().withName(R.string.create_it).withIcon(R.drawable.icn_createit).withIdentifier(9);
        SecondaryDrawerItem justlikethat = new SecondaryDrawerItem().withName(R.string.just_like_that).withIcon(R.drawable.icn_justlikethat).withIdentifier(10);
        SecondaryDrawerItem elevation = new SecondaryDrawerItem().withName(R.string.elevation).withIcon(R.drawable.icn_elevation).withIdentifier(11);


        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.temp_header)
                .withHeaderBackgroundScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(header)
                .addDrawerItems(computeaid, robotics, cybercrusade, foodforfun, moneymatters, infocus, newron, innovati, createit, justlikethat, elevation)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null)
                        {
                            tempLayout.setVisibility(View.GONE);
                            eventsList.setVisibility(View.VISIBLE);
                            changetoolbartitle((int)drawerItem.getIdentifier());
                            reinitList((int)drawerItem.getIdentifier());
                        }
                        return false;
                    }
                })
                .build();
        drawer.openDrawer();
            }

    private void reinitList(int position)
    {
        switch (position)
        {
            case 1:
                presentList = null;
                presentList = computeaidEvents;
                category = 0;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,0);
                eventsList.setAdapter(eventListAdapter);
                break;
            case 2:
                presentList = null;
                presentList = roboticsEvents;
                category = 1;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,1);
                eventsList.setAdapter(eventListAdapter);
                break;
            case 3:
                presentList = null;
                presentList = cybercrusadeEvents;
                category = 2;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,2);
                eventsList.setAdapter(eventListAdapter);
                break;
            case 4:
                presentList = null;
                presentList = foodforfunEvents;
                category = 3;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,3);
                eventsList.setAdapter(eventListAdapter);
                break;
            case 5:
                presentList = null;
                presentList = moneymattersEvents;
                category = 4;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,4);
                eventsList.setAdapter(eventListAdapter);
                break;
            case 6:
                presentList = null;
                presentList = infocusEvents;
                category = 5;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,5);
                eventsList.setAdapter(eventListAdapter);
                break;
            case 7:
                presentList = null;
                presentList = newronEvents;
                category = 6;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,6);
                eventsList.setAdapter(eventListAdapter);
                break;
            case 8:
                presentList = null;
                presentList = innovatiEvents;
                category = 7;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,7);
                eventsList.setAdapter(eventListAdapter);
                break;
            case 9:
                presentList = null;
                presentList = createitEvents;
                category = 8;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,8);
                eventsList.setAdapter(eventListAdapter);
                break;
            case 10:
                presentList = null;
                presentList = justlikethatEvents;
                category = 9;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,9);
                eventsList.setAdapter(eventListAdapter);
                break;

            case 11:
                presentList = null;
                presentList = elevationEvents;
                category = 10;

                eventsList.setAdapter(null);
                eventListAdapter = new EventListAdapter(this,presentList,10);
                eventsList.setAdapter(eventListAdapter);
                break;
        }
    }


    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Events");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    private void changetoolbartitle(int position)
    {
        switch(position)
        {
            case 1: getSupportActionBar().setTitle(R.string.compute_aid);
                    break;
            case 2: getSupportActionBar().setTitle(R.string.robotics);
                    break;
            case 3: getSupportActionBar().setTitle(R.string.cyber_crusade);
                    break;
            case 4: getSupportActionBar().setTitle(R.string.food_for_fun);
                    break;
            case 5: getSupportActionBar().setTitle(R.string.money_matters);
                    break;
            case 6: getSupportActionBar().setTitle(R.string.infocus);
                    break;
            case 7: getSupportActionBar().setTitle(R.string.newron);
                    break;
            case 8: getSupportActionBar().setTitle(R.string.innovati);
                    break;
            case 9: getSupportActionBar().setTitle(R.string.create_it);
                    break;
            case 10: getSupportActionBar().setTitle(R.string.just_like_that);
                    break;
            case 11: getSupportActionBar().setTitle(R.string.elevation);
                    break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_events, menu);
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

    public void eventSelected(int event)
    {
        int resource = getEvent(event);
        Intent intent = new Intent(EventsActivity.this, EventContentActivity.class);
        intent.putExtra("Event Data",resource);
        startActivity(intent);
    }

    private int getEvent(int event)
    {
        switch (category)
        {
            case 0:
                switch (event)
                {
                    case 0: return R.array.flawless_data;
                    case 1: return R.array.bughunt_data;
                    case 2: return R.array.codemart_data;
                    case 3: return R.array.cryptoquest_data;
                    case 4: return R.array.gameofzones_data;
                    case 5: return R.array.codeout_data;
                }
                break;
            case 1:
                switch (event)
                {
                    case 0: return R.array.xpedition_data;
                    case 1: return R.array.xoccer_data;
                    case 2: return R.array.xport_data;
                    case 3: return R.array.blitzkriegx_data;
                    case 4: return R.array.xcelsior_data;
                    case 5: return R.array.xult_data;
                    case 6: return R.array.aerostrix_data;
                    case 7: return R.array.xplore_data;
                    case 8: return R.array.perplexity_data;
                    case 9: return R.array.xtreat_data;
                }
                break;
            case 2:
                switch (event)
                {
                    case 0: return R.array.cs_data;
                    case 1: return R.array.cspro_data;
                    case 2: return R.array.mortalkombat_data;
                    case 3: return R.array.dota_data;
                    case 4: return R.array.fifa15_data;
                    case 5: return R.array.fifa11_data;
                    case 6: return R.array.nfs_data;
                }
                break;
            case 3:
                switch (event)
                {
                    case 0: return R.array.xquizit_data;
                    case 1: return R.array.foodrelay_data;
                    case 2: return R.array.creationxnihilo_data;
                    case 3: return R.array.cryptolabel_data;
                    case 4: return R.array.casestudy_data;
                    case 5: return R.array.kwiznet_data;
                }
                break;
            case 4:
                switch (event)
                {
                    case 0: return R.array.bplan_data;
                    case 1: return R.array.addomedia_data;
                    case 2: return R.array.bquiz_data;
                    case 3: return R.array.stockit_data;
                }
                break;
            case 5:
                switch (event)
                {
                    case 0: return R.array.crumbs_data;
                    case 1: return R.array.odyssey_data;
                    case 2: return R.array.shootmup_data;
                    case 3: return R.array.insta_data;
                }
                break;
            case 6:
                switch (event)
                {
                    case 0: return R.array.thequiz_data;
                    case 1: return R.array.youthparliament_data;
                    case 2: return R.array.electronicallyyours_data;
                }
                break;
            case 7:
                switch (event)
                {
                    case 0: return R.array.projectview_data;
                }
                break;
            case 8:
                switch (event)
                {
                    case 0: return R.array.ragstoriches_data;
                    case 1: return R.array.mekanix_data;
                }
                break;
            case 9:
                switch (event)
                {
                    case 0: return R.array.khuljasimsim_data;
                    case 1: return R.array.selfie_data;
                }
                break;
            case 10:
                switch (event)
                {
                    case 0: return R.array.cad_data;
                    case 1: return R.array.nirmaan_data;
                }
                break;
        }
        return 0;
    }

}
