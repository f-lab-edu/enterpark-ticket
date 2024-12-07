### API

- URL을 구성할 때 맨 앞에 버전을 명시한다. (/v1/...)
- URL은 소문자로만 구성하고, _(언더바)를 사용하지 않고, -(하이픈)을 사용한다.
- URL에서 PathVariable 은 PK 값만 허용한다.
- PathVariable 사이에는 PathVariable이 나타내는 의미(보통 Entity 이름)를 뜻하면 문자열을 복수형으로 넣는다.

```
/v1/users
/v1/users/1 # PK 1인 user
/v1/users/1/tickets # PK 1인 user의 모든 티켓목록
/v1/users/1/tickets/20 # PK 1인 user의 PK가 20인 티켓
```

- 조회 조건 등은 조회 조건에 따른 URL을 붙이고 Query Parameter로 한다.

```
/v1/users/tickets?ticketStartDate=2024-11-29&ticketEndDate=2024-11-30
```

조회 조건을 PathVariable 로 넣는 것은 Spring 에게 PathVariable에 특수문자가 들어왔을 경우 등에 오동작을
일으키기 쉬우며, 조회 조건 DTO를 만들기도 어렵고, 같은 타입의 조회 조건이 2개 이상 만들어질 때 혼란을 일으키게 된다.
PathVariable의 무분별한 남발은 API 이해도를 매우 떨어뜨린다.

아래는 path로 했을때 가독성을 떨어트리는 예제이다.

```
/v1/users/{userId}/{ticketStartDate} # 이런 API가 있을 때
/v1/users/{userId}/{ticketEndDate} # 라는 API 가 또 추가 된다면 어떻게 해야하나? 그게 아니더라도
/v1/users/{userId}/{ticketId} # 이런게 추가된다면 Spring MVC는 위의 것들과 어떻게 다르게 파싱해야할까?
# API URL access 로그를 보는 사람은 이 API을 어떻게 구분해서 해석할 수 있을까?
```

- URL에는 동사보다는 명사를 사용한다.
- 하지만 GET, POST, PUT, PATCH, DELETE 로는 설명이 잘 되지 않는 행위 혹은 기본 메소드이더라도 명시적으로 하고자 한다면 동사를 뒤에 붙일 수 있다.

```
/v1/users/{userId}/update-credit4u
```

### 기본 응답

- 모든 성공한 응답은 HTTP Status 200 번대로 보낸다.
- 모든 응답은 `ResponseEntity<응답 객체>`로 감싸서 응답한다.
- 클라이언트 요청이 성공한 경우 `200` 성공 응답
- 새로운 리소스를 생성한 경우 `201` 성공 응답

### Exception Handling

- 애플리케이션 Exception 응답은 4xx 번대 Http Status 로 처리한다. 500은 Tomcat, 외부 API 등의 오류에서만 사용한다.
- GlobalExceptionHandler에서 예외를 일관성있게 처리한다.
- 도메인 별로 ErrorCode를 만들어서 BaseErrorCode를 상속받아 처리한다.

```kotlin
enum class UserErrorCode(private val status: Int, private val message: String) : BaseErrorCode {

    USER_NOT_FOUND(NOT_FOUND, "존재하지 않는 회원입니다.");

    override fun getErrorDescription(): ErrorDescription {
        return ErrorDescription(name, message, status)
    }
}
```

- 공통 ErrorCode는 GlobalErrorCode에서 처리한다.

```kotlin
enum class GlobalErrorCode(private val status: Int, private val message: String) : BaseErrorCode {

    INVALID_TOKEN(UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 토큰입니다."),
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 오류가 발생하였습니다.");

    override fun getErrorDescription(): ErrorDescription {
        return ErrorDescription(name, message, status)
    }
}
```

- 개별 Exception은 EnterparkTicketException을 상속받아 생성한다.

```kotlin
class NotFoundUserException : EnterparkTicketException(UserErrorCode.USER_NOT_FOUND)
```

- 인자나 상태가 잘못된 경우 `400` 에러 발생
- 인증 관련 오류인 경우 `401` 에러 발생
- 인가 관련 오류인 경우 `403` 에러 발생
- 리소스가 존재하지 않는 경우 `404` 에러 발생
- DB에 존재하는 데이터와 충돌한 경우 `409` 에러 발생
- 이외 서버에서 발생하는 예외인 경우 `500` 에러 발생

### ErrorResponse

```json
{
  "code": "USER_NOT_FOUND",
  "message": "존재하지 않는 회원입니다."
}
```

### Log Level

- `4xx` 에러는 warn 로그
- `5xx` 에러는 error 로그
- 이외에 필요하다고 생각되는 정보는 info 로그를 적는다.

### DTO

| 의도            | 클래스 이름                                                   |
|---------------|----------------------------------------------------------|
| 기본            | 요청 : `{service 메소드 이름}Request` ex) RegisterUserRequest   |
|               | 응답 : `{service 메소드 이름}Response` ex) RegisterUserResponse |
| 공통으로 사용하는 클래스 | 요청 : `{해당 클래스의 의미}Request` ex) UserTokenRequest          |
|               | 응답 : `{해당 클래스의 의미}Response` ex) UserTokenResponse        |

- DTO는 각 클래스마다 파일을 분리해서 생성한다.
- DTO 내부에 `of()`, `from()`, `to()` 메소드를 사용해서 생성한다.

### branch 규칙
- `{feat, chore 등}/#{이슈 번호}-이름_이름`
- 브랜치 이름이 길어질 경우, _(언더바)를 사용한다.

### commit 규칙
- 제목(Subject) - `#{이슈 번호} [feat, chore 등] 커밋 메시지`
- 커밋 메시지가 길어질 경우, 본문(Body)도 함께 사용한다.