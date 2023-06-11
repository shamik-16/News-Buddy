package com.example.newsbuddy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsbuddy.R;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<DataModelClass> arrNews;

    public MyRecyclerAdapter(Context context, ArrayList<DataModelClass> arrNews) {
        this.context = context;
        this.arrNews = arrNews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WebViewActivity.class);
                intent.putExtra("url",arrNews.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.heading.setText(arrNews.get(position).getTitle());

        if(arrNews.get(position).getDescription() == null){
            holder.content.setText(R.string.temp_discription);
        }else {
            holder.content.setText(arrNews.get(position).getDescription());
        }

        if(arrNews.get(position).getAuthor() == null){
            holder.author.setText(R.string.author_anonymous);
        }else{
            holder.author.setText("Author : "+arrNews.get(position).getAuthor());
        }

        holder.time.setText("Published At : " + arrNews.get(position).getPublishedAt());

        if(arrNews.get(position).getUrlToImage() == null){
            holder.image.setImageResource(R.drawable.newpaper);
        }else{
            Glide.with(context).load(arrNews.get(position).getUrlToImage()).into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return arrNews.size();
    }

    // View holder class
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading,content,author,time;
        CardView cardView;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.txtHeading);
            content = itemView.findViewById(R.id.txtDescription);
            author = itemView.findViewById(R.id.txtAuthor);
            time = itemView.findViewById(R.id.txtPublishedAt);
            image = itemView.findViewById(R.id.imgNews);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
