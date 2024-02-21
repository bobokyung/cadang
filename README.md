
<p align="center">
<img src="./RMimg/image-1.png">
</p>

# 📕 목차 
## 1. [**서비스 소개**](#1)
## 2. [**기술 스택**](#2)
## 3. [**시스템 아키텍처**](#3)
## 4. [**주요 기능**](#4)
## 5. [**팀원 소개**](#5)
## 6. [**설계 문서**](#6)

<br />
<br />
<br />


<div id="1"></div>

# 🔍 서비스 소개

## 당신의 카페인과 당 섭취를 줄여줄 인생 콰당 방지 서비스  
> 대한민국은 커피 공화국..!!  
전세계 1인당 연간 커피 소비량인 161잔을 훨씬 뛰어넘는 연간 367잔의 커피를 마시는 한국인들   
이 과정에서 무심코 섭취하게되는 카페인과 당을 관리하지 않는다면    
매일 무의식적으로 마시는 커피와 음료가 당신의 건강을 위협할 수 있습니다.  
따라서 이들을 관리해 줄 장치가 필요하지만, 그게 말처럼 쉽지많은 않습니다.
>
#### 콰당과 함께 지금부터 카페인과 당 섭취를 관리해보세요





<br />
<br />
<br />


<div id="2"></div>

# 💻 기술 스택

## Front-end
- HTML5
- CSS3
- JavaScript
- VUE
- Pinia
- VSCODE
- WebRTC
<br />
<br />

## Back-end
- Java
- Spring
- JPA
- QueryDSL
- Gradle
- MYSQL
- Python
- Flask
- WebRTC
- Intellij
<br />
<br />

## Server
- AWS EC2
- Nginx
<br />
<br />

## Cooperation tool
- Jira
- GitLab
- Gerrit
- Notion
- Mattermost

<br />
<br />



<br />
<br />
<br />

<div id="3"></div>

# 🧮 시스템 아키텍처

<img src="./RMimg/시스템 구성.PNG" alt="마이페이지" >


<br />
<br />
<br />


<div id="4"></div>

# 🙌 주요 기능
<br />
<br />

## ❤ 시작페이지
<img src="./RMimg/시작.gif" alt="start" >
 
<br />

- 카카오 소셜로그인으로 회원가입 및 로그인이 가능합니다
<br />
<br />
<br />

## 🤝 회원가입페이지
<img src="./RMimg/회원가입페이지.gif" >

<br />

- 자주 마시는 음료를 기입하고 기입한 정보를 토대로 현재 상태를 간단하게 보여줍니다
- 설문조사는 건너뛰기도 가능합니다
- 완료되면 메인페이지로 넘어가게 됩니다
<br />
<br />
<br />

## 🌏 메인페이지
<img src="./RMimg/메인페이지.gif" >

<br />

- 카페인, 당 권장 섭취량과 현재 섭취량을 비교해줍니다.  
- 일,주 별로 섭취량 현황을 알 수 있습니다
- 유저가 최근 음료를 기반으로 비슷한 음료를 추천해줍니다  
<br />
<br />
<br />

## 🌏 메인페이지 - 추천음료
<img src="./RMimg/fruitDrink.PNG" >
<img src="./RMimg/coldbrewDrink.PNG">
<img src="./RMimg/peachDrink.PNG">

<br />

- 사용자의 가장 최근 등록한 음료를 기반하여 음료를 추천해줍니다.  
- 음료의 태깅으로 음료간의 코사인 유사도를 계산하여 10개 뽑아냅니다.
  그 이후 10개 중에서 카페인이 낮거나, 당이 낮은 음료를 정렬하여 선별합니다. 
<br />
<br />
<br />

## ✍ 마신 음료 등록
<img src="./RMimg/음료기입1.gif" >
<br />
<img src="./RMimg/음료기입2.gif" >

<br />

- 상위 15개의 프랜차이즈 카페에서 판매하는 커피 및 음료가 데이터베이스에 등록되어있습니다
- 샷, 시럽 추가 기입도 가능합니다
- 등록되어 있지 않은 나만의 음료도 직접 기입할 수 있습니다
<br />
<br />
<br />

## 🗣 채팅
<img src="./RMimg/채팅.gif" >

<br />

- 상위 15개 카페별로 채팅방이 열려있어 자유롭게 대화 가능합니다
<br />
<br />
<br />

## 📅 캘린더
<img src="./RMimg/캘린더.gif" >

<br />

