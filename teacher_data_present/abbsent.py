import numpy as np
import matplotlib.pyplot as plt
import requests as ur
import json
#Just call absent_drawer(dic),dic is the dictionary in form
#dic sample {"name":[int for absent,float for happiness]}
def absent_drawer(infor):
	plt.close()
	fig, ax = plt.subplots()
	# Hide axes
	ax.xaxis.set_visible(False) 
	ax.yaxis.set_visible(False)
	collabel=("name", "absent or not", "happy")
	dictlist = []
	for key, value in infor.items():
	    temp = [key,value[0],value[1]]
	    if value[0] == "0":
	    	temp[1] = "absent"
	    else:
	    	temp[1] = "attendent"
	    dictlist.append(temp)
	ax.table(cellText=dictlist,colLabels=collabel,loc='center')
	plt.draw()
	plt.pause(0.05)

if __name__ == "__main__":
	
	
	print("?")
	d = {"a":[1,0.002],"b":[1,0.2],"c":[0,1.3],"d":[0,0.5]}
	tmp = {"student":["0",0]}
	while True:
		
		URL_REQUEST = "http://163.28.17.68:5000/student/attendance/state"
		request_respond = ur.get(URL_REQUEST)
		request_text = request_respond.text
		request_json = json.loads(request_text)
		# print(request_json)
		# print(tmp["student"][0] )
		# print(request_json)
		if tmp["student"][0] != request_json["student_1"][0] or tmp["student"][1] != request_json["student_1"][1]:
			
			tmp["student"][0] = request_json["student_1"][0]
			tmp["student"][1] = request_json["student_1"][1]
			absent_drawer(tmp)
	
	d = {"a":[1,0.002],"b":[1,0.2],"c":[0,1.3],"d":[0,0.5]}
	absent_drawer(d)
	a = input()

