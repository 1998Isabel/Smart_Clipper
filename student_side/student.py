import RPi.GPIO as GPIO
import time
import requests

GPIO.setmode(GPIO.board)

GPIO.setmode(GPIO.BOARD)   

GPIO.setup(11, GPIO.IN)

while True:
   inputValue = GPIO.input(11)
   if inputValue:
      print("Button pressed ")
      requests.get(http://163.28.17.68:5000/student/button/click)
      while inputValue ==  False:  
            time.sleep(0.3)   