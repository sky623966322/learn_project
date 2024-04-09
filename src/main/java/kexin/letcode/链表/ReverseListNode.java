package kexin.letcode.链表;

public class ReverseListNode {

    public static void main(String[] args) {
        ListNode listNode = ListNode.buildListNode(new int[]{1, 2, 3, 4, 5});
        ListNode reversed1 = reverse(listNode);
        // ListNode reversed = reverseBetween(listNode, 2, 4);
        System.out.println(reversed1);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pre = new ListNode(-1);
        ListNode mid = null;
        ListNode tail = new ListNode(-2);
        int count = 1;
        while (head != null) {
            if (count < left) {

            } else if (count >= left && count <= right) {

            } else {

            }
            count++;
        }
        return tail;
    }

    public static ListNode reverse(ListNode listNode) {
        ListNode pre = null;
        while (listNode != null) {
            ListNode temp = listNode.next;
            listNode.next = pre;
            pre = listNode;
            listNode = temp;
        }
        return pre;
    }
}
