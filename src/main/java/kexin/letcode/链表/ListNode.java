package kexin.letcode.链表;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        ListNode cur = next;
        while (cur != null ) {
            sb.append(",").append(cur.val);
            cur = cur.next;
        }
        return sb.toString();
    }

    public static ListNode buildListNode(int[] intArr) {
        ListNode node = new ListNode(-1);
        ListNode cur = node;
        for (int val : intArr) {
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        return node.next;
    }

    public static void main(String[] args) {
        int[] intArr = {1, 2, 3};
        ListNode listNode = buildListNode(intArr);
        System.out.println(listNode);
    }
}