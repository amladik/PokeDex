package com.example.firebasetextrecognition;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class allPokemon extends AppCompatActivity{
    private ArrayList<String> allPokemon;
     Button button;
     ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pokemon);
        button = findViewById(R.id.button7);
        listView = findViewById(R.id.allPoke);
        allPokemon = new ArrayList<>();
        final getName getName = new getName();
        getName.execute();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                allPokemon );
            listView.setAdapter(arrayAdapter);







    }
    public class getName extends AsyncTask<Void, Void, Void> {
        String data ="";
        JSONObject JA;
        String name = "";

        @Override
        protected Void doInBackground(Void... voids) {
            for (int x = 1; x<10;x++){
                try {
                    URL url = new URL("https://pokeapi.co/api/v2/pokemon/"+ x + "/");
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while (line != null){
                        line = br.readLine();
                        data += line;
                    }

                    JA = new JSONObject(data);
                    name = JA.getString("name");
                    allPokemon.add(name);
                    Log.d("list", line);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            return null;
        }
    }
}



