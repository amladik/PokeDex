package com.example.firebasetextrecognition;

import android.graphics.Color;
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

public class fetchType extends AsyncTask<Void,Void,Void> {
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
                moves = JA.getJSONArray("types");
                for (int x = 0; x < moves.length(); x++) {
                    arrayList.add(moves.getJSONObject(x));
                }


                object2 = arrayList.get((int) (Math.random() * moves.length())).getJSONObject("type");


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
                pokedexentry.type.setText(object2.getString("name").toUpperCase());
                Log.d("type", object2.getString("name"));

                if (object2.getString("name").equals( "fighting")) {

                    pokedexentry.typePic.setImageResource(R.drawable.fightingtype);

                    pokedexentry.mainLayout.setBackgroundColor(Color.rgb(160,82,45));

                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(200,111,45));

                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(200,111,45));




                }

                if (object2.getString("name").equals( "fire")) {

                    pokedexentry.typePic.setImageResource(R.drawable.firetype);
                    pokedexentry.mainLayout.setBackgroundColor(Color.rgb(255,0,5));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(255,75,0));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(255,75,0));

                }

                if (object2.getString("name").equals( "ice")) {

                    pokedexentry.typePic.setImageResource(R.drawable.icetype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(173,216,230));
                    pokedexentry.genInfo.setBackgroundColor(Color.WHITE);
                    pokedexentry.linearLayout.setBackgroundColor(Color.WHITE);


                }

                if (object2.getString("name").equals( "water")) {

                    pokedexentry.typePic.setImageResource(R.drawable.watertype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(0,191,255));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(240,248,255));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(240,248,255));
                }


                if (object2.getString("name").equals( "psychic")) {

                    pokedexentry.typePic.setImageResource(R.drawable.psychictype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(186,85,211));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(230,230,250));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(230,230,250));
                }

                if (object2.getString("name").equals( "electric")) {

                    pokedexentry.typePic.setImageResource(R.drawable.electrictype);

                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(255,255,0));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(255,255,100));
                   pokedexentry.linearLayout.setBackgroundColor(Color.rgb(255,255,100));


                }

                if (object2.getString("name").equals( "steel")) {

                    pokedexentry.typePic.setImageResource(R.drawable.steeltype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(211,211,211));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(220,220,220));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(220,220,220));

                }

                if (object2.getString("name").equals( "poison")) {

                    pokedexentry.typePic.setImageResource(R.drawable.poisontype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(28,0,128));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(28,0,160));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(28,0,160));


                }

                if (object2.getString("name").equals( "ground")) {

                    pokedexentry.typePic.setImageResource(R.drawable.groundtype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(222,184,135));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(250,235,215));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(250,235,215));


                }

                if (object2.getString("name").equals( "rock")) {

                    pokedexentry.typePic.setImageResource(R.drawable.rocktype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(222,184,135));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(250,235,215));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(250,235,215));


                }


                if (object2.getString("name").equals( "dark")) {

                    pokedexentry.typePic.setImageResource(R.drawable.darktype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(75,0,130));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(150,0,150));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(150,0,150));


                }

                if (object2.getString("name").equals( "grass")) {

                    pokedexentry.typePic.setImageResource(R.drawable.grasstype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(0,250,154));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(152,251,152));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(152,251,152));


                }

                if (object2.getString("name").equals( "bug")) {

                    pokedexentry.typePic.setImageResource(R.drawable.bugtype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(0,250,154));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(152,251,152));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(152,251,152));


                }

                if (object2.getString("name").equals( "fairy")) {

                    pokedexentry.typePic.setImageResource(R.drawable.fairytype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(255,182,193));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(255,192,203));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(255,192,203));

                }

                if (object2.getString("name").equals( "normal")) {

                    pokedexentry.typePic.setImageResource(R.drawable.normaltype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(255,255,230));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(250,235,215));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(250,235,215));


                }

                if (object2.getString("name").equals( "dragon")) {

                    pokedexentry.typePic.setImageResource(R.drawable.dragontype);
                    pokedexentry.mainLayout.setBackgroundColor( Color.rgb(255,255,230));
                    pokedexentry.genInfo.setBackgroundColor(Color.rgb(255,75,0));
                    pokedexentry.linearLayout.setBackgroundColor(Color.rgb(255,75,0));


                }




            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
