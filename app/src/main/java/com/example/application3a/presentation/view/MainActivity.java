package com.example.application3a.presentation.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application3a.Constants;
import com.example.application3a.Singletons;
import com.example.application3a.R;
import com.example.application3a.data.AtomeApi;
import com.example.application3a.presentation.model.Atome;
import com.example.application3a.presentation.model.RestAtomeResponse;
import com.example.application3a.presentation.controller.MainController;
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

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        controller = new MainController(
                this,Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext())
        );
        controller.onStart();


}


    public void showList(List<Atome> atomeList){
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

    public void toastListSave(){
        Toast.makeText(this, "List saved", Toast.LENGTH_SHORT).show();
    }
    public void showError(){
        Toast.makeText(getApplicationContext(),"API ERROR", Toast.LENGTH_SHORT).show();
    }
}
