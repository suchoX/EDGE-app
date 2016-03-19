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
 * Created by sucho on 18/3/16.
 */
public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView teamName;
    public ImageView teamPhoto;
    public TextView teamPosition;

    ImageView dialogImage;
    TextView dialogCall,dialogFB;

    String number,fblink,link;


    AlertDialog teamDialog;
    AlertDialog.Builder tempBuilder;
    LayoutInflater factory;
    View teamView;

    public TeamViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        teamName = (TextView) itemView.findViewById(R.id.team_name);
        teamPhoto = (ImageView) itemView.findViewById(R.id.team_photo);
        teamPosition = (TextView) itemView.findViewById(R.id.team_position);
    }

    @Override
    public void onClick(View view)
    {
        final View v = view;
        tempBuilder=new AlertDialog.Builder(view.getContext());
        teamDialog=tempBuilder.create();
        factory = (LayoutInflater) view.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        teamView = factory.inflate(R.layout.team_dialog, null);

        dialogImage = (ImageView)teamView.findViewById(R.id.dialog_image);
        dialogCall = (TextView)teamView.findViewById(R.id.dialog_call);
        dialogFB = (TextView)teamView.findViewById(R.id.dialog_facebook);
        dialogCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamDialog.dismiss();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + number));
                v.getContext().startActivity(callIntent);
            }
        });
        dialogFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facebookUrl = fblink;
                teamDialog.dismiss();
                try {
                    int versionCode = v.getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                    if (versionCode >= 3002850)
                    {
                        Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                        v.getContext(). startActivity(new Intent(Intent.ACTION_VIEW, uri));

                    } else {
                        // open the Facebook app using the old method (fb://profile/id or fb://page/id)
                        v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/336227679757310")));
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    // Facebook is not installed. Open the browser
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
                }
            }
        });
        Picasso.with(view.getContext()).load(link).fit().centerCrop().into(dialogImage);
        teamDialog.setView(teamView);
        teamDialog.setCancelable(true);
        teamDialog.show();

        //Toast.makeText(view.getContext(), "Clicked Position = " + teamName.getText().toString(), Toast.LENGTH_SHORT).show();
    }

}
