package org.apache.coyote.http11;

import camp.nextstep.exception.UncheckedServletException;
import org.apache.coyote.Processor;
import org.apache.coyote.http11.requestline.ContentType;
import org.apache.coyote.http11.requestline.RequestLine;
import org.apache.coyote.http11.requestline.RequestLineParser;
import org.apache.coyote.http11.util.UtilString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;

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
             final var outputStream = connection.getOutputStream();
             final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (bufferedReader.lines() == null) {
                return;
            }
            RequestLineParser requestLineParser = new RequestLineParser(bufferedReader.readLine());

            RequestLine requestLine = RequestLine.from(requestLineParser);

            String urlPath = requestLine.getUrlPath();

            urlPath = urlPath.equals("/") ? "index.html" : urlPath;

            URL resource = getClass().getClassLoader().getResource("static/" + urlPath);

            String extension = UtilString.parseExtension(urlPath);

            final String responseBody = new String(Files.readAllBytes(new File(resource.getFile()).toPath()));

            final var response = String.join("\r\n",
                    "HTTP/1.1 200 OK ",
                    "Content-Type: " + ContentType.findByExtension(extension).getContentType() + ";charset=utf-8 ",
                    "Content-Length: " + responseBody.getBytes().length + " ",
                    "",
                    responseBody);

            outputStream.write(response.getBytes());
            outputStream.flush();
        } catch (IOException | UncheckedServletException e) {
            log.error(e.getMessage(), e);
        }
    }
}
