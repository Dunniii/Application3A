package com.example.application3a.presentation.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application3a.Constants;
import com.example.application3a.Singletons;
import android.content.Intent;
import com.example.application3a.R;
import com.example.application3a.data.AtomeApi;
import com.example.application3a.presentation.model.Atome;
import com.example.application3a.presentation.model.RestAtomeResponse;
import com.example.application3a.presentation.controller.MainController;
import com.example.application3a.presentation.controller.DescriptionController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DescriptionActivity extends AppCompatActivity{
    private TextView titre;
    private TextView description;
    private ImageView icon;
    private Button button;
    private DescriptionController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titre = findViewById(R.id.titre);
        description = findViewById(R.id.textDescription);
        icon = findViewById(R.id.imageView);
        button = findViewById(R.id.bouton_retour);

        Intent intent = getIntent();
        String atomeJson = intent.getStringExtra(Constants.EXTRA_ATOME);
        Atome atome = Singletons.getGson().fromJson(atomeJson, Atome.class);

        controller = new DescriptionController(this, atome);
        controller.onStart();



        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                controller.onButtonClick();
            }
        });

    }

    public void showDetail(Atome atome){
        titre.setText(atome.getName());
        description.setText(atome.getDescription());
        Picasso.get().load(atome.getUrl()).fit().into(icon);
    }

    public void backToList(){
        finish();
    }


}
