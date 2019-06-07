package  com.example.firebasetextrecognition;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebasetextrecognition.Helper.GraphicOverlay;
import com.example.firebasetextrecognition.Helper.TextGraphic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    CameraView cameraView;
   static ArrayList<String> pokemonList = new ArrayList<>();
    AlertDialog waitingDialog;
    GraphicOverlay graphicOverlay;
    public static TextView text;
    Button capButton;
    ImageView home;
    TextView textView;
    ConstraintLayout l ;
    static JSONObject pokemonJSON;
    String str2;
    Button addButton;
    Button shuffle;
    static boolean isPokemon  = false;
    static final int CODE = 111;
    int numat;


   static List<FirebaseVisionText.Element> element;


    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
            l = findViewById(R.id.l);
            pokemonJSON = new JSONObject();
            l.setBackgroundColor(Color.BLACK);
//        shuffle = findViewById(R.id.shuffle);
        //text = findViewById(R.id.textView2);
        waitingDialog = new SpotsDialog.Builder().setCancelable(false).setMessage("Fetching Data").setContext(this).build();
        textView = findViewById(R.id.pokename);
        home = findViewById(R.id.homebutt);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(MainActivity.this, titleScreen.class);
                startActivity(goHome);
            }
        });

        textView.setTextColor(Color.RED);
        cameraView = findViewById(R.id.camera_view);
        addButton = findViewById(R.id.addButton);
        graphicOverlay = findViewById(R.id.graphic_overlay);
      //  pokemonList = new ArrayList<>();
        numat = 0;

        capButton = findViewById(R.id.btn_capture);
        capButton.setBackgroundColor(Color.DKGRAY);
        capButton.setTextColor(Color.RED );
        addButton.setBackgroundColor(Color.DKGRAY);
        addButton.setTextColor(Color.RED);
        capButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.start();
                cameraView.captureImage();
                graphicOverlay.clear();
               // pokemonList = getIntent().getStringArrayListExtra("pokemonList");
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openList = new Intent(MainActivity.this, pokeList.class);
                if (pokemonList != null) {

                   // pokemonList.clear();

                    openList.putStringArrayListExtra("PokeList", pokemonList);

                    startActivity(openList);
                }


            }
        });


        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                waitingDialog.show();

                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), false);
                Bitmap newBit = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth()/2, bitmap.getHeight()/5);
                cameraView.stop();

                recognizeText(newBit);


            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });

    }

    private void recognizeText(Bitmap bitmap) {

        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        numat = 0;

        FirebaseVisionCloudTextRecognizerOptions options =
                new FirebaseVisionCloudTextRecognizerOptions.Builder()
                        .setLanguageHints(Arrays.asList("en"))
                        .build();

        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance()
                .getCloudTextRecognizer(options);

        textRecognizer.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        drawTextResult(firebaseVisionText);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("ERROR", e.getMessage());

            }
        });
    }

    private void drawTextResult(FirebaseVisionText firebaseVisionText) {

        List<FirebaseVisionText.TextBlock> blocks = firebaseVisionText.getTextBlocks();

        if(blocks.size() == 0) {

            Toast.makeText(this, "No Text Found", Toast.LENGTH_SHORT).show();
            return;
        }
        graphicOverlay.clear();

        for (int i = 0; i< blocks.size(); i++)
        {

            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
            for(int j = 0; j< lines.size(); j++)
            {
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                element = elements;

                for(int kun = 0 ; kun< elements.size();kun++){


                    TextGraphic textGraphic = new TextGraphic(graphicOverlay,elements.get(kun));
                    graphicOverlay.add(textGraphic);

                }
            }

        }
        if(element.size()>0) {
            String str= element.get(0).getText();
            if(str.contains("0")) {
                 str2 = str.substring(0, str.indexOf("0"));
            }
            if(str.contains("1")) {
                 str2 = str.substring(0, str.indexOf("1"));
            }if(str.contains("2")) {
                 str2 = str.substring(0, str.indexOf("2"));
            }if(str.contains("3")) {
                 str2 = str.substring(0, str.indexOf("3"));
            }if(str.contains("4")) {
                 str2 = str.substring(0, str.indexOf("4"));
            }if(str.contains("5")) {
                 str2 = str.substring(0, str.indexOf("5"));
            }if(str.contains("6")) {
                 str2 = str.substring(0, str.indexOf("6"));
            }if(str.contains("7")) {
                 str2 = str.substring(0, str.indexOf("7"));
            }if(str.contains("8")) {
                 str2 = str.substring(0, str.indexOf("8"));
            }if(str.contains("9")) {
                 str2 = str.substring(0, str.indexOf("9"));
            }
            else{
                 str2 = str;
            }

            textView.setText(str2);
            if (pokemonList.contains(str2)){
                Toast.makeText(MainActivity.this, "ERROR: POKéMON ALREADY IN COLLECTION", Toast.LENGTH_SHORT).show();
            }



            else {

                    pokemonList.add(0, str2.toLowerCase());

                saveData();
            }
        }
        waitingDialog.dismiss();
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(pokemonList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        pokemonList = gson.fromJson(json,type);

        if(pokemonList == null){
            pokemonList = new ArrayList<>();
        }
    }


}
