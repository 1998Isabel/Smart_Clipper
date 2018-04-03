import time
import requests as ur
import json
from SpeechProccesor_GoogleT import SpeechProccessor as Sp
import _thread

# Define Constants
REQ_PERIOD = 1.0
BASE_URL = "http://163.28.17.68:5000/"
REQ_URL = "teacher/request"
RESET_URL = "teacher/reset/state"
RETURN_URL = "variable/"

class TeacherPi:
    def __init__(self):
        print ("Starting...")
        self.sp = Sp()
        self.keywordlist = []
    def run(self):
        while True :
            print ("Getting Speech...")
            sentence = self.sp.getAudio()
            print ("Getting State...")
            stuList = self.get_student_state()
            if stuList:
                (self.return_result(stuList,sentence))
            print (self.keywordlist)
            #time.sleep(REQ_PERIOD)
    def get_student_state(self):
        def process_json(text):
            req_list = []
            try:
                json_file = json.loads(text)
                for id in json_file:
                    if json_file[id] == 1:
                        req_list.append(id)
                print (req_list)
                ur.get(BASE_URL+RESET_URL)
                return req_list
            except:
                return None
        try:
            con = ur.get(BASE_URL+REQ_URL)
            return process_json(con.text)
        except ur.exceptions.ConnectionError:
            print ("Connection Error !!!")
            return None
    def return_result(self,lis,sen):
        self.keywordlist.append(self.sp.getN(sen))
        for student in lis:
            for work in self.keywordlist[-1]:
                ur.get(BASE_URL+RETURN_URL+work)

if __name__ == "__main__":
    program = TeacherPi()
    program.run()
