package com.heung.mobileapp.service;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.heung.mobileapp.MainActivity;
import com.heung.mobileapp.RecordingActivity;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SpeechRecognitionAssistance {
    private SpeechRecognizer speechRecognizer;
    private AppCompatActivity main;

    public SpeechRecognitionAssistance(AppCompatActivity mainActivity){
        main = mainActivity;
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
                    MainActivity.isListening = true;
                }

                @Override
                public void onPartialResults(Bundle partialResults) { }

                @Override
                public void onEvent(int eventType, Bundle params) { }

                @Override
                public void onResults(Bundle results) {
                    processSpeech(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0));
                }
            });
        }
    }


    private void processSpeech(String speech){
        String toPass;
        if (main instanceof MainActivity){
            if (speech.contains("c")){
                toPass = "cup";
            } else if (speech.contains("m")){
                toPass = "mouse";
            } else {
                toPass = "pen";
            }
            MainActivity.isListening = false;
            ((MainActivity)main).openCamera(toPass);
        } else {
            if (speech.contains("y")){
                toPass = "yes";
            } else {
                toPass = "no";
            }
            RecordingActivity.isListening = false;
            
            if(toPass.equals("yes")){
                try{
                    ServerAPIManager.sendCallMessageToAll();
                }
                catch (Exception eCall){
                    try {
                        ServerAPIManager.sendTextMessageToAll();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(toPass);
        }
    }
}

    public void startListening(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        speechRecognizer.startListening(intent);
    }
}
