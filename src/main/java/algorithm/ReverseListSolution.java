package algorithm;

import lombok.ToString;
import org.junit.Test;

/**
 * 链表反转
 */
public class ReverseListSolution {

    @Test
    public void testReverseList(){
        ListNode head = new ListNode(1);
        ListNode next2 = new ListNode(2);
        ListNode next3 = new ListNode(3);

        head.next = next2;
        next2.next = next3;

        ListNode listNode = ReverseList(head);
        System.out.println(listNode);
    }

    public ListNode ReverseList(ListNode head) {
        /**
         * 1.如果head或next为null则直接返回；
         */
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}

@ToString
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}