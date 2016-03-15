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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekonix.edge.adapters.EventListAdapter;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

public class EventsActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;

    TextView mToolBarTextView;
    Toolbar mToolbar;
    MenuParams menuParams;

    ListView eventsList;
    EventListAdapter eventListAdapter;
    List<String> computeaidEvents,roboticsEvents,foodforfunEvents,cybercrusadeEvents,moneymattersEvents,newronEvents,infocusEvents,createitEvents,justlikethatEvents,innovatiEvents,elevationEvents;
    List<String> presentList;
    boolean menuOpen=false;

    int category = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        fragmentManager = getSupportFragmentManager();

        eventsList = (ListView)findViewById(R.id.event_list);

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
        initMenuFragment();
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

    private void initMenuFragment() {
        menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        menuParams.setAnimationDuration(30);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setBgColor(R.color.blackPrimary);
        close.setDividerColor(R.color.blackPrimary);
        close.setResource(R.drawable.icn_close);

        MenuObject computeaid = new MenuObject("Compute Aid");
        computeaid.setBgColor(R.color.blackPrimary);
        computeaid.setDividerColor(R.color.blackPrimary);
        computeaid.setResource(R.drawable.icn_computeaid);

        MenuObject robotics = new MenuObject("Robotics");
        robotics.setBgColor(R.color.blackPrimary);
        robotics.setDividerColor(R.color.blackPrimary);
        robotics.setResource(R.drawable.icn_robotics);

        MenuObject cybercrusade = new MenuObject("Cyber Crusade");
        cybercrusade.setBgColor(R.color.blackPrimary);
        cybercrusade.setDividerColor(R.color.blackPrimary);
        cybercrusade.setResource(R.drawable.icn_cybercrusade);

        MenuObject foodforfun = new MenuObject("Food for Fun");
        foodforfun.setBgColor(R.color.blackPrimary);
        foodforfun.setDividerColor(R.color.blackPrimary);
        foodforfun.setResource(R.drawable.icn_foodforfun);

        MenuObject moneymatters = new MenuObject("Money Matters");
        moneymatters.setBgColor(R.color.blackPrimary);
        moneymatters.setDividerColor(R.color.blackPrimary);
        moneymatters.setResource(R.drawable.icn_moneymatters);

        MenuObject infocus = new MenuObject("InFocus");
        infocus.setBgColor(R.color.blackPrimary);
        infocus.setDividerColor(R.color.blackPrimary);
        infocus.setResource(R.drawable.icn_infocus);

        MenuObject newron = new MenuObject("Newron");
        newron.setBgColor(R.color.blackPrimary);
        newron.setDividerColor(R.color.blackPrimary);
        newron.setResource(R.drawable.icn_newron);

        MenuObject innovati = new MenuObject("Innovati");
        innovati.setBgColor(R.color.blackPrimary);
        innovati.setDividerColor(R.color.blackPrimary);
        innovati.setResource(R.drawable.icn_innovati);

        MenuObject createit = new MenuObject("Create It");
        createit.setBgColor(R.color.blackPrimary);
        createit.setDividerColor(R.color.blackPrimary);
        createit.setResource(R.drawable.icn_createit);

        MenuObject justlikethat = new MenuObject("Just Like That");
        justlikethat.setBgColor(R.color.blackPrimary);
        justlikethat.setDividerColor(R.color.blackPrimary);
        justlikethat.setResource(R.drawable.icn_justlikethat);

        MenuObject elevation = new MenuObject("Elevation");
        elevation.setBgColor(R.color.blackPrimary);
        elevation.setDividerColor(R.color.blackPrimary);
        elevation.setResource(R.drawable.icn_elevation);

        menuObjects.add(close);
        menuObjects.add(computeaid);
        menuObjects.add(robotics);
        menuObjects.add(cybercrusade);
        menuObjects.add(foodforfun);
        menuObjects.add(moneymatters);
        menuObjects.add(infocus);
        menuObjects.add(newron);
        menuObjects.add(innovati);
        menuObjects.add(createit);
        menuObjects.add(justlikethat);
        menuObjects.add(elevation);
        return menuObjects;
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //mToolbar.setNavigationIcon(R.drawable.icn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolBarTextView.setText(R.string.compute_aid);
    }

    private void changetoolbartitle(int position)
    {
        switch(position)
        {
            case 1: mToolBarTextView.setText(R.string.compute_aid);
                    break;
            case 2: mToolBarTextView.setText(R.string.robotics);
                    break;
            case 3: mToolBarTextView.setText(R.string.cyber_crusade);
                    break;
            case 4: mToolBarTextView.setText(R.string.food_for_fun);
                    break;
            case 5: mToolBarTextView.setText(R.string.money_matters);
                    break;
            case 6: mToolBarTextView.setText(R.string.infocus);
                    break;
            case 7: mToolBarTextView.setText(R.string.newron);
                    break;
            case 8: mToolBarTextView.setText(R.string.innovati);
                    break;
            case 9: mToolBarTextView.setText(R.string.create_it);
                    break;
            case 10: mToolBarTextView.setText(R.string.just_like_that);
                    break;
            case 11: mToolBarTextView.setText(R.string.elevation);
                    break;

        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        changetoolbartitle(position);
        reinitList(position);
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    menuOpen = true;
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else{
            finish();
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
        Toast.makeText(this,""+category+"  " + event,Toast.LENGTH_SHORT).show();
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
                    case 3: return R.array.twomintosell_data;
                    case 4: return R.array.cryptolabel_data;
                    case 5: return R.array.casestudy_data;
                    case 6: return R.array.kwiznet_data;
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
