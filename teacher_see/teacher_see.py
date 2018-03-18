import pandas as pd
import sys
import pylab
from collections import Counter
from matplotlib.pyplot import *
from IPython.display import *

def dynamic_draw(df):
    # xn = range(df['event'])
    clf()
    df.plot(kind = 'bar')
    # xticks(xn, df['event'])
    ax.relim()
    ax.autoscale_view(True,True,True)
    draw()
    # y=y*1.1
    # plt.pause(0.1)

    # clf() # clear previous picture
    # draw = df.plot(kind = 'bar')
    # show(draw)
    # clear_output(wait = True)
    # close()


if __name__ == '__main__':
    ion()
    f = figure()
    ax = gca()
    ax.set_autoscale_on(True)
    user_inputs = []
    while 1:
        user_input = input()
        user_inputs.append(user_input)
        print(user_inputs)
        user_input_counter = Counter(user_inputs)
        df = pd.DataFrame.from_dict(user_input_counter, orient = 'index')
        # df = df.rename(columns={'index':'event', 0:'count'})
        print(df)
        # print(type(df['count'][0]))
        dynamic_draw(df)
        # ax.plot(df)
        # f.canvas.draw()
        # f.close()
        # pause(0.1)
        
# import numpy as np
# import matplotlib.pyplot as plt

# x = np.arange(0, 10, 0.1)
# y = np.sin(x)

# plt.ion()
# ax = plt.gca()
# ax.set_autoscale_on(True)
# line, = ax.plot(x, y)

# for i in xrange(100):
#     line.set_ydata(y)
#     ax.relim()
#     ax.autoscale_view(True,True,True)
#     plt.draw()
#     y=y*1.1
#     plt.pause(0.1)
