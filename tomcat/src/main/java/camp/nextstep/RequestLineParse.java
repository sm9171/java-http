package camp.nextstep;

public class RequestLineParse {
    private RequestLineParse() {
    }

    public static RequestLine parse(String requestLine) {
        String[] tokens = requestLine.split(" ");
        HttpMethod method = HttpMethod.of(tokens[0]);
        String path = tokens[1];
        String[] protocolAndVersion = tokens[2].split("/");
        String protocol = protocolAndVersion[0];
        String version = protocolAndVersion[1];
        return new RequestLine(method, path, protocol, version);
    }
}
