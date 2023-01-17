package pl.bobak.integrations.fdademo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OpenfdaSearchServiceTest {

    @Autowired
    private OpenfdaSearchService searchServiceUnderTest;

    @Test
    void shouldCreateUrlAsExpected() {
        // given
        String expectedUrl = "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:\"test input\"";
        String phrase = "test input";

        // when
        String computedUrl = searchServiceUnderTest.createUrl(phrase);

        // then
        assertEquals(expectedUrl, computedUrl);
    }

}
