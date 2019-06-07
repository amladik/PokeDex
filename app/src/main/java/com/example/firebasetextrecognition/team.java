package com.example.firebasetextrecognition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class team extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ConstraintLayout layout ;
    static TextView text1;
    static TextView text2;
    static ArrayList<String> teamlist;
    static TextView text3;
    static TextView text4;
    static TextView title;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        button1 = findViewById(R.id.button3);
        button1.setBackgroundColor(Color.DKGRAY);
        button1.setTextColor(Color.RED);
        layout = findViewById(R.id.poopopopopopopopopopo);
        layout.setBackgroundColor(Color.BLACK);
        button2 = findViewById(R.id.button4);
        button2.setBackgroundColor(Color.DKGRAY);
        button2.setTextColor(Color.RED);
        button3 = findViewById(R.id.button5);
        button3.setBackgroundColor(Color.DKGRAY);
        button3.setTextColor(Color.RED);
        title = findViewById(R.id.textView5);
        save = findViewById(R.id.saveData);
        save.setBackgroundColor(Color.WHITE);
        button4 = findViewById(R.id.button6);
        button4.setBackgroundColor(Color.DKGRAY);
        button4.setTextColor(Color.RED);
        text1 = findViewById(R.id.textView);
        text1.setBackgroundColor(Color.DKGRAY);
        text1.setTextColor(Color.RED);
        text2 = findViewById(R.id.textView2);
        text2.setBackgroundColor(Color.DKGRAY);
        text2.setTextColor(Color.RED);
        text3 = findViewById(R.id.textView3);
        text3.setBackgroundColor(Color.DKGRAY);
        text3.setTextColor(Color.RED);
        text4 = findViewById(R.id.textView4);
        text4.setBackgroundColor(Color.DKGRAY);
        text4.setTextColor(Color.RED);
        title.setText(getIntent().getStringExtra("Nameo"));
        loadTeamData();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openList = new Intent(team.this, listforteam.class);
                openList.putExtra("Index",    "1");
                startActivity(openList);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openList = new Intent(team.this, listforteam.class);
                openList.putExtra("Index",    "2");
                startActivity(openList);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openList = new Intent(team.this, listforteam.class);
                openList.putExtra("Index",    "3");
                startActivity(openList);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openList = new Intent(team.this, listforteam.class);
                openList.putExtra("Index",    "4");
                startActivity(openList);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // teamlist= new ArrayList<>();
                teamlist.clear();

                teamlist.add(text1.getText().toString());
                teamlist.add(text2.getText().toString());
                teamlist.add(text3.getText().toString());
                teamlist.add(text4.getText().toString());

                saveData();

                Log.d("indez", teamlist.toString());

                Intent openTeamList = new Intent(team.this, myTeams.class);
                openTeamList.putExtra("Name", title.getText());
                myTeams.teams.add(title.getText().toString());

                saveData();
                saveTeam();
                startActivity(openTeamList);


            }
        });


    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myTeams.teams);
        editor.putString("teams", json);
        editor.apply();
    }

    private void saveTeam(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(teamlist);
        editor.putString(title.getText().toString(), json);
        editor.apply();
    }



    private void loadTeamData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("teams", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        teamlist = gson.fromJson(json,type);

        if(teamlist == null){
            teamlist = new ArrayList<>();
        }
    }
}
