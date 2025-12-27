package kexin.letcode.链表;

/**
 * 328.奇偶链表
 */
public class OddEvenList {
    public static void main(String[] args) {
        ListNode head = ListNode.buildListNode(new int[]{1, 2, 3, 4, 5});
        ListNode listNode = oddEvenList(head);
        System.out.println(listNode);
    }

    public static ListNode oddEvenList(ListNode head) {
        // [1, 2, 3, 4, 5]
        // [1, 3, 4, 2, 4]
        if(head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
