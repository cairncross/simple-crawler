import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Crawler {

    private Queue<String> queue = new LinkedList<>();
    private Set<String> visited = new HashSet<>();
    private String domain;

    public Crawler(String startUrl, String domain) throws IllegalArgumentException {
        this.domain = domain;
        if (!isWithinDomain(startUrl)) {
            throw new IllegalArgumentException("startUrl " + startUrl + " is not within domain " + domain);
        }
        queue.add(startUrl);
    }


    public void run() {
        System.out.println("running...");
        String url;
        while(!queue.isEmpty()) {
            url = queue.remove();
            if (!visited.contains(url)) {
                crawl(url);
            }
        }
        System.out.println("finished");
    }

    public void crawl(String url) {
        System.out.println("crawling: " + url);
        visited.add(url);
        Elements links;
        try {
            links = Jsoup.connect(url).get().select("a[href]");
            for (Element link: links) {
                if (isWithinDomain(link.absUrl("href"))) {
                    queue.add(link.absUrl("href"));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isWithinDomain(String url) {
        if (url.matches(".*//" + domain + "(/.*)?")
            || url.matches("(.*\\.)?" + domain + "(/.*)?")) {
            return true;
        }
        return false;
    }
}
