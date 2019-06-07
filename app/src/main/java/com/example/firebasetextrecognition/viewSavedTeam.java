package com.example.firebasetextrecognition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class viewSavedTeam extends AppCompatActivity {

    ListView lv;
    TextView textView;
    ConstraintLayout back;
    ArrayList<String> ting;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_team);
        button = findViewById(R.id.buttpoop);
        button.setBackgroundColor(Color.DKGRAY);
        button.setTextColor(Color.RED);
        back = findViewById(R.id.lool);
        back.setBackgroundColor(Color.BLACK);
        textView = findViewById(R.id.titleee);
        textView.setTextColor(Color.RED);
        lv = findViewById(R.id.lister);
        lv.setBackgroundColor(Color.WHITE);
        textView.setText(getIntent().getStringExtra("entry"));

        loadTeamData();
        Log.d("name", ting.toString());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ting );

        lv.setAdapter(arrayAdapter);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent openEntry = new Intent(viewSavedTeam.this,pokedexentry.class);
                    openEntry.putExtra("pokemonName",ting.get(position));
                    startActivity(openEntry);
                }
            });


    }

    private void loadTeamData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(textView.getText().toString(), null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ting = gson.fromJson(json,type);

        if(ting == null){
            ting = new ArrayList<>();
            ting.add("Poop");
        }
    }
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myTeams.teams);
        editor.putString("teams", json);
        editor.apply();
    }
}
