package com.garethwestern.azure.speech;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;

import static java.lang.System.err;
import static java.lang.System.getenv;
import static java.lang.System.out;

public class MicDemo {

    public static void main(String[] args) {
        var speechSubscriptionKey = getenv("SUBSCRIPTION_KEY");
        var serviceRegion = "westeurope";
        var speechConfig = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);

        // Your microphone input device Id may differ. Use the following to list all deviceIds
//        Arrays.stream(AudioSystem.getMixerInfo()).map(Mixer.Info::getName).forEach(out::println);

        var audioConfig = AudioConfig.fromMicrophoneInput("plughw:0,0");
        // The following is deprecated. See https://github.com/MicrosoftDocs/azure-docs/issues/
        speechConfig.setSpeechRecognitionLanguage("nb-NO");
        // Once that issue is resolved then we can use the following (or, better yet, enable autorecognition of input language
//        var sourceLanguageConfig = SourceLanguageConfig.fromLanguage("nb-NO");
        var recognizer = new SpeechRecognizer(speechConfig, audioConfig);

        recognizer.recognized.addEventListener((o, speechRecognitionEventArgs) -> {
            var s = speechRecognitionEventArgs.getResult().getText();
            out.println("Recognized: " + s);
            if (s.equalsIgnoreCase("stopp")) {
                recognizer.stopContinuousRecognitionAsync();
                recognizer.close();
            }
        });

        // We could also add an eventListener for the "recognizing" even
        recognizer.canceled.addEventListener((o, speechRecognitionCanceledEventArgs) -> {
            err.println("Something went wrong: " + speechRecognitionCanceledEventArgs.getErrorDetails());
        });

        out.println("Start talking");
        recognizer.startContinuousRecognitionAsync();
    }
}
