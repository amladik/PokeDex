package com.example.firebasetextrecognition;

import android.os.AsyncTask;

import com.squareup.picasso.Picasso;

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

public class fetchdefPic extends AsyncTask<Void,Void,Void> {

    public static String poke;
    JSONObject JA;
    JSONArray moves;
    String picURL = "";
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



            object2 = JA.getJSONObject("sprites");





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

                   String x = JA.getString("id");
                   pokedexentry.pokeID = Integer.parseInt(x);
                   pokedexentry.indexNumber.setText("ID Number: \n"+ Integer.parseInt(x)+"");


                    Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+Integer.parseInt(x)+".png").into(pokedexentry.i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }





    }
}
