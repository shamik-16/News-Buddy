package com.example.newsbuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsbuddy.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {

    String api = "59991cf8600e49379c97a414310ea921";
    ArrayList<DataModelClass> modelClassArrayList;
    MyRecyclerAdapter adapter;
    String country = "in";
    private RecyclerView sportsrecyclerView;

    private final String category = "sports";
    private ProgressBar pgBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sports_fragment,null);

        pgBar = v.findViewById(R.id.pgBar);
        sportsrecyclerView = v.findViewById(R.id.sportsRecyclerView);
        modelClassArrayList = new ArrayList<>();
        sportsrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerAdapter(getContext(),modelClassArrayList);
        sportsrecyclerView.setAdapter(adapter);

        fetchNews();


        return v;
    }

    private void fetchNews() {

        pgBar.setVisibility(View.VISIBLE);

        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<NewsMenuDetails>() {
            @Override
            public void onResponse(Call<NewsMenuDetails> call, Response<NewsMenuDetails> response) {
                if(response.isSuccessful()){
                    // assert response.body() != null;
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                    pgBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<NewsMenuDetails> call, Throwable t) {

                pgBar.setVisibility(View.GONE);
            }
        });

    }
}
