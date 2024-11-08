package camp.nextstep.request;

import java.util.List;

public class QueryStrings {
    private final List<QueryString> queryStrings;

    public QueryStrings(List<QueryString> queryStrings) {
        this.queryStrings = queryStrings;
    }

    public static QueryStrings create(final String queryString) {
        return QueryStringsParser.parse(queryString);
    }

    public static QueryStrings empty() {
        return new QueryStrings(List.of());
    }

    public String get(final String key) {
        return queryStrings.stream()
                .filter(queryString -> queryString.isKey(key))
                .findFirst()
                .map(QueryString::getValue)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 key 입니다."));
    }
}
