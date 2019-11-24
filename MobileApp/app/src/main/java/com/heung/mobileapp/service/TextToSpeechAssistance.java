package com.heung.mobileapp.service;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.heung.mobileapp.MainActivity;
import com.heung.mobileapp.RecordingActivity;

import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TextToSpeechAssistance {
    private TextToSpeech TTS;

    public TextToSpeechAssistance(final AppCompatActivity mainActivity) {
        this.TTS = new TextToSpeech(mainActivity, status -> {
            if (TTS.getEngines().size() == 0){
                System.out.println("No Text To Speech Support Allowed!");
                mainActivity.finish();
            }
            TTS.setLanguage(Locale.US);
            if (mainActivity instanceof MainActivity){
                speak("Welcome to A-Eye! Please touch anywhere on the screen to begin!");
            }
            if (mainActivity instanceof RecordingActivity){
                speak("Please move your camera slowly.");
            }
        });
    }

    public void speak(String info) {
        TTS.speak(info, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void listenToResponseAfter(SpeechRecognitionAssistance SRA){
        final Handler h = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                if (!TTS.isSpeaking()) {
                    SRA.startListening();
                    return;
                }
                h.postDelayed(this, 1000);
            }
        };
        h.postDelayed(r, 1000);
    }

}
