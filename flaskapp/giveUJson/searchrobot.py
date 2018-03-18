#163.28.17.68
import requests as ur
from bs4 import BeautifulSoup
import json
import sys
def search(keyword):
    keyword = keyword.replace(" ","+")
    con = ur.get("https://www.google.com/search?q="+keyword+"&num=10&hl=en")
    soup = BeautifulSoup(con.text,"html.parser")
    blocks = soup.find_all("div",attrs={"class":"g"})
    results = []
    for block in blocks:
        link = block.find("a",href=True)
        title = block.find("h3",attrs={"class":"r"})
        des = block.find("span",attrs={"class":"st"})
        if link and title and des:
            results.append(["https://www.google.com"+link["href"],title.get_text(),des.get_text()])
    return results
def getwiki(sr):
    for result in sr:
        if result[0].find("wiki")>=0 or result[0].find("Wiki") >=0:
             return {"url":result[0],"description":result[2]}
    return {"url":sr[0][0],"description":sr[0][2]}
def wiki(keyword):
    links = search(keyword)
    return getwiki(links)
def gettextfromlink(link):
    con = ur.get(link)
    soup = BeautifulSoup(con.text,"html.parser")
    textlist = soup.find_all("p")
    text = []
    for piece in textlist:
        text.append(piece.get_text())
    return text
def get_search_result(keyword):
    results = wiki(keyword)
    return {keyword:results}
if __name__ == "__main__" :
    #word = input()
    if not len(sys.argv) <= 1:
        js = get_search_result(" ".join(sys.argv[1:]))
    else :
        js = ""
    with open("out.json","w") as file:
        file.write(json.dumps(js))
    file.close()
    print (js)
