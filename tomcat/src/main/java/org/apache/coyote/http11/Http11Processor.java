package org.apache.coyote.http11;

import camp.nextstep.resolver.StaticResource;
import camp.nextstep.resolver.StaticResourceResolver;
import camp.nextstep.request.HttpRequest;
import camp.nextstep.request.HttpRequestReader;
import camp.nextstep.resolver.ContentTypeResolver;
import camp.nextstep.exception.UncheckedServletException;
import camp.nextstep.response.HttpResponse;
import camp.nextstep.response.HttpResponseWriter;
import org.apache.coyote.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;

public class Http11Processor implements Runnable, Processor {

    private static final Logger log = LoggerFactory.getLogger(Http11Processor.class);

    private final Socket connection;

    public Http11Processor(final Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        log.info("connect host: {}, port: {}", connection.getInetAddress(), connection.getPort());
        process(connection);
    }

    @Override
    public void process(final Socket connection) {
        try (final var inputStream = connection.getInputStream();
             final var outputStream = connection.getOutputStream()) {
            HttpRequest httpRequest = HttpRequestReader.read(inputStream);
            StaticResourceResolver staticResourceResolver = new StaticResourceResolver(new ContentTypeResolver());
            StaticResource staticResource = staticResourceResolver.resolve(httpRequest.getPath());

            HttpResponse httpResponse = HttpResponse.ok(staticResource.getContentType(), staticResource.getBody());
            HttpResponseWriter httpResponseWriter = new HttpResponseWriter();
            httpResponseWriter.write(outputStream, httpResponse);
        } catch (IOException | UncheckedServletException | URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
    }
}
