package camp.nextstep.request;

import camp.nextstep.HttpMethod;

public class RequestLineParser {
    private final static String REQUEST_LINE_DELIMITER = " ";
    private final static String PATH_QUERY_DELIMITER = "\\?";
    private final static String PROTOCOL_VERSION_DELIMITER = "/";

    private RequestLineParser() {
    }

    public static RequestLine parse(String requestLine) {
        String[] split = requestLine.split(REQUEST_LINE_DELIMITER);
        HttpMethod method = HttpMethod.of(split[0]);
        String[] pathAndQuery = split[1].split(PATH_QUERY_DELIMITER);
        String[] protocolAndVersion = split[2].split(PROTOCOL_VERSION_DELIMITER);
        String protocol = protocolAndVersion[0];
        String version = protocolAndVersion[1];

        if(notExistQueryStrings(pathAndQuery)) {
            return new RequestLine(method, pathAndQuery[0], protocol, version, QueryStrings.empty());
        }

        String path = pathAndQuery[0];
        QueryStrings queryStrings = QueryStrings.create(pathAndQuery[1]);
        return new RequestLine(method, path, protocol, version, queryStrings);
    }

    private static boolean notExistQueryStrings(final String[] pathAndQuery) {
        return pathAndQuery.length == 1;
    }
}
