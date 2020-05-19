package com.example.application3a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.application3a.presentation.model.Atome;

import android.widget.Button;

public class DescriptionPage {
    public class DescriptionActivity extends AppCompatActivity {

        private TextView titre;
        private TextView description;
        private ImageView icon;
        private Button button;
        //private DescriptionController controller;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.description);

            titre = findViewById(R.id.titre);
            description = findViewById(R.id.textDescription);
            button = findViewById(R.id.bouton_retour);

            //Intent intent = getIntent();
           // String atomeJson = intent.getStringExtra(Constants.E);


            //controller = new DescriptionController(this, atome);
            //controller.onStart();



            button.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //controller.onButtonClick();
                }
            });

        }

        public void showDetail(Atome atome){
            titre.setText(atome.getName());
            //description.setText(atome.getDescription());
        }

        public void backToList(){
            finish();
        }

    }
}
