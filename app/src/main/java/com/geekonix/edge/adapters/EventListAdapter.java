package com.geekonix.edge.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public EventListAdapter(Context mContext,List<String> eventNames,int category)
    {
        this.mContext = mContext;
        this.eventNames = eventNames;
        this.category = category;
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
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_flawless));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icg_bughunt));
                break;
            case 2:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_codemart));
                break;
            case 3:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_cryptoquest));
                break;
            case 4:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_gameofzones));
                break;
            case 5:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_codeout));
                break;
        }
    }
    private void roboticsImage(int position,Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_xpedition));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_xoccer));
                break;
            case 2:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_xport));
                break;
            case 3:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_blitzkriegx));
                break;
            case 4:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_xcelsior));
                break;
            case 5:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_exult));
                break;
            case 6:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_aerostrix));
                break;
            case 7:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_xplore));
                break;
            case 8:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_perplexity));
                break;
            case 9:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_3xtreet));
                break;

        }
    }
    private void cybercrusadeImage(int position,Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_cs));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_csgo));
                break;
            case 2:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_mortalkombat));
                break;
            case 3:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_dota));
                break;
            case 4:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_fifa15));
                break;
            case 5:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_fifa11));
                break;
            case 6:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_nfsmw));
                break;
        }
    }
    private void foodforfunImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_xquizit));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_foodrelay));
                break;
            case 2:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_creationxnihilo));
                break;
            case 3:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_2minutestosell));
                break;
            case 4:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_foodproductlabel));
                break;
            case 5:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_casestudy));
                break;
            case 6:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_kwiznet));
                break;
        }
    }
    private void moneymatytersImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_bplan));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_addomedia));
                break;
            case 2:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_bquiz));
                break;
            case 3:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_stockit));
                break;

        }
    }
    private void infocusImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_crumbs));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_odyssey));
                break;
            case 2:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_shootmup));
                break;
            case 3:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_insta));
                break;

        }
    }
    private void newronImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_btechquiz));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_debate));
                break;
            case 2:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_electronic));
                break;
        }
    }
    private void innovatiImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_projectview));
                break;
        }
    }
    private void elevationImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_cad));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_bridge));
                break;
        }
    }
    private void createitImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_selfie));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_mekanix));
                break;
        }
    }
    private void justlikethatImage(int position, Holder holder)
    {
        switch (position)
        {
            case 0:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_khuljasimsim));
                break;
            case 1:
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icn_selfie));
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
