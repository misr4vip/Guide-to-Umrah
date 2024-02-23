package com.example.guidetoumrah;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class myAdapter extends ArrayAdapter<serviceModel> {
    private int resourceLayout;
    private Context mContext;

    public myAdapter(Context context, int resource, ArrayList<serviceModel> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }
    @Override
    public void remove(@Nullable serviceModel object) {
        super.remove(object);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);

        }
        serviceModel p = getItem(position);
        if (p != null) {
            TextView tv = v.findViewById(R.id.tvServiceName);
            tv.setText(p.getName());
            ImageView iv = v.findViewById(R.id.ivLocation);
            iv.setOnClickListener(view->{
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+p.getLatitude()+","+p.getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mContext.startActivity(mapIntent);

            });

        }
        return v;
    }

}
