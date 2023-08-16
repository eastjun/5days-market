
# 5일장 마켓

### 프로젝트 소개

개인거래 사이트 '5일장'은 Java와 Spring을 토대로 제작하였습니다.

추가적으로 JSP ,JSTL, Ajax, HTML, CSS, JavaScript를 사용해서 구현했습니다.

개인이 사용하지 않는 새 물건이나 중고물품들을 거래할 수 있는 사이트를 목표했습니다.

형식은 쇼핑몰의 형식을 띄고 있으나, 사업자가 아닌 개인회원이 가입해서 구매, 판매하는 구성입니다.


### 프로젝트 진행기간

2023.07-04 ~ 2023.07.13

   ### 개발환경

* OS : Windows 10

* FW : Spring Framework

* DB : Oracle11c

* WAS : Apache Tomcat 9.0

* Front-end : HTML5 / CSS / javascript / jQuery 

* Back-end : JDK 1.8 / JSP / JSTL / Mybatis / AJAX / JSON

* Developer Tools : STS 3.9.12 / eXERD / SqlDeveloper

* GitHub

### 주요기능

* 공지사항 CRUD

* 개인회원 CRUD

* 상품 CRUD

* 로그인

* 아이디, 비밀번호 찾기

* 회원가입 


## 상세 기능 설명

* ### 공지사항 CRUD

  * ### 공지등록
    권한이 있는경우에만 공지 등록을 할 수 있는 버튼이 활성화 된다.

<img src="https://user-images.githubusercontent.com/128656153/257974600-60d0c02c-2684-4b27-bff0-5f70129d0c50.png" width="60%" height="60%"/>

  * ### 수정삭제

      수정과 삭제 역시 권한이 있는 계정만 버튼이 활성화 된다.
<img src="https://user-images.githubusercontent.com/128656153/257974637-7aeca2b3-fc6d-44af-b588-0ccfd6d8f5b4.png">

* ### 개인정보 관리
* 회원정보 수정을 하기 위해서는 비밀번호를 확인 후 수정할 수 있다.



<img src= "https://github.com/eastjun/project-stap3/assets/128656153/a93db36f-5f65-4395-85c3-423aa5b4f991" width="30%" height="30%" >


* 회원 탈퇴를 하기 위해서는 비밀번호를 확인 후 탈퇴할 수 있다.



<img src="https://github.com/eastjun/project-stap3/assets/128656153/433da26b-b335-42ff-906c-cea75405bd6a" width="30%" height="30%">


* ### 회원가입
  * 아이디의 경우 버튼을 통해 중복을 체크하도록 되어있다.
  * 닉네임과 이메일은 즉시 중복인지를 확인한다.


<img src="https://github.com/eastjun/project-stap3/assets/128656153/ac3875f3-3257-410c-b126-03a5a82dece1" width="30%" height="30%">


* ### 로그인
<img src="https://github.com/eastjun/project-stap3/assets/128656153/bbe5129c-42e4-4be5-aeed-8d5ab919f0f9" width="30%" height="30%">


* #### 아이디 찾기
  * 아이디의 경우 이메일과 휴대폰번호로 확인 후 바로 확인이 가능하다.


<img src="https://github.com/eastjun/project-stap3/assets/128656153/f5847319-45e8-4f20-825c-130b6a5ff237" width="30%" height="30%">
<br>

<img src="https://github.com/eastjun/project-stap3/assets/128656153/4b30c435-c414-4eb3-959c-53924de8e262" width="30%" height="30%">


* #### 비밀번호 찾기
  * 비밀번호의 경우 등록된 이메일로 변경 메일이 가서 수정하도록 되어있다.


<img src="https://github.com/eastjun/project-stap3/assets/128656153/c077ef4c-b5f4-4c8b-aa4c-0e41349ca5be" width="50%" height="50%">

* ### 상품
  *  상품등록은 권한을 가진 계정만 가능하다
  *  등록된 상품 수정, 삭제의 경우 자신의 아이디인 경우만 가능하다

<br>

<img src="https://github.com/eastjun/project-stap3/assets/128656153/c1148847-529d-4d2f-a20d-07a2a77510cb" width="50%" height="50%">

<br>
<br>
<img src="https://github.com/eastjun/project-stap3/assets/128656153/9288d016-a2fe-462f-8b3a-9ff56a2f435a" width="30%" height="30%">

<img src="https://github.com/eastjun/project-stap3/assets/128656153/295a73c7-8a37-4634-a033-068fcb8ed3b5" width="30%" height="30%">


* ### Q&A 
   *상품을 등록한 아이디와 동일한 경우에만 답변을 남길 수 있다.

<br>
<img src ="https://user-images.githubusercontent.com/128656153/260882770-71de1eb8-9ece-47e9-96f1-32b537848768.png">
<img src= "https://user-images.githubusercontent.com/128656153/260882786-e0db1c13-ae11-488e-9b95-efb6537102fc.png">

ERD
---
<img src="https://github.com/eastjun/5days-market/assets/128656153/c6e5529d-a959-4966-a7d5-60293446e0af">




