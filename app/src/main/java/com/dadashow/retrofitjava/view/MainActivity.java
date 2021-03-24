package com.dadashow.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dadashow.retrofitjava.R;
import com.dadashow.retrofitjava.adapter.RecyclerViewAdapter;
import com.dadashow.retrofitjava.model.CyrptoModel;
import com.dadashow.retrofitjava.service.CyrptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CyrptoModel> cyrptoModels;
    String BASE_URL="https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson=new GsonBuilder().setLenient().create();
        recyclerView=findViewById(R.id.recyclerView);
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }

    private void loadData() {
        CyrptoAPI cyrptoAPI= retrofit.create(CyrptoAPI.class);

        Call<List<CyrptoModel>> call=cyrptoAPI.getData();
        call.enqueue(new Callback<List<CyrptoModel>>() {
            @Override
            public void onResponse(Call<List<CyrptoModel>> call, Response<List<CyrptoModel>> response) {
                if (response.isSuccessful()){
                    List<CyrptoModel> cyrptoModelList=response.body();
                    cyrptoModels=new ArrayList<>(cyrptoModelList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter=new RecyclerViewAdapter(cyrptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<CyrptoModel>> call, Throwable t) {

            }
        });


    }

    //https://api.nomics.com/v1/prices?key=0e2e1db9ec63d52fcfb617b03193706b
}