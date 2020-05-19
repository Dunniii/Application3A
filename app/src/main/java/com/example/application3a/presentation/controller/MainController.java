package com.example.application3a.presentation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application3a.Constants;
//import com.example.application3a.ListAdapter;
import com.example.application3a.R;
import com.example.application3a.data.AtomeApi;
import com.example.application3a.presentation.model.Atome;
import com.example.application3a.presentation.model.RestAtomeResponse;
import com.example.application3a.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;



import android.widget.Toast;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;



    public MainController(MainActivity  mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson= gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){




        List<Atome> atomeList =getDataFromCache();

        if (atomeList != null) {
            view.showList(atomeList);
        } else {
            makeApiCall ();
        }

    }

    private void makeApiCall(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AtomeApi atomeApi = retrofit.create(AtomeApi.class);

        Call<RestAtomeResponse> call = atomeApi.getAtomeResponse();
        call.enqueue( new Callback<RestAtomeResponse> (){
            @Override
            public void onResponse(Call<RestAtomeResponse> call, Response<RestAtomeResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Atome> atomeList= response.body().getResults();
                    view.showList(atomeList);
                    saveList(atomeList);
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestAtomeResponse> call, Throwable t) {
                view.showError();
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

        view.toastListSave();
    }

    private List<Atome> getDataFromCache(){
        String jsonAtome =  sharedPreferences.getString( Constants.KEY_ATOME_LIST, null);

        if(jsonAtome == null){
            return null;
        }else {
            Type ListType = new TypeToken<List<Atome>>(){}.getType ();
            return gson.fromJson(jsonAtome, ListType );
        }

    }

   /* public  void onItemClick(Atome atome){

    }*/


}
