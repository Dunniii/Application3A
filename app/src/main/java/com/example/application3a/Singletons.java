package com.example.application3a;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import android.content.Context;
import com.example.application3a.Constants;
import android.content.SharedPreferences;
//import com.example.application3a.ListAdapter;
import com.example.application3a.R;
import com.example.application3a.data.AtomeApi;
import com.example.application3a.presentation.model.Atome;
import com.example.application3a.presentation.model.RestAtomeResponse;
import com.example.application3a.presentation.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Singletons {

    private static  Gson gsonInstance;
    private static AtomeApi atomeApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient ()
                    .create ();
        }
        return gsonInstance;
    }
    public static AtomeApi getAtomeApi(){
        if(atomeApiInstance== null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            atomeApiInstance = retrofit.create(AtomeApi.class);
        }
        return atomeApiInstance;
    }
    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
