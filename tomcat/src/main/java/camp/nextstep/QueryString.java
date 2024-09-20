package camp.nextstep;

public class QueryString {
    private final String key;
    private final String value;

    public QueryString(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public boolean isKey(final String key) {
        return this.key.equals(key);
    }

    public String getValue() {
        return value;
    }
}
