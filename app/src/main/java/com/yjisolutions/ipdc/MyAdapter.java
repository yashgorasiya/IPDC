package com.yjisolutions.ipdc;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<ListData> listData;
    ArrayList<ListData> backup;
    Context context;

    public MyAdapter(ArrayList<ListData> listData, Context context) {
        this.listData = listData;
        this.context = context;
        backup = new ArrayList<>(listData);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListData temp = listData.get(position);
        holder.chName.setText("Lecture - " + temp.getChapterName());
        holder.size.setText(temp.getVideoSize());
        holder.chDes.setText(temp.getChapterDescription());
        Glide.with(holder.ep1T.getContext()).load(temp.getVideoThumbnail()).into(holder.ep1T);

        holder.ep1T.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoViewer.class);
            intent.putExtra("url", temp.getVideoUrl());
            intent.putExtra("chapter", temp.getChapterName());
            intent.putExtra("dis", temp.getChapterDescription());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        holder.mxRedirect.setOnClickListener(v -> {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(temp.getVideoUrl()));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                browserIntent.setPackage("com.mxtech.videoplayer.ad");
                context.startActivity(browserIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Please install a MxPlayer to use this Option", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        );

        holder.download.setOnClickListener(v -> {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(temp.getVideoUrl()));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Error Opening Browser", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        );

        holder.size.setOnClickListener(v -> {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(temp.getVideoUrl()));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Error Opening Browser", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        );


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView chName;
        private final TextView chDes;
        private final TextView size;
        private final ImageView ep1T;
        private final ImageView download;
        private final ImageView mxRedirect;

        public ViewHolder(View itemView) {
            super(itemView);
            chName = itemView.findViewById(R.id.chName);
            chDes = itemView.findViewById(R.id.chDescription);
            size = itemView.findViewById(R.id.downloadSize);
            ep1T = itemView.findViewById(R.id.ep1Thumb);
            download = itemView.findViewById(R.id.downloadIcon);
            mxRedirect = itemView.findViewById(R.id.mxRedirect);
        }
    }


}
