package camp.nextstep.request;

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
    public String getHeader(String name) {
        return headers.get(name);
    }
}
