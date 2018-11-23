import java.util.List;

public class TreeNode {

    private String url;
    private List<TreeNode> children;

    public TreeNode(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void print(TreeNode node, String indent, boolean isLastChildElement)
    {
        System.out.println(indent + "+- " + node.getUrl());
        indent += isLastChildElement ? "   " : "|  ";

        if (node.getChildren() != null) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                print(node.getChildren().get(i), indent, i == node.getChildren().size() - 1);
            }
        }
    }
}
