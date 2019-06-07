package com.example.firebasetextrecognition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSmoothScroller;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class listforteam extends AppCompatActivity {
    ArrayList<String> list;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listforteam);
        lv = findViewById(R.id.lv);
        loadData();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list );

        lv.setAdapter(arrayAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("indez", getIntent().getStringExtra("Index"));
                if(getIntent().getStringExtra("Index").equals("1")) {
                    team.text1.setText(list.get(position));
                    finish();



                }

                if(getIntent().getStringExtra("Index").equals("2")) {

                    team.text2.setText(list.get(position));
                    finish();


                }

                if(getIntent().getStringExtra("Index").equals("3")) {

                    team.text3.setText(list.get(position));
                    finish();


                }

                if(getIntent().getStringExtra("Index").equals("4")) {

                    team.text4.setText(list.get(position));
                    finish();


                }


            }
        });



    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        list = gson.fromJson(json,type);

        if(list == null){
            list = new ArrayList<>();
        }
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("teamlist", json);
        editor.apply();
    }

    private void loadTeamData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("teamlist", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        list = gson.fromJson(json,type);

        if(list == null){
            list = new ArrayList<>();
        }
    }

}
