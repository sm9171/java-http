package camp.nextstep;

public enum HttpMethod {
    GET,
    POST,
    PUT,
    DELETE;

    public static HttpMethod of(String method) {
        return HttpMethod.valueOf(method);
    }
}
