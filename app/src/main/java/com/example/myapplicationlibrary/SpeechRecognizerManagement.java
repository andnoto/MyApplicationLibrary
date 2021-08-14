package com.example.myapplicationlibrary;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import java.util.ArrayList;
import java.util.Locale;

public class SpeechRecognizerManagement extends Activity {
    private static SpeechRecognizer speechRecognizer;
    private static Intent speechRecognizerIntent;
    public static void startSpeechRecognizer (Context context,
                                              String callbackGameObject,
                                              String resultCallbackMethod,
                                              String errorCallbackMethod,
                                              String readyCallbackMethod,
                                              String beginCallbackMethod) {
        // debug
        // String editText = "messaggio da UnitySendMessage";
        // com.unity3d.player.UnityPlayer.UnitySendMessage(callbackGameObject, "ReceiveDebug",editText );
        //
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        //
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
            }
            @Override
            public void onBeginningOfSpeech() {
                String editText = "Listening..";
                com.unity3d.player.UnityPlayer.UnitySendMessage(callbackGameObject, beginCallbackMethod,editText );
            }
            @Override
            public void onRmsChanged(float v) {
            }
            @Override
            public void onBufferReceived(byte[] bytes) {
            }
            @Override
            public void onEndOfSpeech() {
            }
            @Override
            public void onError(int i) {
            }
            @Override
            public void onResults(Bundle bundle) {
                //
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String editText = data.get(0);
                com.unity3d.player.UnityPlayer.UnitySendMessage(callbackGameObject, resultCallbackMethod,editText );
            }
            @Override
            public void onPartialResults(Bundle bundle) {
            }
            @Override
            public void onEvent(int i, Bundle bundle) {
            }
        });
        speechRecognizer.startListening(speechRecognizerIntent);
    }
}
