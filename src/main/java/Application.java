public class Application {

    public static void main(String[] args) throws IllegalArgumentException {
        Crawler crawler = new Crawler("https://buildit.wiprodigital.com", "wiprodigital.com");
        crawler.run();
    }
}
