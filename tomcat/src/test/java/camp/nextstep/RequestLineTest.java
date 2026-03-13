package camp.nextstep;

import camp.nextstep.request.RequestLine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestLineTest {

    @Test
    void parseGetRequestLine() {
        String url = "GET /users HTTP/1.1";
        RequestLine requestLine = RequestLine.create(url);
        assertSoftly(
                softly -> {
                    softly.assertThat(requestLine.getHttpMethod()).isEqualTo(HttpMethod.GET);
                    softly.assertThat(requestLine.getPath()).isEqualTo("/users");
                    softly.assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
                    softly.assertThat(requestLine.getVersion()).isEqualTo("1.1");
                }
        );
    }

    @Test
    void parsePostRequestLine() {
        String url = "POST /users HTTP/1.1";
        RequestLine requestLine = RequestLine.create(url);
        assertSoftly(
                softly -> {
                    softly.assertThat(requestLine.getHttpMethod()).isEqualTo(HttpMethod.POST);
                    softly.assertThat(requestLine.getPath()).isEqualTo("/users");
                    softly.assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
                    softly.assertThat(requestLine.getVersion()).isEqualTo("1.1");
                }
        );
    }

    @Test
    void parseGetRequestLineWithQueryString() {
        String url = "GET /users?userId=javajigi&password=password&name=JaeSung HTTP/1.1";
        RequestLine requestLine = RequestLine.create(url);
        assertSoftly(
                softly -> {
                    softly.assertThat(requestLine.getHttpMethod()).isEqualTo(HttpMethod.GET);
                    softly.assertThat(requestLine.getPath()).isEqualTo("/users");
                    softly.assertThat(requestLine.getQueryString().getValue("userId")).isEqualTo("javajigi");
                    softly.assertThat(requestLine.getQueryString().getValue("password")).isEqualTo("password");
                    softly.assertThat(requestLine.getQueryString().getValue("name")).isEqualTo("JaeSung");
                    softly.assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
                    softly.assertThat(requestLine.getVersion()).isEqualTo("1.1");
                }
        );
    }

    @Test
    void parseRequestLineWithEmptyQueryString() {
        String url = "GET /users? HTTP/1.1";
        RequestLine requestLine = RequestLine.create(url);

        assertSoftly(
                softly -> {
                    softly.assertThat(requestLine.getHttpMethod()).isEqualTo(HttpMethod.GET);
                    softly.assertThat(requestLine.getPath()).isEqualTo("/users");
                    softly.assertThat(requestLine.getQueryString().getValue("userId")).isNull();
                    softly.assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
                    softly.assertThat(requestLine.getVersion()).isEqualTo("1.1");
                }
        );
    }

    @Test
    void throwExceptionWhenHttpMethodIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> RequestLine.create("INVALID /users HTTP/1.1"));
    }

    @Test
    void throwExceptionWhenRequestLineDoesNotContainPath() {
        assertThrows(IllegalArgumentException.class, () -> RequestLine.create("GET HTTP/1.1"));
    }

    @Test
    void throwExceptionWhenProtocolAndVersionFormatIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> RequestLine.create("GET /users HTTP1.1"));
    }

    @Test
    void throwExceptionWhenRequestLineIsNull() {
        assertThrows(IllegalArgumentException.class, () -> RequestLine.create(null));
    }

    @Test
    void throwExceptionWhenRequestLineIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> RequestLine.create("   "));
    }
}
