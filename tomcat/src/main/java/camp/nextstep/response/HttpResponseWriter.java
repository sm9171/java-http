package camp.nextstep.response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpResponseWriter {
    public void write(OutputStream outputStream, HttpResponse response) throws IOException {
        StringBuilder head = new StringBuilder();
        head.append(response.getStatusLine()).append(" ").append("\r\n");
        for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
            head.append(header.getKey()).append(": ").append(header.getValue()).append(" ").append("\r\n");
        }
        head.append("\r\n");
        outputStream.write(head.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.write(response.getBody());
        outputStream.flush();
    }
}
