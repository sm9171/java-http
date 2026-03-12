package camp.nextstep;

public class RequestLine {
    private HttpMethod httpMethod;
    private String path;
    private String protocol;
    private String version;
    private QueryString queryString;

    public RequestLine(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Request line must not be null or blank");
        }

        String[] splitUrl = url.split(" ");
        if (splitUrl.length != 3 || splitUrl[1].isBlank()) {
            throw new IllegalArgumentException("Request line must contain method, path, and protocol/version");
        }

        httpMethod = HttpMethod.valueOf(splitUrl[0]);
        String pathAndQueryString = splitUrl[1];
        path = pathAndQueryString;
        if (pathAndQueryString.contains("?")) {
            String[] splitPathAndQueryString = pathAndQueryString.split("\\?", 2);
            path = splitPathAndQueryString[0];
            String query = splitPathAndQueryString.length == 2 ? splitPathAndQueryString[1] : "";
            queryString = QueryString.parse(query);
        }

        String protocolAndVersion = splitUrl[2];
        String[] splitProtocolAndVersion = protocolAndVersion.split("/", 2);
        if (splitProtocolAndVersion.length != 2 || splitProtocolAndVersion[0].isBlank() || splitProtocolAndVersion[1].isBlank()) {
            throw new IllegalArgumentException("Protocol and version must be separated by '/'");
        }
        protocol = splitProtocolAndVersion[0];
        version = splitProtocolAndVersion[1];
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
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

    public QueryString getQueryString() {
        return queryString;
    }
}
