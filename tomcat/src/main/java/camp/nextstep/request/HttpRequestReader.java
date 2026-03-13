package camp.nextstep.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpRequestReader {
    private HttpRequestReader() {}

    public static HttpRequest read(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        RequestLine requestLine = RequestLine.create(br.readLine());
        RequestHeaders headers = RequestHeadersParser.parse(br);
        return new HttpRequest(requestLine, headers);
    }
}
