package com.heung.mobileapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.heung.mobileapp.service.SpeechRecognitionAssistance;
import com.heung.mobileapp.service.TextToSpeechAssistance;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    private TextToSpeechAssistance myTTS;
    private SpeechRecognitionAssistance SRA;
    public static boolean isListening = false;

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

    public void promptUser(View view){
        if (!isListening){
            isListening = true;
            myTTS.speak("What would you like to find?");
            myTTS.listenToResponseAfter(SRA);
        }
    }

    public void openCamera(String toPass){
        startActivity(new Intent(MainActivity.this, RecordingActivity.class).putExtra("selected", toPass));
    }
}
