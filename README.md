# Smart-Clipper 智慧剪貼簿

## 概要
### **2018 makeNTU 作品**
![](https://i.imgur.com/Xjy3XKk.png)
* 伺服器端
    * OS: Ubuntu 16.04 LTS.
    * Flask app
    * 功能: state儲存、資料的傳送、Uderstood自然語意理解API
* 學生端
    * OS: Raspbian
    * Raspberry Pi 3
    * 功能: 學生按鍵觸發、Pi camera定時照相
* 學生端資料呈現
    * Android app
    * 功能: 即時更新server上學生問題、資料呈現（定義、網址）、可輸入手動輸入查詢單詞、課後問題回答上傳
* 老師端
    * OS: Raspbian
    * Raspberry Pi 3
    * 收音模組固定收音、語音辨識api分析老師上課內容成字串、即時更新學生state
* 老師端資料呈現
    * 學生端的所有問題單詞長條圖呈現、學生出缺席以及開心分數呈現

## 發想
* 專有名詞很多的課 ex.生物、法律...
* 教授上課很快，沒空抄筆記
* 教授想掌握學生狀況
* 建立有別於舉手的學生提問與搜尋管道
* 增進教學上的互動
* 以簡單、便利、不用分心的方式提出問題 -- 按鈕式提問

## 檔案介紹及使用
```
Smart_Clippr
│   README.md
│   data.json : a json file to store student's question key word
│
└───server_side
|   |   smart_noter.py : flask API main script
│   │   Q.json : Understood returned data
│   │   nlu.py : Understood API script
│   │
│   └───giveUJson
│       │   const.py : defined const
│       │   header.py : main  script to generate student's question words
│       |   searchrobot.py : a search engine for student's question words
│   
└───student_side
│   │   imgurAPI.py : a script to take pictures and return microsoft face api result in json every 10 seconds
│   │   face.json : output result of imgurAPI result
|   |   student.py : a script to controll a button for students to press
|
└───teacher_side
|   │   SpeechProccesor_GoogleT.py : Using google speech_proccesor api to generate word list
|   |   teachert.py : Using SpeechProccesor_GoogleT.py and return keyword to students
|
└───student_side_app_present : an Android app        
|
└───teacher_data_present
|   |   abbsent.py : a script to show students' attendance, happiness
│   │   teacher_see_questions_bar.py : a script to show students' question words in bar chart
|   |   keyboard.py : a test script based on user's input (bar chart)
```

## 影響以及應用
* 我們做到了:
    * 以較小的干擾和負擔提供額外的學習資源
    * 提升師生課堂間的雙向互動服務
    * 及時反應教學成效，有助提升教學效率
* 可以用來:
    * 協助學生複習
    * 作為遠距教學的輔助工具



## 未來延伸性
1. 增進查詢的演算法
2. 建立課本資料庫，輔助語音判讀對於專有名詞的精確性
4. 除了單詞 definition 查詢，增加不懂相關內容的補充、課本內容索引
5. 作為網路影音教學資源的附加功能

## 工作分配

沂謙 - google、microsoft語音辨識API、Understood自然語意理解training

慕哲 - json資料打包資、Understood自然語意理解training、microsoft影像辨識API、老師端資料呈現

瀚墨 - 搜尋引擎json整理、老師端rpi、學生端rpi、老師端程式整合(收音模組)、學生端程式整合(Pi camera)

冠豪 - linux server架設、Flask API環境建立、學生端Android app資料呈現，老師端資料呈現、state傳送設計



