package com.geekonix.edge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by starringharsh on 13-03-2016.
 */
public class UpcomingActivity extends Activity{

    Button reminder;
    TextView round, time, duration;
    LinearLayout llUpcoming;
    UpcomingFile upcomingObj;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming);

        round = (TextView) findViewById(R.id.tvRound);
        time = (TextView) findViewById(R.id.tvTime);
        duration = (TextView) findViewById(R.id.tvDuration);
        llUpcoming = (LinearLayout) findViewById(R.id.llUpcoming);
        reminder = (Button) findViewById(R.id.bReminders);

        upcomingObj = new UpcomingFile();
        upcomingObj.execute();

    }
    private class UpcomingFile extends AsyncTask<String,String,String> {

        int dd, mm, hr, min, durat;

        @Override
        protected String doInBackground(String... strings) {
            // TODO Auto-generated method stub
            String x="";

            try {
                URL url = new URL("https://docs.google.com/uc?authuser=0&id=0B9ir1SJLpxDEWXJvQ3JYSU9XNWs&export=download");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                x=br.readLine();
                if(x.equalsIgnoreCase("y"))
                {
                    String y;
                    while((y=br.readLine())!=null)
                    {
                        x=x+"\n"+y;
                    }
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                x="NO CONNECTION";
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                x="NO CONNECTION";
                e.printStackTrace();
            }


            return x;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            BufferedReader br = new BufferedReader(new StringReader(result));
            String line = "n";
            sharedPreferences = getSharedPreferences("eventname", Context.MODE_PRIVATE);
            String oldString = sharedPreferences.getString("upcoming", null);
            try {
                line = br.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(line.equalsIgnoreCase("y"))
            {
                llUpcoming.setVisibility(View.VISIBLE);

                try {
                    String roundName = br.readLine();
                    round.setText(roundName);
                    dd = Integer.parseInt(br.readLine());
                    mm = Integer.parseInt(br.readLine());
                    hr = Integer.parseInt(br.readLine());
                    min = Integer.parseInt(br.readLine());
                    durat = Integer.parseInt(br.readLine());
                    System.out.println(dd + mm + hr + min);
                    time.setText("When: " + dd + "/" + mm + "/16 at " + hr + ":" + min + "hrs.");
                    duration.setText("Duration: " + durat + "mins.");
                    reminder.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub

                            Calendar cal = Calendar.getInstance();
                            Intent intent = new Intent(Intent.ACTION_EDIT);
                            cal.set(2016, mm - 1, dd, hr, min);
                            intent.setType("vnd.android.cursor.item/event");
                            intent.putExtra("beginTime", cal.getTimeInMillis());
                            intent.putExtra("allDay", false);
                            intent.putExtra("endTime", cal.getTimeInMillis() + durat * 60 * 1000);
                            intent.putExtra("title", "Reminder for " + round + " round.");
                            startActivity(intent);

                        }
                    });
                    editor = sharedPreferences.edit();
                    editor.putString("upcoming", result);
                    editor.commit();

                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            else if(line.equalsIgnoreCase("n"))
                Toast.makeText(UpcomingActivity.this,"No Upcoming Round.",Toast.LENGTH_LONG).show();
            else
            {
                Toast.makeText(UpcomingActivity.this,"Connection Error.",Toast.LENGTH_LONG).show();
                if(oldString != null)
                {
                    onPostExecute(oldString);
                }

            }

        }

    }

}