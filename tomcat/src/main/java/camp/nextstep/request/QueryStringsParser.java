package camp.nextstep.request;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QueryStringsParser {
    private final static String QUERY_STRINGS_DELIMITER = "&";
    private final static String QUERY_STRING_DELIMITER = "=";

    private QueryStringsParser() {
    }

    public static QueryStrings parse(String queryStrings) {
        String[] queries = queryStrings.split(QUERY_STRINGS_DELIMITER);
        return new QueryStrings(Arrays.stream(queries)
                .map(QueryStringsParser::parseQueryString)
                .collect(Collectors.toList()));
    }

    private static QueryString parseQueryString(String queryString) {
        String[] query = queryString.split(QUERY_STRING_DELIMITER);
        return new QueryString(query[0], query[1]);
    }
}
