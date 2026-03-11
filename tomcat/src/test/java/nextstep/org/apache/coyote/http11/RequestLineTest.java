package nextstep.org.apache.coyote.http11;

import camp.nextstep.HttpMethod;
import camp.nextstep.RequestLine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class RequestLineTest {

    @Test
    void test1() {
        String url = "GET /users HTTP/1.1";
        RequestLine requestLine = new RequestLine(url);
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
    void test2() {
        String url = "POST /users HTTP/1.1";
        RequestLine requestLine = new RequestLine(url);
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
    void test3() {
        String url = "GET /users?userId=javajigi&password=password&name=JaeSung HTTP/1.1";
        RequestLine requestLine = new RequestLine(url);
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
}
