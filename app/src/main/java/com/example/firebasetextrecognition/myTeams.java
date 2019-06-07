package com.example.firebasetextrecognition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class myTeams extends AppCompatActivity {

    EditText editText;
    Button newButton;
    ListView listView;
    ImageView home;
    String x;
    ConstraintLayout layout;
    static ArrayList<String> teams = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_teams);
        layout = findViewById(R.id.ronit);
        home = findViewById(R.id.homie);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(myTeams.this, titleScreen.class);
                startActivity(goHome);
            }
        });
        layout.setBackgroundColor(Color.BLACK);
        newButton = findViewById(R.id.button8);
        editText = findViewById(R.id.editText2);
        editText.setBackgroundColor(Color.DKGRAY);
        editText.setTextColor(Color.RED);
        listView = findViewById(R.id.lister);
        listView.setBackgroundColor(Color.WHITE);
        loadTeamData();
        newButton.setTextColor(Color.RED);
        newButton.setBackgroundColor(Color.DKGRAY);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent openViewSavedTeams = new Intent(myTeams.this, viewSavedTeam.class);
                openViewSavedTeams.putExtra("entry", teams.get(position));
                startActivity(openViewSavedTeams);
            }
        });



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                teams );

        listView.setAdapter(arrayAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    x = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myTeams.this, team.class);
                intent.putExtra("Nameo", x);

                    startActivity(intent);
                }

        });

    }



    private void loadTeamData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("teams", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        teams = gson.fromJson(json,type);

        if(teams == null){
            teams = new ArrayList<>();
        }
    }
}
