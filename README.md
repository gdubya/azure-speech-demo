# Azure Cognitive Services Speech Demo

This is a small demo project to try out the Azure Cognitive Services Speech API.
In addition to general testing of how it performs with English input, I'm also interested to see how it handles Norwegian, especially my gebrokkent Norsk (Bokmål - no support for Nynorsk yet).

## Microphone Demo

Source: [MicDemo.java](src/main/java/com/garethwestern/azure/speech/MicDemo.java)

### Instructions

* Set an environment variable named "SUBSCRIPTION_KEY" with the value of your Speech API key.
* After upgrading to client-sdk 1.10 it is now necessary to also set the "SSL_CERT_DIR" environment variable.
  * In my case (Fedora 31) the value has to be `/etc/pki/tls/certs/ca-bundle.crt`
  * See [How to configure OpenSSL](https://docs.microsoft.com/en-us/azure/cognitive-services/speech-service/how-to-configure-openssl-linux) for more details.
* Run the MicDemo main method.
  * You may have to update the deviceId for your microphone. See the Azure documentation for tips on how to do this.
* Start speaking in either English or Norwegian
* Text will be translated at the end of each sentence (when a pause is detected).
* Say "stopp" to terminate the process.

## TODO

* Add demo to get input from a recorded file
* Add demo to get input from a recorded file in a data lake
* Try out other API features (e.g. profanity filter)
* Try out techniques for improving WER
* Send the transcribed output somewhere down a pipeline for further processing and/or storage.