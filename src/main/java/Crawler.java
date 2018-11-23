import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Crawler {

    private Queue<TreeNode> queue = new LinkedList<>();
    private Set<String> visited = new HashSet<>();
    private Set<String> seen = new HashSet<>();
    private String domain;
    private TreeNode root;

    public Crawler(String startUrl, String domain) throws IllegalArgumentException {
        this.domain = domain;
        if (!isWithinDomain(startUrl)) {
            throw new IllegalArgumentException("startUrl " + startUrl + " is not within domain " + domain);
        }
        root = new TreeNode(startUrl);
        queue.add(root);
    }


    public void run() {
        System.out.println("running...");
        TreeNode node;
        while(!queue.isEmpty()) {
            node = queue.remove();
            String url = node.getUrl();
            if (!visited.contains(url)) {
                node.setChildren(crawl(url));
            }
        }
        System.out.println("finished");
    }

    private List<TreeNode> crawl(String url) {
        System.out.println("crawling: " + url);
        visited.add(url);
        List<TreeNode> children = new ArrayList<>();
        Elements links;
        try {
            links = Jsoup.connect(url).get().select("a[href]");
            for (Element link: links) {
                String childUrl = link.absUrl("href");
                if (isWithinDomain(childUrl) && !seen.contains(childUrl)) {
                    seen.add(childUrl);
                    TreeNode child = new TreeNode(childUrl);
                    children.add(child);
                    queue.add(child);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return children;
    }

    public boolean isWithinDomain(String url) {
        if (url.matches(".*//" + domain + "(/.*)?")
            || url.matches("(.*\\.)?" + domain + "(/.*)?")) {
            return true;
        }
        return false;
    }
}
