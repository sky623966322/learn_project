package kexin.letcode.链表;

public class ReverseListNode {

    public static void main(String[] args) {
        ListNode listNode = ListNode.buildListNode(new int[]{1, 2, 3, 4, 5});
        ListNode reversed1 = reverse2(listNode);
        // ListNode reversed = reverseBetween(listNode, 2, 4);
        System.out.println(reversed1);
    }


    /**
     * 将head连表划分为3部分：
     * l1 = (0, left)
     * l2 = [left, rigth]
     * l3 = (rigth, --)
     * <p>
     * 然后将 l2 尾节点指向 l3, l1尾节点指向l2,
     * 结果返回l1
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        int count = 1;
        ListNode l1 = new ListNode(-1);
        ListNode l1TailNode = l1; // 定义l1的尾节点
        ListNode l2 = null;

        while (head != null) {
            if (count < left) {
                l1TailNode.next = head; // 先将初始化的l1尾指针指向head
                head = head.next; // head的指针移动到下一个节点
                l1TailNode = l1TailNode.next; // l1的尾指针也往后移，后续拼接l2需要使用
            } else if (count >= left && count <= right) {
                // 反转 [left, rigth] 之间的连表
                ListNode temp = head.next;
                head.next = l2;
                l2 = head;
                head = temp;
            } else {
                // rigth之后的连表已经被head指针引用， 后续直接将l2最后一个节点指针指向head即可
                break;
            }
            count++;
        }
        ListNode l2Cur = l2;
        while (l2Cur.next != null) { // 只需要遍历到到最后一个元素，null元素不需要遍历，所以不用 while(l2Cur != null)
            l2Cur = l2Cur.next;
        }
        l2Cur.next = head; // rigth之后的部分拼接到l2尾部
        l1TailNode.next = l2; // l2拼接到l1尾部
        return l1.next;
    }

    public static ListNode reverse(ListNode listNode) {
       ListNode pre = null;
       ListNode cur = listNode;
       while(cur != null) {
           ListNode temp = cur.next;
           cur.next = pre;
           pre = cur;
           cur = temp;
       }
       return pre;
    }

    public static ListNode reverse2(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode node = reverse2(head.next);
        if (node != null) {
            return node.next;
        } else {
            return node;
        }
    }
}
