package com.geekonix.edge;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;

import java.util.ArrayList;
import java.util.List;

import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

public class EventsActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;

    TextView mToolBarTextView;
    Toolbar mToolbar;
    MenuParams menuParams;

    boolean menuOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        fragmentManager = getSupportFragmentManager();

        initToolbar();
        initMenuFragment();
    }

    private void initMenuFragment() {
        menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        menuParams.setAnimationDuration(50);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setBgColor(R.color.midnightblue);
        close.setDividerColor(R.color.blackPrimary);
        close.setResource(R.drawable.icn_close);

        MenuObject computeaid = new MenuObject("Compute Aid");
        computeaid.setBgColor(R.color.midnightblue);
        computeaid.setDividerColor(R.color.blackPrimary);
        computeaid.setResource(R.drawable.icn_computeaid);

        MenuObject robotics = new MenuObject("Robotics");
        robotics.setBgColor(R.color.midnightblue);
        robotics.setDividerColor(R.color.blackPrimary);
        robotics.setResource(R.drawable.icn_robotics);

        MenuObject cybercrusade = new MenuObject("Cyber Crusade");
        cybercrusade.setBgColor(R.color.midnightblue);
        cybercrusade.setDividerColor(R.color.blackPrimary);
        cybercrusade.setResource(R.drawable.icn_cybercrusade);

        MenuObject foodforfun = new MenuObject("Food for Fun");
        foodforfun.setBgColor(R.color.midnightblue);
        foodforfun.setDividerColor(R.color.blackPrimary);
        foodforfun.setResource(R.drawable.icn_foodforfun);

        MenuObject moneymatters = new MenuObject("Money Matters");
        moneymatters.setBgColor(R.color.midnightblue);
        moneymatters.setDividerColor(R.color.blackPrimary);
        moneymatters.setResource(R.drawable.icn_moneymatters);

        MenuObject infocus = new MenuObject("InFocus");
        infocus.setBgColor(R.color.midnightblue);
        infocus.setDividerColor(R.color.blackPrimary);
        infocus.setResource(R.drawable.icn_infocus);

        MenuObject newron = new MenuObject("Newron");
        newron.setBgColor(R.color.midnightblue);
        newron.setDividerColor(R.color.blackPrimary);
        newron.setResource(R.drawable.icn_newron);

        MenuObject innovati = new MenuObject("Innovati");
        innovati.setBgColor(R.color.midnightblue);
        innovati.setDividerColor(R.color.blackPrimary);
        innovati.setResource(R.drawable.icn_innovati);

        MenuObject createit = new MenuObject("Create It");
        createit.setBgColor(R.color.midnightblue);
        createit.setDividerColor(R.color.blackPrimary);
        createit.setResource(R.drawable.icn_createit);

        MenuObject justlikethat = new MenuObject("Just Like That");
        justlikethat.setBgColor(R.color.midnightblue);
        justlikethat.setDividerColor(R.color.blackPrimary);
        justlikethat.setResource(R.drawable.icn_justlikethat);

        MenuObject elevation = new MenuObject("Elevation");
        elevation.setBgColor(R.color.midnightblue);
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
        mToolBarTextView.setText("Compute Aid");
    }

    private void changetoolbartitle(int position)
    {
        switch(position)
        {
            case 1: mToolBarTextView.setText("Compute Aid");
                    break;
            case 2: mToolBarTextView.setText("Robotics");
                    break;
            case 3: mToolBarTextView.setText("Cyber Crusade");
                    break;
            case 4: mToolBarTextView.setText("Food for Fun");
                    break;
            case 5: mToolBarTextView.setText("Money Matters");
                    break;
            case 6: mToolBarTextView.setText("InFocus");
                    break;
            case 7: mToolBarTextView.setText("Newron");
                    break;
            case 8: mToolBarTextView.setText("Innovati");
                    break;
            case 9: mToolBarTextView.setText("CreateIt");
                    break;
            case 10: mToolBarTextView.setText("Just Like That");
                    break;
            case 11: mToolBarTextView.setText("Elevation");
                    break;

        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
        changetoolbartitle(position);
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {

        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
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
}
