package com.example.firebasetextrecognition;

import android.os.AsyncTask;

import org.json.JSONArray;
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

public class fetchAttack extends AsyncTask<Void,Void,Void> {
    ArrayList<JSONObject> arrayList = new ArrayList<>();
    public static String poke;
    JSONObject JA;
    JSONArray moves;
    JSONObject object2;
    String data = "";
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/"+ poke + "/");


            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = br.readLine();
                data += line;
            }

            JA = new JSONObject(data);
            if (JA!= null) {
                moves = JA.getJSONArray("stats");
                for (int x = 0; x < moves.length(); x++) {
                    arrayList.add(moves.getJSONObject(x));
                }


                object2 = arrayList.get(5).getJSONObject("stat");


            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (JA!= null) {

            try {
                pokedexentry.attack.setText("Base Attack: \n" + arrayList.get(4).getString("base_stat"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
