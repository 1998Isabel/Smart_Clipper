import speech_recognition as sr
from textblob import TextBlob
class SpeechProccessor:
    def __init__(self):
        self.r = sr.Recognizer()
        self.r.pause_threshold = 0.5
        self.r.energy_threshold =2000

    def getAudio(self):
        with sr.Microphone() as source:
            try:
                return self.r.listen(source,phrase_time_limit=10)
            except:
                pass
    def getN(self,sen):
            print ("Processing Speech...")
            try:
                text = self.r.recognize_google(sen)
                print(text)
                blob = TextBlob(text)
                print("Google Speech Recognition thinks you said " + text)
                return blob.noun_phrases
            except sr.UnknownValueError:
                print("Google Speech Recognition could not understand audio")
            except sr.RequestError as e:
                print("Could not request results from Google Speech Recognition service; {0}".format(e))
            except KeyboardInterrupt:
                pass
            return []
