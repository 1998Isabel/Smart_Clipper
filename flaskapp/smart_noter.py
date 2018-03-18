from flask import Flask, url_for, jsonify
import json
from giveUJson.header import jasoner as js
app = Flask(__name__)

@app.route('/', methods=['GET'])
def hello_world():
    return 'Hello ~ You successfully connected to server!'

# <username> type is path
@app.route('/variable/<username>')
def show_variable(username):
    # show the user profile for that user
    print('----------http://163.28.17.68/variable/%s----------' %username)
    question_name.append(username)
    json_list = r.callable(username)
    print(json_list)
    # json_txt = json.dumps(json_list)
    return jsonify(json_list)
# <int:post_id> type is int or post

@app.route('/data/json')
def show_json():
    # show the post with the given id, the id is an integer
    print('----------http://163.28.17.68/data/json----------')
    return jsonify(r.return_json())

@app.route('/teacher/student/data')
def show_teacher_json():
    # show the post with the given id, the id is an integer
    print('----------http://163.28.17.68/teacher/student/data----------')
    print(question_name)
    return jsonify(question_name)

if __name__ == '__main__':
    r = js()
    question_name = []
    app.run( debug = True, host='0.0.0.0')