package com.example.newsbuddy;

import java.util.ArrayList;

public class NewsMenuDetails {

    private String status;
    private String totalResults;
    private ArrayList<DataModelClass> articles;

    public NewsMenuDetails(String status, String totalResults, ArrayList<DataModelClass> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<DataModelClass> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<DataModelClass> articles) {
        this.articles = articles;
    }
}
