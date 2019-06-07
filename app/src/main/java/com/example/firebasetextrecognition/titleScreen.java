package com.example.firebasetextrecognition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;


public class titleScreen extends AppCompatActivity {
ImageView img;
Button recog;
Button list;
Button team;
ImageView setting;
ConstraintLayout fillet;
ArrayList<String> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        img = findViewById(R.id.imageView);
        fillet = findViewById(R.id.filayyyyyyyy);
        recog = findViewById(R.id.button2);
        setting = findViewById(R.id.setting);
        list = findViewById(R.id.mylist);
        team = findViewById(R.id.dreamteam);

        fillet.setBackgroundResource(R.drawable.titlebackground);

        recog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRecog = new Intent(titleScreen.this, MainActivity.class);
                startActivity(openRecog);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                Intent openList = new Intent(titleScreen.this, pokeList.class);
                openList.putStringArrayListExtra("PokeList", pokemonList);
                startActivity(openList);
            }
        });

        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTeams = new Intent(titleScreen.this, myTeams.class);
                startActivity(openTeams);
            }
        });







    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        pokemonList = gson.fromJson(json,type);

        if(pokemonList == null){
            pokemonList = new ArrayList<>();
        }
    }



}

