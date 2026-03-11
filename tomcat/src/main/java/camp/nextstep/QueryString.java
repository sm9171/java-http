package camp.nextstep;

import java.util.HashMap;
import java.util.Map;

public class QueryString {
    public Map<String, String> queryStrings;

    public QueryString(Map<String, String> queryStrings) {
        this.queryStrings = queryStrings;
    }

    public static QueryString parse(String queryStringStr) {
        Map<String, String> queryStrings = new HashMap<>();
        String[] spritPathAndQueryString = queryStringStr.split("&");
        for (String spritPath : spritPathAndQueryString) {
            String[] split = spritPath.split("=");
            queryStrings.put(split[0], split[1]);
        }
        return new QueryString(queryStrings);
    }

    public String getValue(String key) {
        return queryStrings.get(key);
    }
}
