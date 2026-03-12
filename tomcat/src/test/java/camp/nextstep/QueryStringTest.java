package camp.nextstep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryStringTest {

    @Test
    void parseEmptyQueryString() {
        QueryString queryString = QueryString.parse("");

        assertNull(queryString.getValue("userId"));
    }

    @Test
    void parseNullQueryString() {
        QueryString queryString = QueryString.parse(null);

        assertNull(queryString.getValue("userId"));
    }

    @Test
    void parseSingleQueryParameter() {
        QueryString queryString = QueryString.parse("userId=javajigi");

        assertEquals("javajigi", queryString.getValue("userId"));
    }

    @Test
    void parseMultipleQueryParameters() {
        QueryString queryString = QueryString.parse("userId=javajigi&password=password&name=JaeSung");

        assertAll(
                () -> assertEquals("javajigi", queryString.getValue("userId")),
                () -> assertEquals("password", queryString.getValue("password")),
                () -> assertEquals("JaeSung", queryString.getValue("name"))
        );
    }

    @Test
    void throwExceptionWhenQueryParameterDoesNotContainEquals() {
        assertThrows(IllegalArgumentException.class, () -> QueryString.parse("userId"));
    }

    @Test
    void parseQueryParameterWithEmptyValue() {
        QueryString queryString = QueryString.parse("userId=");

        assertEquals("", queryString.getValue("userId"));
    }

    @Test
    void parseQueryParameterContainingEqualsInValue() {
        QueryString queryString = QueryString.parse("token=a=b=c");

        assertEquals("a=b=c", queryString.getValue("token"));
    }

    @Test
    void overwriteWhenDuplicateKeysExist() {
        QueryString queryString = QueryString.parse("userId=javajigi&userId=jaesung");

        assertEquals("jaesung", queryString.getValue("userId"));
    }

    @Test
    void throwExceptionWhenMixedValidAndInvalidParametersExist() {
        assertThrows(IllegalArgumentException.class, () -> QueryString.parse("userId=javajigi&password"));
    }
}
