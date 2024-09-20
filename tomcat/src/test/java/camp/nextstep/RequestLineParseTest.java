package camp.nextstep;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RequestLineParseTest {

    @DisplayName("get요청을 파싱한다.")
    @Test
    void parseGetMethod() {
        // given
        final String requestString = "GET /index.html HTTP/1.1";

        // when
        final RequestLine requestLine = RequestLineParse.parse(requestString);

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
        final RequestLine requestLine = RequestLineParse.parse(requestString);

        // then
        assertSoftly(softly -> {
            softly.assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.POST);
            softly.assertThat(requestLine.getPath()).isEqualTo("/index.html");
            softly.assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
            softly.assertThat(requestLine.getVersion()).isEqualTo("1.1");
        });
    }
}