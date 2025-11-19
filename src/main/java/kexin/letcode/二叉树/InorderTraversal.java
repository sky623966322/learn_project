package kexin.letcode.二叉树;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    private static void inOrder(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        inOrder(treeNode.left, list);
        list.add(treeNode.val);
        inOrder(treeNode.right, list);
    }

    private static void preOrder(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        list.add(treeNode.val);
        preOrder(treeNode.left, list);
        preOrder(treeNode.right, list);
    }

    private static void postOrder(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        postOrder(treeNode.left, list);
        postOrder(treeNode.right, list);
        list.add(treeNode.val);
    }

    private static void levelOrder(TreeNode treeNode, List<List<Integer>> list, int level) {
        // 如果遍历到当前层，则创建每层list
        if(list.size() == level) {
            list.add(new ArrayList<>());
        }
        List<Integer> levelList = list.get(level);
        levelList.add(treeNode.val);
        level++;
        if (treeNode.left != null) {
            levelOrder(treeNode.left, list, level);
        }
        if (treeNode.right != null) {
            levelOrder(treeNode.right, list, level);
        }
    }
}
