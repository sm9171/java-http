package camp.nextstep;

import camp.nextstep.request.RequestLine;
import camp.nextstep.request.RequestLineParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RequestLineParserTest {

    @DisplayName("get요청을 파싱한다.")
    @Test
    void parseGetMethod() {
        // given
        final String requestString = "GET /index.html HTTP/1.1";

        // when
        final RequestLine requestLine = RequestLineParser.parse(requestString);

        // then
        assertSoftly(softly -> {
            softly.assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.GET);
            softly.assertThat(requestLine.getPath()).isEqualTo("/index.html");
            softly.assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
            softly.assertThat(requestLine.getVersion()).isEqualTo("1.1");
        });
    }

    @DisplayName("post요청을 파싱한다.")
    @Test
    void parsePostMethod() {
        // given
        final String requestString = "POST /index.html HTTP/1.1";

        // when
        final RequestLine requestLine = RequestLineParser.parse(requestString);

        // then
        assertSoftly(softly -> {
            softly.assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.POST);
            softly.assertThat(requestLine.getPath()).isEqualTo("/index.html");
            softly.assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
            softly.assertThat(requestLine.getVersion()).isEqualTo("1.1");
        });
    }

    @DisplayName("queryString을 파싱한다.")
    @Test
    void parseQueryString() {
        // given
        final String requestString = "GET /users?userId=javajigi&password=password&name=JaeSung HTTP/1.1";

        // when
        final RequestLine requestLine = RequestLineParser.parse(requestString);

        // then
        assertSoftly(softly -> {
            softly.assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.GET);
            softly.assertThat(requestLine.getPath()).isEqualTo("/users");
            softly.assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
            softly.assertThat(requestLine.getVersion()).isEqualTo("1.1");
            softly.assertThat(requestLine.getQueryStringValue("userId")).isEqualTo("javajigi");
            softly.assertThat(requestLine.getQueryStringValue("password")).isEqualTo("password");
            softly.assertThat(requestLine.getQueryStringValue("name")).isEqualTo("JaeSung");
        });
    }
}