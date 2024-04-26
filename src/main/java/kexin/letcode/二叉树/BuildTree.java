package kexin.letcode.二叉树;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuildTree {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7}, inorder = {9,3,15,20,7};
        TreeNode treeNode = buildTree(preorder, inorder);
        System.out.println(treeNode);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder){
        List<Integer> preList =  Arrays.stream(preorder).boxed().collect(Collectors.toList());;
        List<Integer> inList = Arrays.stream(inorder).boxed().collect(Collectors.toList());
        TreeNode treeNode = buildTreePreAndIn(preList, inList);
        return treeNode;
    }

    /**
     * 根据前序和中序构造二叉树
     *
     * @param preOrder
     * @param inOrder
     * @return
     */
    public static TreeNode buildTreePreAndIn(List<Integer> preOrder, List<Integer> inOrder) {
        if (preOrder == null || preOrder.size() == 0 || inOrder == null || inOrder.size() == 0) {
            return null;
        }
        Integer rootVal = preOrder.get(0);
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = inOrder.indexOf(rootVal);
        List<Integer> inLeftList = inOrder.subList(0, rootIndex); // 中序的左节点
        List<Integer> preLeftList = preOrder.subList(1, inLeftList.size() + 1); // 先序的左节点

        List<Integer> inRightList = inOrder.subList(rootIndex + 1, inOrder.size()); // 中序的右节点
        List<Integer> preRightList = preOrder.subList(inLeftList.size() + 1, preOrder.size()); // 先序的右节点

        root.left = buildTreePreAndIn(preLeftList, inLeftList);
        root.right = buildTreePreAndIn(preRightList, inRightList);
        return root;
    }

    /**
     * 根据中序和后序构建二叉树
     *
     * @param inOrder
     * @param postOrder
     * @return
     */
    public static TreeNode buildTreeInAndPost(List<Integer> inOrder, List<Integer> postOrder) {
        if (inOrder == null || inOrder.isEmpty() || postOrder == null || postOrder.isEmpty()) {
            return null;
        }
        Integer rootVal = postOrder.get(postOrder.size() - 1);
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = inOrder.indexOf(rootVal);
        List<Integer> inLeftList = inOrder.subList(0, rootIndex); // 中序的左节点
        List<Integer> inRightList = inOrder.subList(rootIndex + 1, inOrder.size()); // 中序的右节点

        List<Integer> postLeftList = postOrder.subList(0, inLeftList.size());
        List<Integer> postRightList = postOrder.subList(inLeftList.size(), postOrder.size() - 1);

        root.left = buildTreeInAndPost(inLeftList, postLeftList);
        root.right = buildTreeInAndPost(inRightList, postRightList);
        return root;
    }
}
