package com.example.firebasetextrecognition;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class pokeList extends AppCompatActivity {
    List<String> pokeList;
    static TextView height;
    ConstraintLayout layout;
    private ListView lv;
    static JSONArray list;
    String pokeremovename;
    ImageView home;
    Button recog;
    EditText removeText;
    Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_list);
//        removeButton = findViewById(R.id.removeButton);
 //       removeText = findViewById(R.id.removeName);

        layout = findViewById(R.id.filay);
        pokeList = getIntent().getStringArrayListExtra("PokeList");
        height = findViewById(R.id.height);
//        Log.d("List", pokeList.toString());
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(pokeList.this, titleScreen.class);
                startActivity(goHome);

            }
        });


        lv = findViewById(R.id.pokeListView);
        lv.setBackgroundColor(Color.WHITE);
        layout.setBackgroundColor(Color.BLACK);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pokeList );

        lv.setAdapter(arrayAdapter);


        recog = findViewById(R.id.recognize);
        recog.setBackgroundColor(Color.DKGRAY);
        recog.setTextColor(Color.RED);

        recog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recogAgain = new Intent(pokeList.this, MainActivity.class);

                MainActivity.pokemonList = (ArrayList<String>)pokeList;
                Log.d("listeg", MainActivity.pokemonList.toString());
                recogAgain.putStringArrayListExtra("pokemonList", (ArrayList<String>)pokeList);
                startActivity(recogAgain);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("Selected", "Selected");
                view.setBackgroundColor(Color.RED);


                Intent openEntry = new Intent(pokeList.this,pokedexentry.class);
              //  openEntry.putExtra("pokemonName",pokeList.get(position));
                String s = pokeList.get(position);
                JSONObject JSONstrings = new JSONObject();
                list = new JSONArray();
                try {
                    JSONstrings.put("string1", "" + s);
                    JSONstrings.put("string2", "" + s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                list.put(JSONstrings);
                OutputStreamWriter writer = null;
                try {
                    writer = new OutputStreamWriter(openFileOutput("info.json", MODE_PRIVATE));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    writer.write(list.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                list.put(JSONstrings);
                startActivity(openEntry);
                view.setBackgroundColor(Color.WHITE);
                }

        });





    }

}
