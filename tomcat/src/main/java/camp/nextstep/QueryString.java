package camp.nextstep;

import java.util.HashMap;
import java.util.Map;

public class QueryString {
    private Map<String, String> queryStrings;

    private QueryString(Map<String, String> queryStrings) {
        this.queryStrings = queryStrings;
    }

    public static QueryString parse(String queryStringStr) {
        if (queryStringStr == null || queryStringStr.isBlank()) {
            return new QueryString(new HashMap<>());
        }
        Map<String, String> queryStrings = new HashMap<>();
        String[] spritPathAndQueryString = queryStringStr.split("&");
        for (String spritPath : spritPathAndQueryString) {
            String[] split = spritPath.split("=", 2);
            if (split.length != 2) {
                throw new IllegalArgumentException("Query parameter must contain '='");
            }
            queryStrings.put(split[0], split[1]);
        }
        return new QueryString(queryStrings);
    }

    public String getValue(String key) {
        return queryStrings.get(key);
    }
}
