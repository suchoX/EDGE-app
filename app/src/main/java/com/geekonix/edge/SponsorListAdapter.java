package com.geekonix.edge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekonix.edge.adapters.SponsorsViewHolder;
import com.geekonix.edge.others.SponsorObjects;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sucho on 28/3/16.
 */
public class SponsorListAdapter extends RecyclerView.Adapter<SponsorsViewHolder>
{
    private List<SponsorObjects> itemList;
    private Context context;

    public SponsorListAdapter(Context context, List<SponsorObjects> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public SponsorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsor_single_item, null);
        SponsorsViewHolder rcv = new SponsorsViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(SponsorsViewHolder holder, int position)
    {
        holder.sponsorPosition.setText(itemList.get(position).getPosition());
        holder.url = itemList.get(position).getUrl();
        Picasso.with(context).load("http://edg.co.in/" + itemList.get(position).getImage()).fit().placeholder(R.drawable.temp_header).into(holder.sponsorPhoto);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}