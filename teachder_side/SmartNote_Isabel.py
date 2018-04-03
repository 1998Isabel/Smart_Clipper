import speech_recognition as sr
from textblob import TextBlob

# obtain audio from the microphone
r = sr.Recognizer()
r.pause_threshold = 0.5
r.energy_threshold = 500
with sr.Microphone() as source:
    while True:
        audio = r.listen(source,phrase_time_limit=25)
        # recognize speech using Google Speech Recognition
        try:
            # for testing purposes, we're just using the default API key
            # to use another API key, use `r.recognize_google(audio, key="GOOGLE_SPEECH_RECOGNITION_API_KEY")`
            # instead of `r.recognize_google(audio)`
            text = r.recognize_google(audio)
            print(text)
            blob = TextBlob(text)
            blob.tags           # [('The', 'DT'), ('titular', 'JJ'),
                                #  ('threat', 'NN'), ('of', 'IN'), ...]
            #print(blob.tags)

            blob.noun_phrases   # WordList(['titular threat', 'blob',
                                #            'ultimate movie monster',
                                #            'amoeba-like mass', ...])
            print(blob.noun_phrases)
            print(blob.words)
        except sr.UnknownValueError:
            print("Google Speech Recognition could not understand audio")
        except sr.RequestError as e:
            print("Could not request results from Google Speech Recognition service; {0}".format(e))
