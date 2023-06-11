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

public class ScienceFragment extends Fragment {

    String api = "59991cf8600e49379c97a414310ea921";
    ArrayList<DataModelClass> modelClassArrayList;
    MyRecyclerAdapter adapter;
    String country = "in";
    private RecyclerView sciencerecyclerView;

    private final String category = "science";
    private ProgressBar pgBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.science_fragment,null);

        pgBar = v.findViewById(R.id.pgBar);
        sciencerecyclerView = v.findViewById(R.id.scienceRecyclerView);
        modelClassArrayList = new ArrayList<>();
        sciencerecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerAdapter(getContext(),modelClassArrayList);
        sciencerecyclerView.setAdapter(adapter);

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
