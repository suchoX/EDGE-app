package com.geekonix.edge.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekonix.edge.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sucho on 28/3/16.
 */
public class SponsorsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView sponsorPhoto;
    public TextView sponsorPosition;

    public String url;

    public SponsorsViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        sponsorPhoto = (ImageView) itemView.findViewById(R.id.sponsor_photo);
        sponsorPosition = (TextView) itemView.findViewById(R.id.sponsor_position);
    }

    @Override
    public void onClick(View v)
    {
        if(url.equals("0") || url == null || url.equals("null"))
            Toast.makeText(v.getContext(),"Website Not Available now, Please try later",Toast.LENGTH_SHORT).show();
        else
        {
            try {
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

