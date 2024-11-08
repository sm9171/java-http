# 만들면서 배우는 스프링
[Next Step - 과정 소개](https://edu.nextstep.camp/c/4YUvqn9V)

## 톰캣 구현하기

### 학습목표
- 웹 서버 구현을 통해 HTTP 이해도를 높인다.
- HTTP의 이해도를 높혀 성능 개선할 부분을 찾고 적용할 역량을 쌓는다.
- 서블릿에 대한 이해도를 높인다.
- 스레드, 스레드풀을 적용해보고 동시성 처리를 경험한다.

### 시작 가이드
1. 미션을 시작하기 전에 파일, 입출력 스트림 학습 테스트를 먼저 진행합니다.
   - [File, I/O Stream](study/src/test/java/study)
   - 나머지 학습 테스트는 다음 강의 시간에 풀어봅시다.
2. 학습 테스트를 완료하면 LMS의 1단계 미션부터 진행합니다.

## 학습 테스트
1. [File, I/O Stream](study/src/test/java/study)
2. [HTTP Cache](study/src/test/java/cache)
3. [Thread](study/src/test/java/thread)

## 미션 1단계 - TDD 실습
- [x] 요구사항1 - GET 요청
- [x] 요구사항2 - POST 요청
- [x] 요구사항3 - Query String 파싱
- [x] 요구사항4 - enum 적용(선택)

## 미션 2단계 - HTTP 서버 구현하기
- [ ] 요구사항1 - GET /index.html 응답하기
  - [ ] Http11ProcessorTest 테스트 클래스의 모든 테스트를 통과하면 된다.
- [ ] 요구사항2 - CSS 지원하기
- [ ] 요구사항3 - Query String 파싱