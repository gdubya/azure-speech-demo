# Azure Cognitive Services Speech Demo

This is a small demo project to try out the Azure Cognitive Services Speech API.
In addition to general testing of how it performs with English input, I'm also interested to see how it handles Norwegian, specially my gebrokkent norsk. According to the documentation, there's no support for nynorsk yet.

## Microphone Demo

Source: [MicDemo.java](src/main/java/com/garethwestern/azure/speech/MicDemo.java)

### Instructions

* Set an environment variable named "SUBSCRIPTION_KEY" with the value of your Speech API key.
* Run the MicDemo main method.
  * You may have to update the deviceId for your microphone. See the Azure documentation for tips on how to do this.
* Start speaking in Norwegian
* Text will be translated continuously. The audio will be sent to Azure for translation when a pause is detected in the input.
* Say "stopp" to terminate the process.

### Example

#### Input
Taken from [Aftenposten](https://www.aftenposten.no/meninger/kommentar/i/Jo4apm/norge-kan-stanse-koronaviruset-joacim-lund):
```
Søndag kveld hadde 19 nordmenn testet positivt for koronaviruset.

Kanskje ville viruset funnet veien til Norge uansett. Men at Oslo universitetssykehus skulle bidra så til de grader til spredning av det, er nærmest utilgivelig.
```

#### Result

```
Recognized: Søndag kveld hadde 19 nordmenn testet positivt for kolonna viruset
Recognized: kanskje ville videre seg funnet veien til norge uansett
Recognized: men at oslo universitetssykehus skulle bidra så til de grader til spredning av det er nærmest utilgivelig
Recognized: stopp
```

It's a very small dataset, but the calculated [Word Error Rate (WER)](https://docs.microsoft.com/en-us/azure/cognitive-services/speech-service/how-to-custom-speech-evaluate-data) in this example appears to be only 8.5%, which is pretty good!

|     |              |
|-----|--------------|
| S   | 2            |
| D   | 0            |
| I   | 1            |
| C   | 33           |
| N   | 35           |
| WER | 3/35% = 8.5% |

## TODO - File Demo

Similar to the above, but reading input speech from a file

## TODO - Blob Demo

Similar to the above, but reading the input file from a blob store (kan haz Data Lake?)

## Further Improvements
Send the transcribed output somewhere down a pipeline for further processing and/or storage.