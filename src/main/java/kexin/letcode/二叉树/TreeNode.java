package kexin.letcode.二叉树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        append(sb, this);
        return sb.substring(0, sb.length() - 1);
    }

    private void append(StringBuilder sb, TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        sb.append(treeNode.val).append(",");
        append(sb, treeNode.left);
        append(sb, treeNode.right);
    }

    public static void main(String[] args) {
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(2, treeNode3, treeNode4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode5);
       /* List<Integer> list = inorderTraversal(treeNode1);
        StringBuilder sb = new StringBuilder();
        list.forEach(item-> sb.append(item).append(" "));
        System.out.println(sb);*/
        List<List<Integer>> levelOrder = levelOrder2(treeNode1);
        System.out.println(levelOrder);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    public static void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    public static List<Integer> preTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    public static void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    public static List<Integer> postTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    public static void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }

    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.size() > 0) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode item = queue.poll();
                tmp.add(item.val);
                if (item.left != null) queue.add(item.left);
                if (item.right != null) queue.add(item.right);
            }
            result.add(tmp);
        }
        return result;
    }


    public static List<List<Integer>> levelOrder2(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        levelOrderInvers(root, res, 0);
        return res;
    }

    /**
     * 递归思路：
     * 1、List<List<Integer>>索引为层次序号，根据level获得每层的List<Integer>
     * 2、终止条件为TreeNode为空
     *
     * @param treeNode
     */
    public static void levelOrderInvers(TreeNode treeNode, List<List<Integer>> list, int level){
        if (treeNode == null) {
            return;
        }
        if (list.size() < level + 1) {
            list.add(new ArrayList<>());
        }
        List<Integer> subList = list.get(level);
        subList.add(treeNode.val);
        level++; // 当前层结束，下一层+1
        levelOrderInvers(treeNode.left, list, level);
        levelOrderInvers(treeNode.right, list, level);
    }

}