- 캘린더를 활용해 카페인과 당 섭취량을 한눈에 확인할 수 있습니다
- 해당일을 클릭하면 그날 마신 음료 정보를 알 수 있습니다
- 오늘 마신 음료는 수정 가능합니다
- 캘린더 상단에 권장 섭취량과 오늘 마신 수치를 비교해서 글씨의 색으로 나타내줍니다
<br />
<br />
<br />

## 🔎 검색페이지
<img src="./RMimg/검색페이지.gif">
<br />

- 순위보기를 통해 검색순위와 인기 등록 음료 순위를 확인할 수 있습니다
- 전체보기를 통해 원하는 정렬방식대로 전체 음료를 확인할 수 있습니다
- 음료 상세보기가 가능합니다
- 음료들을 비교하여 여러 음료들을 한번에 확인할 수 있습니다 
<br />
<br />
<br />

## 👀 마이페이지/정보수정
<img src="./RMimg/마이페이지.gif" >
<br />

- 본인의 정보(키,몸무게,성별,나이)에 알맞은 섭취 권장량을 보여줍니다  
- 일별 최고 섭취량을 확인 할 수 있습니다  
- 정보수정이 가능합니다  
 
<br />
<br />
<br />


<div id="5"></div>

# 👫 팀원 소개 및 프로젝트 기간

<table>
    <tr>
        <td height="140px" align="center"> 
             👑 임지현 <br>(Front-End) </a> <br></td>
        <td height="140px" align="center"> 
             🙂 김준섭 <br>(Front-End) </a> <br></td>
        <td height="140px" align="center"> 
             😆 권근열 <br>(Front-End) </a> <br></td>
        <td height="140px" align="center"> 
             😁 김보경 <br>(Back-End) </a> <br></td>
        <td height="140px" align="center"> 
             🙄 김보라 <br>(Back-End) </a> <br></td>        
    </tr>
    <tr>
        <td align="center">UI/UX<br/>Vue<br/></td>
        <td align="center">UI/UX<br/>Vue<br/>WebRTC,WebSocket</td>
        <td align="center">UI/UX<br/>Vue</td>
        <td align="center">REST API<br/>Spring Boot/Flask<br/>WebRTC,WebSocket<br/>Infra</td>
        <td align="center">REST API<br/>Spring Boot<br/>Infra</td>        
    </tr>
</table>


### 👫 똑똑이들 : 24.1.2 ~ 24.2.16

<br />
<br />
<br />

<div id="6"></div>

# 📃 설계 문서

## ERD
<img src="./RMimg/ERD.png" alt="erd">

<br />
<br />
<br />

## API 명세서
 method | URI                            | API                                                            | 담당(BE) | BE구현 | 담당 (FE)     | FE 구현 
