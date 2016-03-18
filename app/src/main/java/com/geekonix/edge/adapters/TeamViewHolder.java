package com.geekonix.edge.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekonix.edge.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sucho on 18/3/16.
 */
public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView teamName;
    public ImageView teamPhoto;

    public TeamViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        teamName = (TextView) itemView.findViewById(R.id.team_name);
        teamPhoto = (ImageView) itemView.findViewById(R.id.team_photo);
    }

    @Override
    public void onClick(View view)
    {
        Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }

}
