package com.example.firebasetextrecognition;

import android.os.AsyncTask;
import android.util.Log;

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

public class fetchWeight extends AsyncTask<Void,Void,Void> {
    ArrayList<JSONObject> arrayList = new ArrayList<>();
    public static String poke;
    JSONObject JA;

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
        try {
            if (JA!= null) {
                String x = JA.getString("weight");
                int num = Integer.parseInt(x);
                num/=10;

                pokedexentry.weight.setText("Weight: \n" + num + " kg");
                //Log.d("height", JA.getString("height"));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
