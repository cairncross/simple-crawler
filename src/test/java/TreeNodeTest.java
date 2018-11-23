import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeNodeTest {

    @Test
    public void print_shouldReturnCorrectString() {
        TreeNode root = new TreeNode("root");

        TreeNode levelOneChild0 = new TreeNode("0");
        TreeNode levelOneChild1 = new TreeNode("1");
        TreeNode levelOneChild2 = new TreeNode("2");

        TreeNode levelTwoChild0 = new TreeNode("1.0");
        TreeNode levelTwoChild1 = new TreeNode("1.1");
        TreeNode levelTwoChild2 = new TreeNode("1.2");

        TreeNode levelThreeChild0 = new TreeNode("1.1.0");

        levelTwoChild1.setChildren(new ArrayList<>(Arrays.asList(levelThreeChild0)));
        levelOneChild1.setChildren(new ArrayList<>(Arrays.asList(levelTwoChild0, levelTwoChild1, levelTwoChild2)));
        root.setChildren(new ArrayList<>(Arrays.asList(levelOneChild0, levelOneChild1, levelOneChild2)));

        String expected =
                "+- root\n" +
                "   +- 0\n" +
                "   +- 1\n" +
                "   |  +- 1.0\n" +
                "   |  +- 1.1\n" +
                "   |  |  +- 1.1.0\n" +
                "   |  +- 1.2\n" +
                "   +- 2\n";

        assertThat(root.print(root, "", true)).isEqualTo(expected);
    }
}
