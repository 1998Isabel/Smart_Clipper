from ynlu import NLUClient
import json as js 
#call Q_callable(str), str is the text from student, give out Q.json as output
def Q_callable(inpu):
    client = NLUClient(token='eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIxLCJleHBpcmVkQXQiOiIyMDE4LTA0LTAyVDAwOjAwOjAwLjAwMFoiLCJpYXQiOjE1MjIyMDY0NjN9.StGytb9PAt2xn7liyDaOL6Ukk23FepM36LPbLyvT_jA')
    # Get model by id
    model = client.get_model_by_id('154849961589085533')
    # Predict
    if len(inpu) == 0:
        intent_result = [{"sleep":1}]
    else:
        intent_result = model.predict(inpu)
    with open("Q.json", "w") as outfile:
        js.dump(intent_result,outfile)
    
    print(intent_result)

if __name__ == "__main__":
    Q_callable("")

