package com.dadashow.retrofitjava.service;

import com.dadashow.retrofitjava.model.CyrptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CyrptoAPI {

    @GET("prices?key=0e2e1db9ec63d52fcfb617b03193706b")
    Call<List<CyrptoModel>> getData();
}
