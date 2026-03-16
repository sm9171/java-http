package camp.nextstep.request;

import java.util.Map;

public class RequestHeaders {
    private final Map<String, String> headers;
    private RequestHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public static RequestHeaders create(Map<String, String> headers) {
        return new RequestHeaders(headers);
    }

    public String get(String name) {
        return headers.get(name);
    }
}
