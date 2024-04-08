package kexin.letcode.链表;

public class ReverseListNode {

    public static void main(String[] args) {
        ListNode listNode = ListNode.buildListNode(new int[]{1, 2, 3, 4, 5});
        ListNode reversed = reverseBetween(listNode, 2, 4);
        System.out.println(reversed);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode cur = head;
        ListNode tail = null;
        int count = 1;
        while (cur != null) {
            if (count >= left && count <= right) {
                cur.next = tail;
                tail = cur;
            }
            cur = cur.next;
            count++;
        }
        return tail;
    }
}
