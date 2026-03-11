# Tomcat 구현하기 미션 요구사항

## 공통 프로그래밍 요구사항

- 모든 로직에 단위 테스트를 구현한다. 단, 테스트하기 어려운 UI 로직은 제외한다.
- 핵심 로직과 UI 로직을 분리한다. UI 로직은 별도 클래스로 분리한다.
- IntelliJ Java 코드 스타일 컨벤션을 준수한다.
- 한 메서드에 오직 한 단계의 들여쓰기만 허용한다.
- `else` 사용을 지양한다.
- 기능 구현 전 `README.md`에 구현할 기능 목록을 정리한다.
- 기능 단위로 커밋한다.
- 커밋 메시지 컨벤션은 `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`를 사용한다.

## 리뷰 공통 기준

- 이번 PR이 어느 단계의 요구사항을 다루는지 먼저 판단한다.
- 현재 단계 범위를 벗어난 과도한 설계보다, 해당 단계 목표를 정확히 달성했는지 우선 확인한다.
- 미션 요구사항 누락, 테스트 부족, 로직 분리 부족, HTTP 동작 불일치를 우선 리뷰한다.
- 다음 단계 요구사항을 미리 구현했더라도 현재 단계의 복잡도를 과도하게 높였는지 함께 본다.

## 1단계 - TDD 실습

### 목표

HTTP 요청/응답을 파싱해 원하는 값을 반환하는 API를 TDD로 구현한다.

### 기능 요구사항

1. `GET /users HTTP/1.1` 형태의 RequestLine을 파싱하여 method, path, protocol, version을 추출한다.
2. `POST /users HTTP/1.1` 형태의 RequestLine을 파싱한다.
3. `GET /users?userId=javajigi&password=password&name=JaeSung HTTP/1.1` 형태의 Query String을 `name=value` 구조로 파싱한다.
4. 선택 요구사항으로 HTTP method(`GET`, `POST`)를 enum으로 구현할 수 있다.

### 핵심 고민 포인트

- `InputStream`, `OutputStream`은 테스트하기 어렵기 때문에 테스트 가능한 구조로 분리하는 것이 핵심이다.

## 2단계 - HTTP 서버 구현하기

### 목표

실제 동작하는 간단한 HTTP 서버를 구현한다.

### 기능 요구사항

1. `/index.html` 요청 시 해당 HTML 파일을 읽어 응답한다. `Http11ProcessorTest`의 모든 테스트를 통과해야 한다.
2. CSS 파일 요청 시 응답 헤더의 `Content-Type`을 `text/css`로 전송한다. 확장자 또는 `Accept` 헤더로 구분한다.
3. `http://localhost:8080/login?account=gugu&password=password` 접속 시 `login.html`을 보여주고, Query String을 파싱해 아이디와 비밀번호가 일치하면 콘솔에 회원 조회 결과를 출력한다.

## 3단계 - 로그인 구현하기

### 목표

HTTP 표준을 활용한 로그인, 회원가입, 쿠키/세션 기능을 구현한다.

### 기능 요구사항

1. 로그인 성공 시 응답 헤더에 `302` 상태코드와 함께 `/index.html`로 리다이렉트하고, 실패 시 `401.html`로 리다이렉트한다.
2. `/register` GET 요청 시 회원가입 페이지를 노출하고, 가입 버튼 클릭 시 POST 방식으로 전송한다. 완료 후 `index.html`로 리다이렉트한다. 로그인도 POST 방식으로 변경한다.
3. 로그인 성공 시 응답 헤더에 `Set-Cookie: JSESSIONID={UUID}` 형태로 세션 ID를 전달한다. `HttpCookie` 클래스를 구현하며, 요청 헤더에 `JSESSIONID`가 없으면 새로 발급한다.
4. `JSESSIONID`를 통해 로그인 여부를 확인하고, 세션에 `User` 객체를 저장한다. 이미 로그인된 상태에서 `GET /login` 접근 시 `index.html`로 리다이렉트한다. `SessionManager`는 싱글톤으로 구현하고 동시성 컬렉션 사용을 고려한다.

## 4단계 - 리팩터링

### 목표

WAS 기능, HTTP 처리, 애플리케이션 비즈니스 로직의 역할을 명확히 분리한다.

### 추가 프로그래밍 요구사항

- 모든 원시값과 문자열을 포장한다.
- 일급 컬렉션을 사용한다.

### 기능 요구사항

1. HTTP 요청 전체를 처리하는 `HttpRequest` 클래스를 구현한다. 첫 줄 파싱 역할은 `RequestLine` 클래스로 분리한다.
2. HTTP 응답 전체를 처리하는 `HttpResponse` 클래스를 구현한다. RFC2616을 참고한다.
3. URI 경로별 `if` 분기를 컨트롤러 패턴으로 리팩터링한다. `Controller` 인터페이스와 `AbstractController`를 구현하고, 각 분기 로직을 구현체로 분리한다. `RequestMapping` 클래스로 URI에 맞는 컨트롤러를 반환하는 구조로 구성한다.

### 구현 인터페이스 구조

```java
public interface Controller {
    void service(HttpRequest request, HttpResponse response) throws Exception;
}

public abstract class AbstractController implements Controller {
    // HTTP method(GET/POST)에 따라 doGet / doPost 분기
}
```

## 5단계 - 동시성 확장하기

### 목표

Thread Pool을 적용하여 동시 접속 상황에서 안정적인 서버를 구현한다.

### 학습 목표

단순 기능 구현보다 WAS에서 Thread Pool의 동작 원리 이해가 핵심이다.

### 기능 요구사항

1. `Connector` 클래스에서 요청마다 스레드를 새로 생성하는 방식을 `ExecutorService` 기반 Thread Pool 방식으로 교체한다. 스레드 수는 `maxThreads` 변수로 지정하며, `acceptCount`와 `maxThreads`를 생성자 파라미터로 받는다.
2. `SessionManager`의 세션 컬렉션을 `ConcurrentHashMap` 등 동시성 컬렉션으로 교체하여 스레드 안전성과 원자성을 보장한다.

### 핵심 고민 포인트

- `acceptCount`와 `maxThreads`의 차이를 이해하고 설명할 수 있어야 한다.
