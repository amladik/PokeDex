package com.example.firebasetextrecognition;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class pokedexentry extends AppCompatActivity {
    TextView name;
    static TextView height;
    static TextView hp;
    static TextView attack;
    static TextView speed;
    static TextView defense;
    static TextView weight;
    static LinearLayout stats;
    static TextView indexNumber;
    static ImageView typePic;
    static TextView type;
    static TextView ability;
    static String frontURL;
    static LinearLayout genInfo;
    static LinearLayout linearLayout;
    static ConstraintLayout mainLayout;
    static int pokeID;
    static ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedexentry);
         i = findViewById(R.id.iconic);
         linearLayout = findViewById(R.id.statsInfo);
         defense = findViewById(R.id.defense);
         hp = findViewById(R.id.hp);
         defense = findViewById(R.id.defense);
         stats = findViewById(R.id.statsInfo);
         attack = findViewById(R.id.attack);
         speed = findViewById(R.id.speed);
        name = findViewById(R.id.name);
        type = findViewById(R.id.type);
        typePic = findViewById(R.id.typepicture);
        height = findViewById(R.id.height);
        genInfo = findViewById(R.id.genInfoLayout);
        mainLayout = findViewById(R.id.mainLayout);
        weight = findViewById(R.id.weight);
        indexNumber = findViewById(R.id.index);
        ability = findViewById(R.id.ability);
        frontURL = "";
       // pokeID = 666;

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(openFileInput("info.json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = null;
        try {
            s = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONArray newList = new JSONArray(s);
            name.setText(newList.getJSONObject(0).getString("string1"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
       // name.setText(getIntent().getStringExtra("pokemonName"));
        fetchHeight.poke = name.getText().toString().toLowerCase();
        fetchHeight process = new fetchHeight();
        process.execute();


        fetchWeight.poke = name.getText().toString().toLowerCase();
        fetchWeight process2 = new fetchWeight();
        process2.execute();


        fetchdefPic.poke = name.getText().toString().toLowerCase();
        fetchdefPic process3 = new fetchdefPic();
        process3.execute();
        ability.setText(pokeID+"pepe");

        fetchType.poke = name.getText().toString().toLowerCase();

        fetchType typee = new fetchType();
        typee.execute();

        fetchAbility.poke = name.getText().toString().toLowerCase();
        fetchAbility fetchAbility = new fetchAbility();
        fetchAbility.execute();

        fetchHP.poke = name.getText().toString().toLowerCase();
        fetchHP chAbility = new fetchHP();
        chAbility.execute();

        fetchAttack.poke = name.getText().toString().toLowerCase();
        fetchAttack hAbility = new fetchAttack();
        hAbility.execute();

        fetchDefense.poke = name.getText().toString().toLowerCase();
        fetchDefense bility = new fetchDefense();
        bility.execute();

        fetchSpeed.poke = name.getText().toString().toLowerCase();
        fetchSpeed ch= new fetchSpeed();
        ch.execute();






    }
}
