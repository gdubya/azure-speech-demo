package com.garethwestern.azure.speech;

import com.microsoft.cognitiveservices.speech.AutoDetectSourceLanguageConfig;
import com.microsoft.cognitiveservices.speech.SourceLanguageConfig;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;

import java.util.List;

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
        var englishConfig = SourceLanguageConfig.fromLanguage("en-US");
        var norskConfig = SourceLanguageConfig.fromLanguage("nb-NO");
        var sourceLanguageConfig = AutoDetectSourceLanguageConfig.fromSourceLanguageConfigs(List.of(englishConfig, norskConfig));
        var recognizer = new SpeechRecognizer(speechConfig, sourceLanguageConfig, audioConfig);

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
