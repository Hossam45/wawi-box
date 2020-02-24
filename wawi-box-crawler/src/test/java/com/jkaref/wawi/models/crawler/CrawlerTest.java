package com.jkaref.wawi.models.crawler;

import com.jkaref.wawi.api.ProductLocation;
import com.jkaref.wawi.crawler.Crawler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
public class CrawlerTest {

    private static final String EXPECTED = "http://www.api.com/test-product-location/1";

    private ProductLocation location;
    private InputStream in;

    @Before
    public void setup() {
        location = new ProductLocation("test-prefix/test-product-location");
    }

    @Test
    public void testBuildUrl_withTrailingApiSlash() {

        final String api = "http://www.api.com/";
        final String prefix = "test-prefix/";

        final String actual = Crawler.buildUrl(location, api, prefix);

        assertThat(actual)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(EXPECTED);

    }

    @Test
    public void testBuildUrl_withoutTrailingApiSlash() {

        final String api = "http://www.api.com";
        final String prefix = "test-prefix/";

        final String actual = Crawler.buildUrl(location, api, prefix);

        if (actual.equals(EXPECTED)) {
            assertThat(actual)
                    .isNotNull()
                    .isNotEmpty()
                    .isEqualTo(EXPECTED);
        } else {
            final String slash = String.format("%s/", api);
            final String actual2 = Crawler.buildUrl(location, slash, prefix);
            assertThat(actual2)
                    .isNotNull()
                    .isNotEmpty()
                    .isEqualTo(EXPECTED);

        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void testBuildUrl_withNull() {

        final String actual = Crawler.buildUrl(location, null, null);

    }


    @Test
    public void testCrawlProductLocations() {

        try {
            InputStream inputStream = Crawler.crawlProductLocations("http://example.com");

            assertThat(inputStream).isNotNull();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrawlProductLocations_withNull() throws IOException {

        InputStream inputStream = Crawler.crawlProductLocations(null);

        fail("This should never happen!");

    }

    @Test(expected = MalformedURLException.class)
    public void testCrawlProductLocations_withIllegalUrl() throws IOException {

        InputStream inputStream = Crawler.crawlProductLocations("blah");

        fail("This should never happen!");

    }
}