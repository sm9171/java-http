package camp.nextstep.resolver;

public class ContentTypeResolver {
    public String resolve(String requestPath) {
        if (requestPath.endsWith(".html")) return "text/html;charset=utf-8";
        if (requestPath.endsWith(".css")) return "text/css";
        if (requestPath.endsWith(".js")) return "application/javascript";
        if (requestPath.endsWith(".png")) return "image/png";
        if (requestPath.endsWith(".jpg") || requestPath.endsWith(".jpeg")) return "image/jpeg";
        return "application/octet-stream";
    }
}
