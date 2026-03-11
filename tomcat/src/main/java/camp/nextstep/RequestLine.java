package camp.nextstep;

public class RequestLine {
    private HttpMethod httpMethod;
    private String path;
    private String protocol;
    private String version;
    private QueryString queryString;

    public RequestLine(String url) {
        String[] splitUrl = url.split(" ");
        httpMethod = HttpMethod.valueOf(splitUrl[0]);
        String pathAndQueryString = splitUrl[1];
        if (pathAndQueryString.contains("?")) {
            String[] spritPathAndQueryString = pathAndQueryString.split("\\?");
            path = spritPathAndQueryString[0];
            queryString = QueryString.parse(spritPathAndQueryString[1]);
        } else {
            path = pathAndQueryString;
        }
        String protocolAndVersion = splitUrl[2];
        String[] splitProtocolAndVersion = protocolAndVersion.split("/");
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
