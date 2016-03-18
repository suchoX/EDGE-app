package com.geekonix.edge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.geekonix.edge.adapters.TeamListAdapter;
import com.geekonix.edge.others.TeamObjects;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity
{
    private StaggeredGridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<TeamObjects> teamList = getListItemData();

        TeamListAdapter rcAdapter = new TeamListAdapter(this, teamList);
        recyclerView.setAdapter(rcAdapter);
    }

    private List<TeamObjects> getListItemData(){
        List<TeamObjects> listViewItems = new ArrayList<TeamObjects>();
        listViewItems.add(new TeamObjects("Utsav Vatsa","http://imgur.com/aee3Ht3.png"));
        listViewItems.add(new TeamObjects("Rittika Singhania", "http://imgur.com/kp1fpid.png"));
        listViewItems.add(new TeamObjects("Palash Chatterjee", "http://imgur.com/tS08ZG1.png"));
        listViewItems.add(new TeamObjects("Raj Singh", "http://imgur.com/OP65JpN.png"));
        listViewItems.add(new TeamObjects("Ashish Choudhary", "http://imgur.com/opGlNBr.png"));
        listViewItems.add(new TeamObjects("Shubham Sharma", "http://imgur.com/Dp5ZgCE.png"));
        listViewItems.add(new TeamObjects("Arpan Mondal", "http://imgur.com/4o6f6y9.png"));
        listViewItems.add(new TeamObjects("Neil Bhaumik", "http://imgur.com/3WKMdfW.png"));
        listViewItems.add(new TeamObjects("Puja Rani", "http://imgur.com/5CREdTC.png"));
        listViewItems.add(new TeamObjects("Rohan Sahay", "http://imgur.com/1tBZJul.png"));
        listViewItems.add(new TeamObjects("Pratyush Raj", "http://imgur.com/fLtY1Jw.png"));
        listViewItems.add(new TeamObjects("Soumyadip Sen", "http://imgur.com/bCw49dC.png"));
        listViewItems.add(new TeamObjects("Shobit Sahay", "http://imgur.com/DpzRZBx.png"));
        listViewItems.add(new TeamObjects("Soumyadip Basu", "http://imgur.com/8ztWgxf.png"));
        listViewItems.add(new TeamObjects("Abhishek Singh", "http://imgur.com/bpLKPC6.png"));

        return listViewItems;
    }
}
