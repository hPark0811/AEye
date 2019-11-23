package com.heung.mobileapp.service;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import com.heung.mobileapp.MainActivity;

import java.util.List;

public class SpeechRecognitionAssistance {
    private SpeechRecognizer speechRecognizer;

    public SpeechRecognitionAssistance(MainActivity mainActivity){
        if (speechRecognizer.isRecognitionAvailable(mainActivity)){
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(mainActivity);
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) { }

                @Override
                public void onBeginningOfSpeech() { }

                @Override
                public void onRmsChanged(float rmsdB) { }

                @Override
                public void onBufferReceived(byte[] buffer) { }

                @Override
                public void onEndOfSpeech() { }

                @Override
                public void onError(int error) {
                    System.out.println("error!");
                }

                @Override
                public void onPartialResults(Bundle partialResults) {
                    System.out.println("partialResults!");
                }

                @Override
                public void onEvent(int eventType, Bundle params) {
                    System.out.println("params!");
                }

                @Override
                public void onResults(Bundle results) {
                    System.out.println("Called!");
                    processSpeech(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0));
                }
            });
        }
    }

    private void processSpeech(String speech){
        System.out.println("Called!");
        System.out.println(speech);
    }

    public void startListening(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        speechRecognizer.startListening(intent);
    }
}
