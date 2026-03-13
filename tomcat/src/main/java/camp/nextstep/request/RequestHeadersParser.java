package camp.nextstep.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestHeadersParser {
    private RequestHeadersParser() {}

    public static RequestHeaders parse(BufferedReader br) throws IOException {
        Map<String, String> headers = new HashMap<>();
        for (String line; (line = br.readLine()) != null; ) {
            line = line.trim();
            if (line.isEmpty()) {
                break;
            }
            String[] parts = line.split(":", 2);
            headers.put(parts[0].trim(), parts[1].trim());
        }
        return RequestHeaders.create(headers);
    }
}
