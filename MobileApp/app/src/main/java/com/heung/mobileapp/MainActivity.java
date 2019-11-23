package com.heung.mobileapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.heung.mobileapp.service.SpeechRecognitionAssistance;
import com.heung.mobileapp.service.TextToSpeechAssistance;


public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private TextToSpeechAssistance myTTS;
    private SpeechRecognitionAssistance SRA;
    public static boolean isListening = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){
            setContentView(R.layout.activity_main);
        }

        SRA = new SpeechRecognitionAssistance(this);
        myTTS = new TextToSpeechAssistance(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void promptUser(View view){

        if (!isListening){
            isListening = true;
            myTTS.speak("What would you like to find?");
            myTTS.listenToResponseAfter(SRA);
        }
    }

    public void openCamera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    public void onClick (View view) {
        startActivity(new Intent(MainActivity.this, RecordingActivity.class));
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            ImageView imgTaken = findViewById(R.id.imageTaken);
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imgTaken.setImageBitmap(imageBitmap);
//        }
//    }

}
