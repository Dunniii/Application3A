package com.example.application3a;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;



import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://raw.githubusercontent.com/Dunniii/Application3A/master/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        sharedPreferences = getSharedPreferences ( "application_esiea", Context.MODE_PRIVATE );

        // showList();

        gson = new GsonBuilder ()
                .setLenient ()
                .create ();

        List<Atome> atomeList =getDataFromCache();

        if (atomeList != null) {
            showList(atomeList);
        } else {
        makeApiCall ();
    }

}
    private List<Atome> getDataFromCache(){
        String jsonAtome =  sharedPreferences.getString(Constants.KEY_ATOME_LIST, null);

        if(jsonAtome == null){
            return null;
        }else {
            Type ListType = new TypeToken<List<Atome>>(){}.getType ();
            return gson.fromJson(jsonAtome, ListType );
        }

    }

    private void showList(List<Atome> atomeList){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /*List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }*/
        mAdapter = new ListAdapter(atomeList);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AtomeApi atomeApi = retrofit.create(AtomeApi.class);

        Call<RestAtomeResponse> call = atomeApi.getAtomeResponse();
        call.enqueue( new Callback<RestAtomeResponse> (){
            @Override
            public void onResponse(Call<RestAtomeResponse> call, Response<RestAtomeResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Atome> atomeList= response.body().getResults();
                    showList(atomeList);
                    saveList(atomeList);
                    Toast.makeText(getApplicationContext(),"API Sucess",Toast.LENGTH_SHORT).show();
                }else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestAtomeResponse> call, Throwable t) {
                showError();
            }


        });

    }
    private void saveList(List<Atome> atomeList){
        String jsonString = gson.toJson(atomeList);

        sharedPreferences
                .edit()
                //.putAtome
                .putString(Constants.KEY_ATOME_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(),"List saved", Toast.LENGTH_SHORT).show();
    }
    private void showError(){
        Toast.makeText(getApplicationContext(),"API ERROR", Toast.LENGTH_SHORT).show();
    }
}
