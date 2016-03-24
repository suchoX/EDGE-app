package com.geekonix.edge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.geekonix.edge.adapters.TeamListAdapter;
import com.geekonix.edge.others.TeamObjects;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity
{
    private StaggeredGridLayoutManager gridLayoutManager;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<TeamObjects> teamList = getListItemData();

        TeamListAdapter rcAdapter = new TeamListAdapter(this, teamList);
        recyclerView.setAdapter(rcAdapter);

        initToolbar();
    }

    private List<TeamObjects> getListItemData(){
        List<TeamObjects> listViewItems = new ArrayList<TeamObjects>();
        listViewItems.add(new TeamObjects("Utsav Vatsa","Branding Head","http://imgur.com/aee3Ht3.png","+919434665052","https://www.facebook.com/utsav.vatsa"));
        listViewItems.add(new TeamObjects("Rittika Singhania","Public Relations", "http://imgur.com/kp1fpid.png","+919007452380","https://www.facebook.com/rittika.singhania.7"));
        listViewItems.add(new TeamObjects("Palash Chatterjee", "Web Developer", "http://imgur.com/tS08ZG1.png","+919051333843","https://www.facebook.com/palash.chatterjee"));
        listViewItems.add(new TeamObjects("Raj Singh", "Convenor", "http://imgur.com/OP65JpN.png","+918100291321","https://www.facebook.com/coolraaj007"));
        listViewItems.add(new TeamObjects("Ashish Choudhary","Sponsorship", "http://imgur.com/opGlNBr.png","+919836891210","https://www.facebook.com/ishprince1"));
        listViewItems.add(new TeamObjects("Shubham Sharma", "Sponsorship", "http://imgur.com/Dp5ZgCE.png","+919836933862","https://www.facebook.com/shubhamdayo"));
        listViewItems.add(new TeamObjects("Arpan Mondal", "Treasurer", "http://imgur.com/4o6f6y9.png","+919830231666","https://www.facebook.com/arpan411"));
        listViewItems.add(new TeamObjects("Neil Bhaumik","Creative Design", "http://imgur.com/3WKMdfW.png","+918335993845","https://www.facebook.com/Gx.Edg"));
        listViewItems.add(new TeamObjects("Puja Rani", "Publicity Head", "http://imgur.com/5CREdTC.png","+919614592487","https://www.facebook.com/puja.rani.5245961"));
        listViewItems.add(new TeamObjects("Rohan Sahay", "Web Developer", "http://imgur.com/1tBZJul.png","+919874640941","https://www.facebook.com/Rohan.Sahay"));
        listViewItems.add(new TeamObjects("Pratyush Raj", "Designing Head", "http://imgur.com/fLtY1Jw.png","+918274931510","https://www.facebook.com/raj.or.pratyush"));
        listViewItems.add(new TeamObjects("Soumyadip Sen", "Sponsorship Head", "http://imgur.com/bCw49dC.png","+919804479352","https://www.facebook.com/soumyadip.sen.14"));
        listViewItems.add(new TeamObjects("Shobit Sahay", "Jt. Convener", "http://imgur.com/DpzRZBx.png","+918017174045","https://www.facebook.com/shobhit.shan"));
        listViewItems.add(new TeamObjects("Soumyadip Basu", "Sponsorship Head", "http://imgur.com/8ztWgxf.png","+917686041496","https://www.facebook.com/soumyadip.basu.54"));
        listViewItems.add(new TeamObjects("Abhishek Singh", "Jt. Convenor", "http://imgur.com/bpLKPC6.png","+918582817792","https://www.facebook.com/abhisheik6"));
        listViewItems.add(new TeamObjects("Vishal Agarwal", "Campus Ambassador Co-Ordinator", "http://i.imgur.com/BrOIDxol.jpg","+919804563465","https://www.facebook.com/agarwalvishal1994"));
        listViewItems.add(new TeamObjects("Suchandrim Sarkar", "App Developer", "http://imgur.com/tg8uaN4.png","+919775301737","https://www.facebook.com/suchandrim.sarkar"));


        return listViewItems;
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("The Team");
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
