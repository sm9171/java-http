package camp.nextstep.response;

import java.util.List;

public class Response {
    private final String StatusLine;
    private final List<ResponseHeader> headers;
    private final String body;

    public Response(String statusLine, List<ResponseHeader> headers, String body) {
        StatusLine = statusLine;
        this.headers = headers;
        this.body = body;
    }
}
