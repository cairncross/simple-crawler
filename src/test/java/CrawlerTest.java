import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CrawlerTest {

    @Test
    public void isWithinDomain_shouldReturnTrue_givenURLWithinDomain() {
        String domain = "domain.com";
        Crawler crawler = new Crawler("start.domain.com", domain);
        String str1 = "subdomain.domain.com";
        String str2 = "http://domain.com";
        String str3 = "https://domain.com";
        String str4 = "http://foo.domain.com";
        String str5 = "https://foo.bar.domain.com";
        String str6 = "domain.com/123/456/foo.bar/baz.png";
        String str7 = "foo.bar.baz.domain.com/123";

        assertThat(crawler.isWithinDomain(str1)).isTrue();
        assertThat(crawler.isWithinDomain(str2)).isTrue();
        assertThat(crawler.isWithinDomain(str3)).isTrue();
        assertThat(crawler.isWithinDomain(str4)).isTrue();
        assertThat(crawler.isWithinDomain(str5)).isTrue();
        assertThat(crawler.isWithinDomain(str6)).isTrue();
        assertThat(crawler.isWithinDomain(str7)).isTrue();
    }

    @Test
    public void isWithinDomain_shouldReturnFalse_givenURLNotWithinDomain() {
        String domain = "domain.com";
        Crawler crawler = new Crawler("start.domain.com", domain);
        String str1 = "domain.foo.com";
        String str2 = "www.twitter.com/something-about-domain.com";
        String str3 = "http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F11%2F15%2Fmaking-health-insurance-conversational-insights-from-chatbot-design%2F&hashtags=wiprodigital";
        String str4 = "http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F11%2F15%2Fmaking-health-insurance-conversational-insights-from-chatbot-design%2F&title=Making Health";
        String str5 = "https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2F2018%2F11%2F15%2Fmaking-health-insurance-conversational-insights-from-chatbot-design%2F";

        assertThat(crawler.isWithinDomain(str1)).isFalse();
        assertThat(crawler.isWithinDomain(str2)).isFalse();
        assertThat(crawler.isWithinDomain(str3)).isFalse();
        assertThat(crawler.isWithinDomain(str4)).isFalse();
        assertThat(crawler.isWithinDomain(str5)).isFalse();
    }
}
