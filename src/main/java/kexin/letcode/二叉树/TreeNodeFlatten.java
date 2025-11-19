package kexin.letcode.二叉树;

public class TreeNodeFlatten {

    public static void flatten(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        flatten(treeNode.left);
    }

}
