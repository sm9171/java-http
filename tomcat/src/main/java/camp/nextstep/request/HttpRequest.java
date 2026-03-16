package camp.nextstep.request;

import camp.nextstep.QueryString;

public class HttpRequest {
    private final RequestLine requestLine;
    private final RequestHeaders headers;
    public HttpRequest(RequestLine requestLine, RequestHeaders headers) {
        this.requestLine = requestLine;
        this.headers = headers;
    }
    public String getPath() {
        return requestLine.getPath();
    }

    public QueryString getQueryString() {
        return requestLine.getQueryString();
    }

    public String getHeader(String name) {
        return headers.get(name);
    }
}
