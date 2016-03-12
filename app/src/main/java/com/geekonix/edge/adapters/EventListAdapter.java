package com.geekonix.edge.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.geekonix.edge.EventsActivity;
import com.geekonix.edge.R;

import java.util.List;

/**
 * Created by sucho on 9/3/16.
 */
public class EventListAdapter extends BaseAdapter
{
    List<String> eventNames;
    Context mContext;
    int category;
    Drawable eventImage[] = new Drawable[10];
    public EventListAdapter(Context mContext,List<String> eventNames,int category)
    {
        this.mContext = mContext;
        this.eventNames = eventNames;
        this.category = category;

        switch (category)
        {
            case 0:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_flawless);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icg_bughunt);
                eventImage[2] = ContextCompat.getDrawable(mContext,R.drawable.icn_codemart);
                eventImage[3] = ContextCompat.getDrawable(mContext,R.drawable.icn_cryptoquest);
                eventImage[4] = ContextCompat.getDrawable(mContext,R.drawable.icn_gameofzones);
                eventImage[5] = ContextCompat.getDrawable(mContext,R.drawable.icn_codeout);
                break;

            case 1:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_xpedition);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icn_xoccer);
                eventImage[2] = ContextCompat.getDrawable(mContext,R.drawable.icn_xport);
                eventImage[3] = ContextCompat.getDrawable(mContext,R.drawable.icn_blitzkriegx);
                eventImage[4] = ContextCompat.getDrawable(mContext,R.drawable.icn_xcelsior);
                eventImage[5] = ContextCompat.getDrawable(mContext,R.drawable.icn_exult);
                eventImage[6] = ContextCompat.getDrawable(mContext,R.drawable.icn_aerostrix);
                eventImage[7] = ContextCompat.getDrawable(mContext,R.drawable.icn_xplore);
                eventImage[8] = ContextCompat.getDrawable(mContext,R.drawable.icn_perplexity);
                eventImage[9] = ContextCompat.getDrawable(mContext,R.drawable.icn_3xtreet);
                break;

            case 2:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_cs);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icn_csgo);
                eventImage[2] = ContextCompat.getDrawable(mContext,R.drawable.icn_mortalkombat);
                eventImage[3] = ContextCompat.getDrawable(mContext, R.drawable.icn_dota);
                eventImage[4] = ContextCompat.getDrawable(mContext,R.drawable.icn_fifa15);
                eventImage[5] = ContextCompat.getDrawable(mContext,R.drawable.icn_fifa11);
                eventImage[6] = ContextCompat.getDrawable(mContext,R.drawable.icn_nfsmw);
                break;

            case 3:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_xquizit);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icn_foodrelay);
                eventImage[2] = ContextCompat.getDrawable(mContext,R.drawable.icn_creationxnihilo);
                eventImage[3] = ContextCompat.getDrawable(mContext,R.drawable.icn_2minutestosell);
                eventImage[4] = ContextCompat.getDrawable(mContext,R.drawable.icn_foodproductlabel);
                eventImage[5] = ContextCompat.getDrawable(mContext,R.drawable.icn_casestudy);
                eventImage[6] = ContextCompat.getDrawable(mContext,R.drawable.icn_kwiznet);
                break;

            case 4:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_bplan);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icn_addomedia);
                eventImage[2] = ContextCompat.getDrawable(mContext,R.drawable.icn_bquiz);
                eventImage[3] = ContextCompat.getDrawable(mContext,R.drawable.icn_stockit);
                break;

            case 5:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_bplan);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icn_addomedia);
                eventImage[2] = ContextCompat.getDrawable(mContext,R.drawable.icn_bquiz);
                eventImage[3] = ContextCompat.getDrawable(mContext,R.drawable.icn_stockit);
                break;

            case 6:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_btechquiz);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icn_debate);
                eventImage[2] = ContextCompat.getDrawable(mContext,R.drawable.icn_electronic);
                break;

            case 7:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_projectview);
                break;

            case 8:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_selfie);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icn_mekanix);
                break;

            case 9:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_khuljasimsim);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icn_selfie);
                break;

            case 10:
                eventImage[0] = ContextCompat.getDrawable(mContext,R.drawable.icn_cad);
                eventImage[1] = ContextCompat.getDrawable(mContext,R.drawable.icn_bridge);
                break;
        }
    }

    @Override
    public int getCount() {
        if(eventNames == null)
            return 0;
        else
            return eventNames.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Holder holder = new Holder();
        if(convertView==null)
        {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.single_category_layout, parent, false);
        }

        holder.categoryName = (TextView)convertView.findViewById(R.id.category_name);
        holder.categoryImage = (ImageView)convertView.findViewById(R.id.category_image);
        holder.linearLayout = (LinearLayout)convertView.findViewById(R.id.row_layout);

        holder.categoryName.setText(eventNames.get(position));
        setBackground(position%6,holder);

        switch(category)
        {
            case 0:
                computeaidImage(position,holder);
                break;
            case 1:
                roboticsImage(position,holder);
                break;
            case 2:
                cybercrusadeImage(position,holder);
                break;
            case 3:
                foodforfunImage(position,holder);
                break;
            case 4:
                moneymatytersImage(position,holder);
                break;
            case 5:
                infocusImage(position,holder);
                break;
            case 6:
                newronImage(position,holder);
                break;
            case 7:
                innovatiImage(position,holder);
                break;
            case 8:
                createitImage(position,holder);
                break;
            case 9:
                justlikethatImage(position,holder);
                break;
            case 10:
                elevationImage(position,holder);
                break;
        }

        convertView.setOnClickListener(new OnItemClickListener(eventNames.get(position)));
        return convertView;
    }

    private void setBackground(int position,Holder holder)
    {
        switch(position)
        {
            case 0:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor1));
                break;

            case 1:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor2));
                break;

            case 2:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor3));
                break;

            case 3:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor4));
                break;

            case 4:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor5));
                break;

            case 5:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor6));
                break;
        }
    }

    private void computeaidImage(int position,Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
            case 2:
                holder.categoryImage.setImageDrawable(eventImage[2]);
                break;
            case 3:
                holder.categoryImage.setImageDrawable(eventImage[3]);
                break;
            case 4:
                holder.categoryImage.setImageDrawable(eventImage[5]);
                break;
            case 5:
                holder.categoryImage.setImageDrawable(eventImage[6]);
                break;
        }
    }
    private void roboticsImage(int position,Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
            case 2:
                holder.categoryImage.setImageDrawable(eventImage[2]);
                break;
            case 3:
                holder.categoryImage.setImageDrawable(eventImage[3]);
                break;
            case 4:
                holder.categoryImage.setImageDrawable(eventImage[4]);
                break;
            case 5:
                holder.categoryImage.setImageDrawable(eventImage[5]);
                break;
            case 6:
                holder.categoryImage.setImageDrawable(eventImage[6]);
                break;
            case 7:
                holder.categoryImage.setImageDrawable(eventImage[7]);
                break;
            case 8:
                holder.categoryImage.setImageDrawable(eventImage[8]);
                break;
            case 9:
                holder.categoryImage.setImageDrawable(eventImage[9]);
                break;

        }
    }
    private void cybercrusadeImage(int position,Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
            case 2:
                holder.categoryImage.setImageDrawable(eventImage[2]);
                break;
            case 3:
                holder.categoryImage.setImageDrawable(eventImage[3]);
                break;
            case 4:
                holder.categoryImage.setImageDrawable(eventImage[4]);
                break;
            case 5:
                holder.categoryImage.setImageDrawable(eventImage[5]);
                break;
            case 6:
                holder.categoryImage.setImageDrawable(eventImage[6]);
                break;
        }
    }
    private void foodforfunImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
            case 2:
                holder.categoryImage.setImageDrawable(eventImage[2]);
                break;
            case 3:
                holder.categoryImage.setImageDrawable(eventImage[3]);
                break;
            case 4:
                holder.categoryImage.setImageDrawable(eventImage[4]);
                break;
            case 5:
                holder.categoryImage.setImageDrawable(eventImage[5]);
                break;
            case 6:
                holder.categoryImage.setImageDrawable(eventImage[6]);
                break;
        }
    }
    private void moneymatytersImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
            case 2:
                holder.categoryImage.setImageDrawable(eventImage[2]);
                break;
            case 3:
                holder.categoryImage.setImageDrawable(eventImage[3]);
                break;

        }
    }
    private void infocusImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
            case 2:
                holder.categoryImage.setImageDrawable(eventImage[2]);
                break;
            case 3:
                holder.categoryImage.setImageDrawable(eventImage[3]);
                break;

        }
    }
    private void newronImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
            case 2:
                holder.categoryImage.setImageDrawable(eventImage[2]);
                break;
        }
    }
    private void innovatiImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
        }
    }
    private void elevationImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
        }
    }
    private void createitImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
        }
    }
    private void justlikethatImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(eventImage[0]);
                break;
            case 1:
                holder.categoryImage.setImageDrawable(eventImage[1]);
                break;
        }
    }



    private class Holder
    {
        TextView categoryName;
        ImageView categoryImage;
        LinearLayout linearLayout;
    }

    private class OnItemClickListener  implements View.OnClickListener {
        private String eventName;

        OnItemClickListener(String eventName)
        {
            this.eventName = eventName;
        }

        @Override
        public void onClick(View arg0)
        {
            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            ((EventsActivity)mContext).eventSelected(eventName);
        }
    }
}
