package kexin.letcode.链表;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 定义哑结点
        ListNode dummy = new ListNode(-1);
        // 当前节点是哑结点，后续往后移动
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                // list1当前的节点小于 list2，则将list1转到cur节点之后
                cur.next = list1;
                // 将list1的指针后移
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            // 判断玩list1和list2后，当前节点往后移动
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
