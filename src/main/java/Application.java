import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Application {

    public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {
        String startUrl = args[0];
        String domain = args[1];
        Crawler crawler = new Crawler(startUrl, domain);
        TreeNode root = crawler.run();
        String result = root.print(root, "", true);
        System.out.println(result);
        try (PrintWriter out = new PrintWriter("" + domain + "-sitemap")) {
            out.println(result);
            System.out.println("created " + domain + "-sitemap");
        }
    }
}
