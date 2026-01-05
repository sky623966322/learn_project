package kexin.letcode.链表;

import java.util.List;

/**
 * 61.旋转链表
 */
public class RotateRight {

    public static void main(String[] args) {
        ListNode head = ListNode.buildListNode(new int[] {1, 2, 3, 4, 5});
        ListNode listNode = rotateRight2(head, 2);
        System.out.println(listNode);
    }
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) {
            return head;
        }
        // 计算head链表的长度
        int length = 0;
        ListNode element = head;
        while (element != null) {
            length++;
            element = element.next;
        }
        ListNode cur = head;
        // 移动k次（大于k次的取模，链表结果一样）
        for (int i = 0; i < k % length; i++) {
            // 每次移动，把head指向上一次移动的链表
            head = cur;
            // 定义尾节点的前一个节点
            ListNode beforeCur = null;
            // 移动cur指针，指向尾节点
            while (cur.next != null) {
                // 记录尾节点的前一个节点
                beforeCur = cur;
                // 尾节点
                cur = cur.next;
            }
            // 将尾节点的前一个节点与尾节点断开
            beforeCur.next = null;
            // 尾节点指向头节点
            cur.next = head;
        }
        return cur;
    }

    public static ListNode rotateRight2(ListNode head, int k) {
        if(head == null || head.next == null) {
            return head;
        }
        // 计算head链表的长度
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }
        // 尾节点指向头节点形成环形链表
        tail.next = head;
        // 定义临时指针
        ListNode temp = tail;
        // k % length是指要移动几次，length - k % length是指要断开的位置
        // 将temp迭代到要断开的位置
        for (int i = 0; i < length - k % length; i++) {
            temp = temp.next;
        }
        // 需要断开的位置下一个节点，即为头节点
        head = temp.next;
        // 断开环形链表
        temp.next = null;
        return head;
    }
}
