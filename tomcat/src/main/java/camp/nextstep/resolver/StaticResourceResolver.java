package camp.nextstep.resolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticResourceResolver {
    private final ContentTypeResolver contentTypeResolver;
    public StaticResourceResolver(ContentTypeResolver contentTypeResolver) {
        this.contentTypeResolver = contentTypeResolver;
    }
    public StaticResource resolve(String requestPath) throws IOException, URISyntaxException {
        if ("/".equals(requestPath)) {
            return new StaticResource("Hello world!".getBytes(StandardCharsets.UTF_8), contentTypeResolver.resolve("/index.html"));
        }

        var resource = getClass().getClassLoader().getResource("static" + requestPath);
        Path path = Path.of(resource.toURI());
        byte[] body = Files.readAllBytes(path);
        String contentType = contentTypeResolver.resolve(requestPath);
        return new StaticResource(body, contentType);
    }
}
