package camp.nextstep.request;

import camp.nextstep.HttpMethod;

public class RequestLine {
    private final HttpMethod method;
    private final String path;
    private final String protocol;
    private final String version;
    private final QueryStrings queryStrings;

    public RequestLine(HttpMethod method, String path, String protocol, String version, QueryStrings queryStrings) {
        this.method = method;
        this.path = path;
        this.protocol = protocol;
        this.version = version;
        this.queryStrings = queryStrings;
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

    public String getQueryStringValue(String key) {
        return queryStrings.get(key);
    }
}
