from imgurpython import ImgurClient
import requests
import json
from IPython.display import HTML
import requests as ur

#call face_callable(image_path, filename(option))
#u will get the json file and print if the student
# is arrived of absent

URL_REQUEST_ATTANDENCE = "http://163.28.17.68:5000/student/absent/or/not/"
def upload_photo(path):
    client_id = '73047c0e236ec03'
    client_secret = '8794a466cabf337336240fa14d41493b8a2c000e'
    client = ImgurClient(client_id, client_secret)
    config = {  'album': None, }
    print("Uploading image... ")
    image = client.upload_from_path(path, config=config, anon=False)
    print("Done")
    print(image['link'])
    return image['link']

def API_handler(image_path):
    headers = { 'Ocp-Apim-Subscription-Key': 'aed5f9e4edb84aa38f01fc3e558f84d9' }
    params = {
        'returnFaceId': 'false',
        'returnFaceLandmarks': 'false',
        'returnFaceAttributes': 'occlusion,headPose,emotion',
    }
    face_api_url = 'https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect'
    urlll = upload_photo(image_path)
    response = requests.post(face_api_url, params=params, headers=headers, json = {"url":urlll})
    faces = response.json()
    return faces
def face_callable(image_path, filename = 'face.json'):
    outputer = API_handler(image_path)
    with open(filename, 'w') as outfile:
        json.dump(outputer, outfile)
    if len(outputer) == 0:
        print("absent")
        ur.get(URL_REQUEST_ATTANDENCE+'0')
    else:
        print("arrived")
        ur.get(URL_REQUEST_ATTANDENCE+'1')
    return

if __name__ == "__main__" :
    face_callable("4.jpg")
