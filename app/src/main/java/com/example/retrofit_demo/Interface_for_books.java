package com.example.retrofit_demo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Interface_for_books {
   // volumes?q=android&maxResults=5
    @GET("volumes")
    Call<Example> getdata(@QueryMap Map<String, String> parameter);
}
