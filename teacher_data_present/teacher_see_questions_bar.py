import pandas as pd
import sys
import pylab
from collections import Counter
import matplotlib.pyplot as plt
import matplotlib.animation as animation
from IPython.display import *
import requests as ur
import time
import json

REQUEST_URL = "http://163.28.17.68:5000/teacher/show/student/question"

def request_json_parse():
    contents = ur.get(REQUEST_URL)
    return contents.content

def dynamic_draw(df):
    plt.close()
    df.plot(kind = 'bar')
    plt.pause(0.05)

if __name__ == '__main__':
    plt.ion()
    #plt.gcf() to get current figure
    # plt.gca() to get current axis
    request_previous_size = 0
    request_json_list = []
    while 1:
        while 1:
            request_json = request_json_parse()  # request data is string
            request_json_list = json.loads(request_json)
            # request_json_list.append("Dog")
            if request_previous_size != len(request_json_list):
                request_previous_size = len(request_json_list)
                print("The size of the new request_json_list", len(request_json_list))
                break
            else:
                # print("Detect the request list size again!!")
                print("Current size: ", len(request_json_list))
                time.sleep(1)
        request_json_list_counter = Counter(request_json_list)
        df = pd.DataFrame.from_dict(request_json_list_counter, orient = 'index')

        print(df)

        dynamic_draw(df)



#http://163.28.17.68:5000/variable/fuck
