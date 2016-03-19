package com.geekonix.edge.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.geekonix.edge.R;
import com.geekonix.edge.others.TeamObjects;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sucho on 18/3/16.
 */
public class TeamListAdapter extends RecyclerView.Adapter<TeamViewHolder>
{
    private List<TeamObjects> itemList;
    private Context context;

    public TeamListAdapter(Context context, List<TeamObjects> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_single_item, null);
        TeamViewHolder rcv = new TeamViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position)
    {
        holder.teamName.setText(itemList.get(position).getName());
        holder.teamPosition.setText(itemList.get(position).getPosition());
        holder.number = itemList.get(position).getNumber();
        holder.fblink = itemList.get(position).getFblink();
        holder.link = itemList.get(position).getImage();
        Picasso.with(context).load(itemList.get(position).getImage()).into(holder.teamPhoto);
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
