package camp.nextstep.resolver;

public class StaticResource {
    private final byte[] body;
    private final String contentType;
    public StaticResource(byte[] body, String contentType) {
        this.body = body;
        this.contentType = contentType;
    }
    public byte[] getBody() {
        return body;
    }
    public String getContentType() {
        return contentType;
    }
}
