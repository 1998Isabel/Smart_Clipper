from flask import Flask, url_for
app = Flask(__name__)

@app.route('/', methods=['GET'])
def hello_world():
    return 'Hello ~ You successfully connected to server!'

# <username> type is path
@app.route('/variable/<username>')
def show_variable(username):
    # show the user profile for that user
    print('----------http://163.28.17.68/variable/%s----------' %username)
    return '%s' % username + ' is received'
# <int:post_id> type is int or post

@app.route('/data/json')
def show_json():
    # show the post with the given id, the id is an integer
    print('----------http://163.28.17.68/data/json----------')
    return 'return json file to cellphone'

@app.route('/teacher/student/data')
def show_json():
    # show the post with the given id, the id is an integer
    print('----------http://163.28.17.68/teacher/student/data----------')
    return 'return data to teacher'


if __name__ == '__main__':
    app.run( debug = True, host='0.0.0.0')