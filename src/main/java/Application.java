public class Application {

    public static void main(String[] args) throws IllegalArgumentException {
        Crawler crawler = new Crawler("https://buildit.wiprodigital.com", "wiprodigital.com");
        TreeNode root = crawler.run();
        root.print(root, "", true);
    }
}
