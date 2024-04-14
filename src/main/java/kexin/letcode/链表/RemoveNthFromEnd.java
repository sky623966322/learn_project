package kexin.letcode.链表;

public class RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode listNode = ListNode.buildListNode(new int[]{1, 2, 3, 4, 5});
        ListNode removed = removeNthFromEnd(listNode, 2);
        System.out.println(removed);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        int length = 1;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        return removeNode(head, length - n);
    }

    public static ListNode removeNode(ListNode head, int n) {
        if (head.next == null) return null;
        if (n == 1) return head.next;
        int count = 1;
        ListNode cur = head;
        while (cur.next != null) {
            if (n - 1 <= count) { // 如果要移除的元素为当前节点的next
                cur.next = cur.next.next;
                break;
            } else {
                cur = cur.next;
            }
            count++;
        }
        return head;
    }

}
