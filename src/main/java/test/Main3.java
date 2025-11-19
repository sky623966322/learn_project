package test;

public class Main3 {

    /*public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 3, 0, 7};
        // 2,5,3,0,7
        // 2,3,5,0,7
        // 2,3,0,5,7
        // 2,0,3,5,7
        // 0,2,3,5,7
        StringBuilder sb = new StringBuilder();
        int[] sortArr = sort(arr);
        for (int i : sortArr) {
            sb.append(i).append(",");
        }
        System.out.println(sb);
    }*/


   /* public static int[] sort(int[] arr) {
        for(int j = arr.length; j > 0; j--) {
            for (int i = 0; i < j - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                } else {
                    continue;
                }
            }
        }
        return arr;
    }*/

    public static void main(String[] args) {
        Node node10 = new Node(10, null);
        Node node8 = new Node(8, node10);
        Node node6 = new Node(6, node8);
        Node node4 = new Node(4, node6);
        Node node2 = new Node(2, node4);

        Node node9 = new Node(9, null);
        Node node7 = new Node(7, node9);
        Node node5 = new Node(5, node7);
        Node node3 = new Node(3, node5);
        Node node1 = new Node(1, node3);

        combine(node1, node2);
    }

    /**
     * 给定两个有序的链表，合并成一个有序的链表
     *
     * [1, 3, 5, 7, 9]
     * [2, 4, 6, 8, 10]
     *
     * [1,3,5,7,9]
     * [2,4,6,8,10]
     */
    public static Node combine(Node node1, Node node2) {
        Node node = new Node(-1, null);
        if (node1.val <= node2.val) {
            node.next = node2;

        }
        while (node1.hasNext() || node2.hasNext()) {

        }
        return node;
    }


    static class Node{
        private int val;
        private Node next;

        public Node(int valParam, Node nextParam) {
            val = valParam;
            next = nextParam;
        }

        public boolean hasNext() {
            if (next != null) {
                return true;
            }
            return false;
        }
    }

}