--------|--------------------------------|----------------------------------------------------------------|-------------|------|-------------|-------
 GET    | /api/kakao-login?code=blahblah | 카카오 로그인                                                        | 김보라    | Yes  | Junseob Kim | Yes   
 PUT    | /api/logout                    | 로그아웃                                                           | 김보라    | Yes  | 권근열         | Yes   
 GET    | /api/user                      | 회원 정보 조회                                                       | 김보라    | Yes  | 권근열         | Yes   
 PUT    | /api/user                      | 회원 정보 수정                                                       | 김보라    | Yes  | 권근열         | Yes   
 DELETE | /api/user                      | 회원 탈퇴                                                          | 김보라    | Yes  | 권근열         | Yes   
 GET    | /api/user/name                 | 회원 닉네임 조회                                                      | 김보라    | Yes  | 지현          | Yes   
 GET    | /api/user/amount               | 회원 권장량 조회                                                      | 김보라    | Yes  | 권근열         | Yes   
 GET    | /api/user/max                  | "마이페이지 - 회원 당, 카페인 최고 섭취량 및 날짜 조회"                             | 김보라    | Yes  | 권근열         | Yes   
 GET    | /api/accumulate/today          | "메인페이지 - 당, 카페인 일일 섭취량 조회"                                     | 보경 김   | Yes  | 지현          | Yes   
 GET    | /api/accumulate/duration       | "메인페이지 - 당, 카페인 기간별 섭취량 조회 ( 그래프 ) "                           | 보경 김   | Yes  | 지현          | Yes   
 GET    | /api/recommend/sugar           | "메인페이지 - 사용자 기록 기반 음료 추천 ( 당 ) "                               | 보경 김   | Yes  | 지현          | Yes   
 GET    | /api/recommend/caffeine        | 메인페이지 - 사용자 기록 기반 음료 추천 ( 카페인 )                                | 보경 김   | Yes  | 지현          | Yes   
 POST   | /api/records/drink             | 기록- 카페 음료 기록 생성                                                | 보경 김   | Yes  | 지현          | Yes   
 POST   | /api/records/make              | 기록 - 나만의 음료 기록 생성                                              | 보경 김   | Yes  | 지현          | Yes   
 GET    | /api/drinks/{cafeId}/{keyword} | 기록 - 카페 선택시 음료 검색                                              | 보경 김   | Yes  | 지현          | Yes   
 GET    | /api/drinks/cafe               | 기록 - 카페 음료 기록 시 선택가능한 카페 조회                                    | 보경 김   | Yes  | 지현          | Yes   
 GET    | /api/drinks/{cafeId}           | 기록 - 카페 음료 기록 시 선택가능한 카페별 음료 조회                                | 보경 김   | Yes  | 지현          | Yes   
 POST   | /api/records/drink             | 캘린더 - 카페 음료 기록 생성                                              | 보경 김   | Yes  | Junseob Kim | Yes   
 POST   | /api/records/make              | 캘린더 - 나만의 음료 기록 생성                                             | 보경 김   | Yes  | Junseob Kim | Yes   
 PUT    | /api/records/drink             | 캘린더 - 카페 음료 기록 수정                                              | 보경 김   | Yes  | Junseob Kim | Yes   
 PUT    | /api/records/make              | 캘린더 - 나만의 음료 기록 수정                                             | 보경 김   | Yes  | Junseob Kim | Yes   
 DELETE | /api/records/{recordId}        | 캘린더 - 음료 기록 삭제                                                 | 보경 김   | Yes  | Junseob Kim | Yes   
 GET    | /api/accumulate/{ym}/month     | 캘린더 - 해당 월 전체 음료 기록 조회                                         | 보경 김   | Yes  | Junseob Kim | Yes   
 GET    | /api/records/{date}/day        | 캘린더 - 날짜에 해당하는 음료 기록 상세 조회                                     | 보경 김   | Yes  | Junseob Kim | Yes   
 GET    | /api/accumulate/{date}/day     | 캘린더 - 상세 기록에서 일일섭취량 총 합                                        | 보경 김   | Yes  | Junseob Kim | Yes   
 GET    | /api/rank/keywordranking       | 검색페이지 - 검색량 높은 키워드 순위                                          | 김보라    | Yes  | 권근열         | Yes   
 GET    | /api/rank/recordranking        | 검색페이지 - 기록량 높은 음료 순위                                           | 김보라    | Yes  | 권근열         | Yes   
 GET    | /api/drinks                    | 검색페이지 - 음료 전체보기 버튼                                             | 김보라    | Yes  | 권근열         | Yes   
 GET    | /api/search/{keyword}          | 검색페이지 - 음료 검색하기                                                | 김보라    | Yes  | 권근열         | Yes   
 GET    | /api/accumulate/today          | "검색페이지 - 전체보기 버튼을 눌렀을 때 각 음료와 유저의 카페인,당 일일섭취량 비교를 위한 일일섭취량 조회" | 보경 김   | Yes  | 권근열         | Yes   
 socket | ws://localhost:8080/ws/chat    | 채팅 소켓 기능                                                       | 보경 김   | Yes  | Junseob Kim | Yes   
 GET    | /api/chat/chatroom             | 채팅룸 list                                                       | 보경 김   | Yes  | Junseob Kim | Yes   
 GET    | /api/chat/{chatId}             | 채팅 내용 조회                                                       | 보경 김   | Yes  | Junseob Kim | Yes   
 GET    | /api/recommand/sugar           | 당 추천음료                                                         | 보경 김   | Yes  |             | Yes   
 GET    | /api/recommand/caffeine        | 카페인 추천음료                                                       | 보경 김   | Yes  |             | Yes   


<br />
<br />
<br />

## 화면설계서
<img src="./RMimg/화면설계서.PNG" alt="화면설계">

<br />
<br />
<br />

## 유저스토리

### 👉🏻[유저스토리 노션페이지](https://www.notion.so/whfflekwlqdprkrhtlvdjzzzz10/1697f15eb9d549b6affbc9b14c93f278)

<br />
<br />
<br />

## Commit convention
<img src="./RMimg/커밋.PNG" >

<br />
<br />
<br />




