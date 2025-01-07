# TeamP_ClimbingSite_Test

## 환경
- Eclipes
- JAVA
- SpringBoot
    - SpringBoot DevTools
    - Lombok
    - Spring Configuration Processor
    - Thymeleaf
    - Spring Web
    - Spring Web Services
    - 이후 필요에 따라 추가 예정

## 로그

### 250107_카카오API 테스트
- 카카오 맵으로 `클라이밍` 단어 관련 자료 추출 테스트(진행 중)

    - 혹시 카카오 권한 설정 안될 경우 권한 확인 -> 안되면 최초 등록 어플리케이션(테스트용) 은 다 됨

    - 카카오 API의 경우 플레이스와는 다른듯..? 
        - 이름은 가져올 수 있으나, 장소 관련 ID를 못 가져옴
        - 일단 테스트 중인데 테스트 방법은
        - 1.이름을 기준으로 다시 검색(별로인듯)
        - 2.이름을 가져오는 함수에서 관련 데이터(플레이스에 있는 그것) 을 가져오도록 -> 없다!
- [PlaceURL](https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-keyword) 방법을 이용 -> 여기서 데이터 크롤링 혹은 이 데이터 그대로 띄우던가.. 좋은 방법인가?