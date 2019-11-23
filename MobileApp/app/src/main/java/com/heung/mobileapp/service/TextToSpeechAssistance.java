package com.heung.mobileapp.service;

import android.os.Build;
import android.speech.tts.TextToSpeech;

import androidx.annotation.RequiresApi;

import com.heung.mobileapp.MainActivity;

import java.util.Locale;

public class TextToSpeechAssistance {
    private TextToSpeech TTS;

    public TextToSpeechAssistance(final MainActivity mainActivity) {
        this.TTS = new TextToSpeech(mainActivity, new TextToSpeech.OnInitListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if (TTS.getEngines().size() == 0){
                    System.out.println("No Text To Speech Support Allowed!");
                    mainActivity.finish();
                }
                TTS.setLanguage(Locale.US);
                speak("Welcome to A-Eye!");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void speak(String info) {
        TTS.speak(info, TextToSpeech.QUEUE_FLUSH, null, null);
    }

}
