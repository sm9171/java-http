package camp.nextstep;

public class RequestLine {
    private final HttpMethod method;
    private final String path;
    private final String protocol;
    private final String version;

    public RequestLine(HttpMethod method, String path, String protocol, String version) {
        this.method = method;
        this.path = path;
        this.protocol = protocol;
        this.version = version;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getVersion() {
        return version;
    }
}
