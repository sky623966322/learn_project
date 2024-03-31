package kexin.letcode.链表;


import java.math.BigInteger;

public class AddTwoNumbers {

    /**
     * l1 = [2,4,3], l2 = [5,6,4]
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();

        while (l1 != null) {
            sb1.append(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            sb2.append(l2.val);
            l2 = l2.next;
        }
        BigInteger num1 = new BigInteger(sb1.reverse().toString());
        BigInteger num2 = new BigInteger(sb2.reverse().toString());

        String ret = new StringBuffer(num1.add(num2).toString()).reverse().toString();
        String[] retArr = ret.split("");

        ListNode listNode = new ListNode(-1);
        ListNode nodePoint = listNode;
        for (String item : retArr) {
            nodePoint.next = new ListNode(Integer.parseInt(item));
            nodePoint = nodePoint.next;
        }
        return listNode.next;
    }

}
