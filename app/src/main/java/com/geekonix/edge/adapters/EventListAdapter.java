package com.geekonix.edge.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.Display;
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
    int eventImage[] = new int[10];
    private Bitmap bmp;
    public EventListAdapter(Context mContext,List<String> eventNames,int category)
    {
        this.mContext = mContext;
        this.eventNames = eventNames;
        this.category = category;

        switch (category)
        {
            case 0:
                eventImage[0] = R.drawable.icn_flawless;
                eventImage[1] = R.drawable.icg_bughunt;
                eventImage[2] = R.drawable.icn_codemart;
                eventImage[3] = R.drawable.icn_cryptoquest;
                eventImage[4] = R.drawable.icn_gameofzones;
                eventImage[5] = R.drawable.icn_codeout;
                break;

            case 1:
                eventImage[0] = R.drawable.icn_xpedition;
                eventImage[1] = R.drawable.icn_xoccer;
                eventImage[2] = R.drawable.icn_xport;
                eventImage[3] = R.drawable.icn_blitzkriegx;
                eventImage[4] = R.drawable.icn_xcelsior;
                eventImage[5] = R.drawable.icn_exult;
                eventImage[6] = R.drawable.icn_aerostrix;
                eventImage[7] = R.drawable.icn_xplore;
                eventImage[8] = R.drawable.icn_perplexity;
                eventImage[9] = R.drawable.icn_3xtreet;
                break;

            case 2:
                eventImage[0] = R.drawable.icn_cs;
                eventImage[1] = R.drawable.icn_csgo;
                eventImage[2] = R.drawable.icn_mortalkombat;
                eventImage[3] =  R.drawable.icn_dota;
                eventImage[4] = R.drawable.icn_fifa15;
                eventImage[5] = R.drawable.icn_fifa11;
                eventImage[6] = R.drawable.icn_nfsmw;
                break;

            case 3:
                eventImage[0] = R.drawable.icn_xquizit;
                eventImage[1] = R.drawable.icn_foodrelay;
                eventImage[2] = R.drawable.icn_creationxnihilo;
                eventImage[3] = R.drawable.icn_2minutestosell;
                eventImage[4] = R.drawable.icn_foodproductlabel;
                eventImage[5] = R.drawable.icn_casestudy;
                eventImage[6] = R.drawable.icn_kwiznet;
                break;

            case 4:
                eventImage[0] = R.drawable.icn_bplan;
                eventImage[1] = R.drawable.icn_addomedia;
                eventImage[2] = R.drawable.icn_bquiz;
                eventImage[3] = R.drawable.icn_stockit;
                break;

            case 5:
                eventImage[0] = R.drawable.icn_bplan;
                eventImage[1] = R.drawable.icn_addomedia;
                eventImage[2] = R.drawable.icn_bquiz;
                eventImage[3] = R.drawable.icn_stockit;
                break;

            case 6:
                eventImage[0] = R.drawable.icn_btechquiz;
                eventImage[1] = R.drawable.icn_debate;
                eventImage[2] = R.drawable.icn_electronic;
                break;

            case 7:
                eventImage[0] = R.drawable.icn_projectview;
                break;

            case 8:
                eventImage[0] = R.drawable.icn_selfie;
                eventImage[1] = R.drawable.icn_mekanix;
                break;

            case 9:
                eventImage[0] = R.drawable.icn_khuljasimsim;
                eventImage[1] = R.drawable.icn_selfie;
                break;

            case 10:
                eventImage[0] = R.drawable.icn_cad;
                eventImage[1] = R.drawable.icn_bridge;
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
            holder.categoryName = (TextView)convertView.findViewById(R.id.category_name);
            holder.categoryImage = (ImageView)convertView.findViewById(R.id.category_image);
            holder.cardView = (CardView)convertView.findViewById(R.id.card_view);
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
            holder.mImageLoader.cancel();
        }

        holder.categoryName.setText(eventNames.get(position));
        setBackground(position % 6, holder);

        holder.mImageLoader = new AsyncImageSetter(mContext,holder.categoryImage,eventImage[position],bmp);
        holder.mImageLoader.execute();


        convertView.setOnClickListener(new OnItemClickListener(position));
        return convertView;
    }

    private void setBackground(int position,Holder holder)
    {
        switch(position)
        {
            case 0:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor1));
                break;

            case 1:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor2));
                break;

            case 2:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor3));
                break;

            case 3:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor4));
                break;

            case 4:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor5));
                break;

            case 5:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.listcolor6));
                break;
        }
    }



    private class Holder
    {
        TextView categoryName;
        ImageView categoryImage;
        CardView cardView;
        public AsyncImageSetter mImageLoader;
    }

    public class AsyncImageSetter extends AsyncTask<Void, Void, Bitmap> {

        private ImageView img;
        private int image_resId;
        private Bitmap bmp;
        private Context c;
        private boolean cancel = false;
        private int sampleSize;


        public AsyncImageSetter(Context c, ImageView img, int image_ResId,
                                Bitmap bmp) {

            this.img = img;
            this.image_resId = image_ResId;
            this.bmp = bmp;
            this.c = c;

        }

        public void cancel() {
            cancel = true;
        }

        @Override
        protected void onPreExecute()
        {
            img.setVisibility(View.GONE);
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            if (!cancel) {
                try {
                    return decodeAndScale(bmp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            img.setVisibility(View.VISIBLE);
            try {
                img.setImageBitmap(result);
            } catch (Exception e) {
                img.setImageResource(R.drawable.icn_close);
            }
            super.onPostExecute(result);
        }

        private Bitmap decodeAndScale(Bitmap bmp) {

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = setSampleSize();

            return BitmapFactory.decodeResource(c.getResources(), image_resId,
                    options);

        }

        private int setSampleSize() {

            // TODO add multiple screens check
            if (getScreenWidth((Activity) c) >= 320) {
                sampleSize = 2;
            }
            return sampleSize;
        }
    }

    public int getScreenWidth(Activity a) {

        Display display = a.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = a.getResources().getDisplayMetrics().density;
        float dpWidth = outMetrics.widthPixels / density;

        return (int) dpWidth;
    }

    private class OnItemClickListener  implements View.OnClickListener {
        private int event;

        OnItemClickListener(int event)
        {
            this.event = event;
        }

        @Override
        public void onClick(View arg0)
        {
            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            ((EventsActivity)mContext).eventSelected(event);
        }
    }
}
