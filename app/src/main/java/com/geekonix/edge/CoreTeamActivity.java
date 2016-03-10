package com.geekonix.edge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class CoreTeamActivity extends AppCompatActivity
{
    ListView teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_team);

        //teamList = (ListView)findViewById(R.id.core_team_list);
    }
}
