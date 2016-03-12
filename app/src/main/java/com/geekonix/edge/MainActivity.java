package com.geekonix.edge;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  implements OnMenuItemClickListener, OnMenuItemLongClickListener
{
    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;

    LinearLayout eventsLayout,megaeventsLayout,campusambassadorLayout,teamLayout,sponsorsLayout;
    Toolbar mToolbar;
    TextView mToolBarTextView;

    MenuParams menuParams;

    boolean menuOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        eventsLayout = (LinearLayout)findViewById(R.id.event_layout);
        megaeventsLayout = (LinearLayout)findViewById(R.id.megaevent_layout);
        campusambassadorLayout = (LinearLayout)findViewById(R.id.campusambassador_layout);
        teamLayout = (LinearLayout)findViewById(R.id.team_layout);
        sponsorsLayout = (LinearLayout)findViewById(R.id.sponsor_layout);
        eventsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });


        initToolbar();
        initMenuFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBarTextView.setText("EDGE X");
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

    private List<MenuObject> getMenuObjects()
    {
        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setBgColor(R.color.blackPrimary);
        close.setDividerColor(R.color.blackPrimary);
        close.setResource(R.drawable.icn_close);

        MenuObject facebook = new MenuObject("Facebook");
        facebook.setBgColor(R.color.blackPrimary);
        facebook.setDividerColor(R.color.blackPrimary);
        facebook.setResource(R.drawable.icn_facebook);

        MenuObject youtube = new MenuObject("Youtube");
        youtube.setBgColor(R.color.blackPrimary);
        youtube.setDividerColor(R.color.blackPrimary);
        youtube.setResource(R.drawable.icn_youtube);

        MenuObject twitter = new MenuObject("Twitter");
        twitter.setBgColor(R.color.blackPrimary);
        twitter.setDividerColor(R.color.blackPrimary);
        twitter.setResource(R.drawable.icn_twitter);

        MenuObject instagram = new MenuObject("Instagram");
        instagram.setBgColor(R.color.blackPrimary);
        instagram.setDividerColor(R.color.blackPrimary);
        instagram.setResource(R.drawable.icn_instagram);

        menuObjects.add(close);
        menuObjects.add(facebook);
        menuObjects.add(youtube);
        menuObjects.add(twitter);
        menuObjects.add(instagram);

        return menuObjects;

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

    @Override
    public void onMenuItemClick(View clickedView, int position)
    {
        switch (position)
        {
            case 1:
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
                break;
            case 2:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/channel/UCSwFemGqe1XRmVlg1jRNJYw"));
                startActivity(intent);
                break;
            case 3:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/geekonixedge")));

                break;
            case 4:
                Uri uri = Uri.parse("http://instagram.com/_u/geekonix");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/geekonix")));
                }
                break;
        }

    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {

    }
}
