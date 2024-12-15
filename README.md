# <img src="./readme/빌리필리.png" alt="🦊" width="40px" /> 책 먹는 여우 <img src="./readme/빌리필리.png" alt="어플 소개 이미지" width="40px" />
<img src="./readme/main.png" alt="어플 소개 이미지" />

# <img src="./readme/빌리필리.png" alt="🦊" width="30px" /> 프로젝트 개요
### 1. 기간

2024.08.26 - 2024.10.11 | 총 7주

### 2. 팀원

| 백하람<br />(프론트, 팀장)| 오예진<br />(프론트)| 김예원<br />(프론트)| 박찬호<br />(백엔드, 인프라)| 이건희<br />(백엔드)| 김미량<br />(빅데이터)|
|---|---|---|---|---|---|
| <img src="https://avatars.githubusercontent.com/u/74824057?v=4" alt='@ramrami-B' width="120" height="120"> | <img src="https://avatars.githubusercontent.com/u/156629458?v=4" alt="@ooyejinn" width="120" height="120"> | <img src="https://avatars.githubusercontent.com/u/158377990?v=4" alt="@yewone1" width="120" height="120" > | <img src="https://avatars.githubusercontent.com/u/91451735?v=4" alt="@Chaeros" width="120" height="120" > | <img src="https://avatars.githubusercontent.com/u/92250144?v=4" alt="@GeonHui2" width="120" height="120"> | <img src="https://avatars.githubusercontent.com/u/156287271?v=4" alt="@miryang1016" width="120" height="120" > |
| [@ramrami-B](https://github.com/ramrami-B)| [@ooyejinn](https://github.com/ooyejinn)| [@yewone1](https://github.com/yewone1)| [@Chaeros](https://github.com/Chaeros)| [@GeonHui2](https://github.com/GeonHui2)| [@miryang1016](https://github.com/miryang1016)|

### 3. 프로젝트 개요
빅데이터 기반 도서 추천 기능 및 독서 기록 관리 기능이 있는 모바일 웹앱

### 4. 기술 스택

**백엔드**
<div style="display: flex; gap: 20px">
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
<img src="https://img.shields.io/badge/java 17-007396?style=for-the-badge&logo=java&logoColor=white">  
</div>

**프론트엔드**
<div style="display: flex; gap: 20px">
<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">
<img src="https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white">
<img src="https://img.shields.io/badge/tailwindcss-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white">
<img src="https://img.shields.io/badge/redux-764ABC?style=for-the-badge&logo=redux&logoColor=white">
<img src="https://img.shields.io/badge/vite-646CFF?style=for-the-badge&logo=vite&logoColor=white">
</div>

**인프라**
<div style="display: flex; gap: 20px">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/docker hub-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white">
<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">
</div>
<div style="display: flex; gap: 20px">
<img src="https://img.shields.io/badge/elasticsearch-005571?style=for-the-badge&logo=elasticsearch&logoColor=white">
<img src="https://img.shields.io/badge/logstash-005571?style=for-the-badge&logo=logstash&logoColor=white">
<img src="https://img.shields.io/badge/kibana-005571?style=for-the-badge&logo=kibana&logoColor=white">
<img src="https://img.shields.io/badge/prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white">
<img src="https://img.shields.io/badge/grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white">
</div>

**빅데이터**
<div style="display: flex; gap: 20px">
<img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=white">
<img src="https://img.shields.io/badge/fastapi-009688?style=for-the-badge&logo=fastapi&logoColor=white">
</div>


# <img src="./readme/빌리필리.png" alt="🦊" width="30px" /> 기능 및 화면
### 1. 로그인/회원가입
<div style="display: flex; gap: 20px;">
  <img src="./readme/로그인.gif" alt="로그인 이미지" width="200"/>
  <img src="./readme/회원가입.gif" alt="회원가입 이미지" width="200"/>
</div>
- 카카오, 구글, 네이버 소셜 로그인이 가능합니다.<br/>
  
### 2. 홈 화면
<img src="./readme/홈화면.gif" alt="홈화면 이미지" width="200"/>
- 독서 현황을 여우의 상태로 보여줍니다.<br/>
  - 1시간 내로 밥을 주지 않으면 여우가 배고파합니다.(BAD)<br/>
  - 밥 주기를 통해 경험치와 상태를 변경할 수 있습니다.(GOOD)<br/>
  - 경험치 조건을 만족하면 레벨이 올라갑니다.<br/>
  - 특정 레벨마다 여우가 성장합니다 <br/>
 - 독서 캘린더를 통해 일자마다 읽은 페이지 수를 알 수 있습니다.<br/>
  - 많은 페이지를 읽을 수록 캘린더의 색이 진해집니다.<br/>
  - 완독한 책의 분아별 통계를 보여줍니다.<br/>
  - 완독한 책의 줄거리를 워드 클라우드로 볼 수 있습니다.<br/>
  - 월간 독서 시간을 차트로 확인할 수 있습니다.<br/>
    
### 3. 추천 책
<img src="./readme/추천책.gif" alt="추천책 이미지" width="200"/>
- 협업 필터링을 통해 선택한 태그의 책들을 추천합니다.<br/>
- 연령과 성별에 따른 인기 도서를 추천합니다.<br/>
  
### 4. 책 상세 보기
<img src="./readme/책상세페이지-읽기시작.gif" alt="책상세페이지 이미지" width="200"/>
- 다른 유저의 리뷰를 볼 수 있습니다.<br/>
- 책 줄거리를 기반으로 유사한 책들을 추천합니다.<br/>
- 내 책장에 해당 도서를 추가할 수 있습니다.<br/>
  
### 5. 나의 책장
<img src="./readme/나의책장.gif" alt="나의책장 이미지" width="200"/>
- 도서들을 읽는 중/완독한 책/찜한 책으로 관리할 수 있습니다.<br/>
  
### 6. 나의 책장 - 읽는 중
<img src="./readme/읽는중상세.gif" alt="읽는중상세 이미지" width="200"/>
- 몇 페이지 읽고 있었는지 기록할 수 있습니다.<br/>
- 현재 페이지와 함게 메모를 기록할 수 있습니다.<br/>
- 스톱워치를 활용해 총 독서 시간이 어떻게 되는지 기록할 수 있습니다.<br/>
  
### 7. 나의 책장 - 완독한 책
<img src="./readme/완독한책.gif" alt="완독한책 이미지" width="200"/>
- 별점과 함께 나의 리뷰를 작성 및 조회할 수 있습니다.<br/>
- 독후감을 기록할 수 있습니다.<br/>
  
### 8. 제목 검색
<img src="./readme/제목검색.gif" alt="제목검색 이미지" width="200"/>
- 책의 제목을 키워드로 검색할 수 있습니다.<br/>
  
### 9. 바코드 검색
<img src="./readme/바코드.gif" alt="바코드 이미지" width="200"/>
- PWA로 핸드폰 카메라 기능을 연결하여 바코드로 책을 검색할 수 있습니다.<br/>
  
### 10. 마이페이지
<img src="./readme/마이페이지.gif" alt="마이페이지 이미지" width="200"/>
- 유저의 닉네임, 프로필 사진, 성별, 생일, 관심사 키워드 등을 변경할 수 있습니다.<br/>
- 로그아웃 및 회원탈퇴를 할 수 있습니다.<br/>


# <img src="./readme/빌리필리.png" alt="🦊" width="30px" /> 빅데이터 알고리즘

### 1. 협업 필터링(User-Based Collaborative Filtering)
특정 사용자와 유사한 취향을 가진 다른 사용자들의 행동 데이터를 바탕으로 추천하는 알고리즘

### 2. 콘텐츠 필터링(TF-IDF)
정보 검색과 텍스트 마이닝에서 사용되는 기법으로, 특정 단어가 문서 내에서 얼마나 중요한지를 평가하는 데 사용되는 알고리즘


# <img src="./readme/빌리필리.png" alt="🦊" width="30px" /> 시스템 아키텍처
<img src="./readme/시스템아키텍처.png" alt="시스템아키텍처 이미지" width="650px" />


# <img src="./readme/빌리필리.png" alt="🦊" width="30px" /> ERD
<img src="./readme/ERD.png" alt="ERD 이미지" width="650px" />

