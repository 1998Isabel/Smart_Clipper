from flask import Flask, url_for, jsonify
import json
import nlu as nl
from giveUJson.header import jasoner as js
app = Flask(__name__)

# student state
student_state_data = {}
student_state_data["student_1"] = 0
# student_state_data["student_2"] = 1
student_state_json = json.dumps(student_state_data)

# student's question
student_questions = []

# student's attandance status
student_attandance = {}
student_attandance["student_1"] = 1
student_attandance_json = json.dumps(student_attandance)

# student's happiness score
student_happiness_score = {}
student_happiness_score["student_1"] = 1
student_happiness_score_json = json.dumps(student_happiness_score)

#student's happiness score
student_attandence_happiness = {}
student_attandence_happiness["student_1"] = [1, 1]
student_attandance_happiness_json = json.dumps(student_attandence_happiness)


@app.route('/', methods=['GET'])
def hello_world():
    return 'Hello ~ You successfully connected to server!'

# <username> type is path
@app.route('/variable/<username>')
def show_variable(username):
    # show the user profile for that user
    print('----------http://163.28.17.68/variable/%s----------' %username)
    # add to the question list
    global student_questions
    student_questions.append(username)
    print(student_questions)
    # data for cellphone
    question_name.append(username)
    json_list = r.callable(username)
    print(json_list)
    # json_txt = json.dumps(json_list)
    return jsonify(json_list)
# <int:post_id> type is int or post

@app.route('/data/json')
def show_json():
    # show the post with the given id, the id is an integer. To request the student's data
    # cellphone!!
    print('----------http://163.28.17.68/data/json----------')
    return jsonify(r.return_json())

@app.route('/teacher/student/data')
def show_teacher_json():
    # show all the data without definition
    print('----------http://163.28.17.68/teacher/student/data----------')
    print(question_name)
    return jsonify(question_name)

@app.route('/student/button/click')
def student_button_call():
    # when student click the button, it will http request server and change state to 1
    print('----------http://163.28.17.68/student/button/click----------')
    # student_state_data = json.loads(student_state_json)
    # student_state_data["student_1"] = 1
    # student_state_json = json.dumps(student_state_data)
    global student_state_data
    student_state_data["student_1"] = 1
    # student_state_data["student_2"] = 1
    global student_state_json
    student_state_json = json.dumps(student_state_data)
    print(student_state_json)
    return student_state_json

@app.route('/teacher/request')
def teacher_request():
    # receive student's http request in the server.
    print('----------http://163.28.17.68/teacher/request----------')
    print(student_state_json)
    return student_state_json

@app.route('/teacher/reset/state')
def teacher_reset_state():
    # teacher call this function and reset student's state to 0!!
    print('----------http://163.28.17.68/teacher/reset/state----------')
    global student_state_data
    student_state_data["student_1"] = 0
    # student_state_data["student_2"] = 0
    global student_state_json
    student_state_json= json.dumps(student_state_data)
    print("----------Question Button state!!!!----------")
    print(student_state_json)
    return student_state_json

@app.route('/teacher/show/student/question')
def teacher_access_student_questions():
    # global student_questions_json. Teacher request this to draw the student's all question.!!
    global student_questions
    student_questions_json = json.dumps(student_questions)
    print(student_questions_json)
    return student_questions_json

@app.route('/student/absent/or/not/<attandence>')
def student_absent_or_not(attandence):
    # send student's attandence state to server and update state
    print('----------http://163.28.17.68/student/absent/or/not/%s----------' %attandence)
    global student_attandance
    global student_attandance_json
    student_attandance["student_1"] = attandence
    student_attandance_json = json.dumps(student_attandance)
    global student_attandence_happiness
    global student_attandance_happiness_json
    student_attandence_happiness["student_1"][0] = attandence
    student_attandence_happiness["student_1"][1] = 0
    student_attandance_happiness_json = json.dumps(student_attandence_happiness)
    print("----------Attandence state!!!!----------")
    print(student_attandance_json)
    print("----------Attandence and happiness state!!!!----------")
    print(student_attandance_happiness_json)
    return student_attandance_happiness_json

@app.route('/student/attendance/state')
def student_absent_state():
    print('----------http://163.28.17.68/student/attendance/state----------')
    print(student_attandance_happiness_json)
    return student_attandance_happiness_json

@app.route('/student/upload/<upload_data>')
def student_upload(upload_data):
    print('----------http://163.28.17.68/student/upload/%s----------' %upload_data)
    print(upload_data)
    nl.Q_callable(upload_data)
    return upload_data

@app.route('/student/happiness/<emotion_score>')
def student_happiness(emotion_score):
    print('----------http://163.28.17.68/student/happiness/%s----------' %emotion_score)
    global student_happiness_score
    global student_happiness_score_json
    student_happiness_score["student_1"] = emotion_score
    student_happiness_score_json = json.dumps(student_happiness_score)
    print("----------Student happiness score ----------")
    print(student_happiness_score_json)

    global student_attandence_happiness
    global student_attandance_happiness_json
    student_attandence_happiness["student_1"][-1] = emotion_score
    student_attandance_happiness_json = json.dumps(student_attandence_happiness)
    print("----------Attandence and happiness state!!!!----------")
    print(student_attandance_happiness_json)
    return student_happiness_score_json

if __name__ == '__main__':
    r = js()
    question_name = []
    app.run( debug = True, host='0.0.0.0')
