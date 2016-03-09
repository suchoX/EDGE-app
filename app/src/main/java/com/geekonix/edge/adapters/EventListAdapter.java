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
    public EventListAdapter(Context mContext,List<String> eventNames)
    {
        this.mContext = mContext;
        this.eventNames = eventNames;
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

        convertView.setOnClickListener(new OnItemClickListener(eventNames.get(position)));
        return convertView;
    }

    private void setBackground(int position,Holder holder)
    {
        switch(position)
        {
            case 0:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor1));
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.temp));
                break;

            case 1:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor2));
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.temp2));
                break;

            case 2:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor3));
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.temp3));
                break;

            case 3:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor4));
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.temp4));
                break;

            case 4:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor5));
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.temp5));
                break;

            case 5:
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor6));
                holder.categoryImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.temp6));
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
