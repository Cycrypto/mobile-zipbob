## 모바일 프로그래밍 프로젝트 README

# 한솥밥

**프로젝트명:** 집밥 공유 플랫폼  
**앱 이름:** 한솥밥  
**수행기간:** 2024-05-01 ~ 2024-06-25 <br/>
**[프로젝트 노션 페이지](https://dandelion-savory-5fa.notion.site/6482d519f98c46bdb0591e13acb98ec4?pvs=4)** 

### 프로젝트 개요

현대 사회는 개인화되고, 삶의 속도는 점점 빨라지고 있습니다. 이러한 변화로 인해 많은 사람들이 집에서 요리할 시간이 부족하거나, 혼자 먹기 위해 대량의 재료를 구매하는 데 어려움을 겪습니다. 이는 결국 외식이나 배달 음식에 의존하게 하고, 음식 낭비를 초래합니다. ‘한솥밥’은 이러한 배경을 토대로, 더 많은 사람들이 집밥의 따뜻함을 느끼고, 공동체의 일원으로서 서로 도울 수 있는 환경을 조성하고자 합니다. 이를 통해 사회적 연결을 강화하고, 지속 가능한 식문화를 구축하며, 취약계층을 지원하는 선순환 구조를 만들어냅니다.

### 프로젝트 기능 소개

1. **집밥 공유**
   - 많은 음식을 한 경우 공유 커뮤니티에 올려 포인트로 판매
   - 사용자들이 커뮤니티에 올라온 집밥을 포인트로 구매하여 쉽게 가정식을 접할 수 있도록 함
   - 자원봉사자들이 집밥을 무료로 공유하면 취약계층이 먼저 확인할 수 있도록 하고, 일정 시간이 지나면 모든 사용자들이 확인할 수 있도록 함

2. **밀키트 판매**
   - 사용자들이 서로의 집밥 레시피를 공유하여 직접 밀키트로 제작하여 포인트로 판매
   - 구매하려는 사람들이 집에서 간편하게 요리 해 먹을 수 있도록 함

3. **공동 구매**
   - '귤 한박스'와 같이 한 사용자가 소비하기에 많다고 느끼는 식재료 또는 상품의 경우 나눌 사람을 모집

4. **레시피 제작**
   - 사용자들이 고유의 집밥 레시피를 공유
   - 댓글, 평점 기능으로 리뷰 가능

5. **포인트 시스템**
   - 신규 회원, 레시피 공유, 집밥 나눔, 뚜벅이 배달 등의 활동을 통해 포인트를 획득

6. **뚜벅이 배달**
   - 집밥 공유, 밀키트 판매 시 직거래가 어려운 사람들을 위해 배달원을 앱 내부에서 모집

### 기술 스택

| 기술 스택         | 뱃지                                                                                     |
|------------------|------------------------------------------------------------------------------------------|
| **빌드 도구**      | ![Jitpack](https://img.shields.io/badge/Jitpack-1E8CBE.svg?style=for-the-badge&logo=jitpack) ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=gradle) |
| **프로그래밍 언어** | ![Kotlin](https://img.shields.io/badge/Kotlin-0095D5.svg?style=for-the-badge&logo=kotlin) ![Kotlin](https://img.shields.io/badge/Java-FC4C02.svg?style=for-the-badge&logo=java)|
| **UI 프레임워크**  | ![Compose](https://img.shields.io/badge/Compose-0288D1.svg?style=for-the-badge&logo=jetpack-compose) |
| **문서화 도구**    | ![Notion](https://img.shields.io/badge/Notion-000000.svg?style=for-the-badge&logo=notion) |
| **커뮤니케이션**   | ![Discord](https://img.shields.io/badge/Discord-5865F2.svg?style=for-the-badge&logo=discord) |
| **IDE**           | ![Android Studio](https://img.shields.io/badge/Android_Studio-3DDC84.svg?style=for-the-badge&logo=android-studio) |
| **버전 관리**      | ![Github](https://img.shields.io/badge/Github-181717.svg?style=for-the-badge&logo=github) |

### 프로젝트 구조

```
|-- AndroidManifest.xml
|-- assets
|   `-- font
|       `-- WandohopeB.ttf
|-- java
|   `-- com
|       `-- example
|           `-- hansotbob
|               |-- MainActivity.kt
|               |-- auth
|               |-- component
|               |-- dto
|               |-- exception
|               |-- fragment
|               |-- navigation
|               |-- service
|               |-- ui
|               `-- viewmodel
`-- res
    |-- drawable
    |-- layout
    |-- menu
    |-- mipmap-anydpi-v26
    |-- mipmap-hdpi
    |-- mipmap-mdpi
    |-- mipmap-xhdpi
    |-- mipmap-xxhdpi
    |-- mipmap-xxxhdpi
    |-- values
    |-- values-night
    `-- xml
```

### 팀원 소개

| 이름     | 역할                            | 이메일               | 사진             |
|--------|-------------------------------|--------------------|----------------|
| 박준하  | 로그인 및 회원가입, 결제 프로세스 | jh01love00@gmail.com  | ![박준하](link_to_image) |
| 이가원  | 집밥 공유                       | gawonlee0712@gmail.com  | ![이가원](link_to_image) |
| 이건준  | 식료품 커뮤니티, 레시피 제작       | dlrjswns151@gmail.com | ![이건준](link_to_image) |
| 진소희  | 밀키트 판매, 마이페이지            | 63wlsthgml@gmail.com  | ![진소희](link_to_image) |

---
