package camp.nextstep.response;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse {
    private final String statusLine;
    private final Map<String, String> headers;
    private final byte[] body;
    private HttpResponse(String statusLine, Map<String, String> headers, byte[] body) {
        this.statusLine = statusLine;
        this.headers = headers;
        this.body = body;
    }
    public static HttpResponse ok(String contentType, byte[] body) {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Content-Type", contentType);
        headers.put("Content-Length", String.valueOf(body.length));
        return new HttpResponse("HTTP/1.1 200 OK", headers, body);
    }
    public String getStatusLine() {
        return statusLine;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public byte[] getBody() {
        return body;
    }
}
