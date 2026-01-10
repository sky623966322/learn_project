package kexin.letcode.链表;

/**
 * 707.设计链表
 */
public class MyLinkedList {
    private ListNode head;
    private int size;

    public MyLinkedList() {
        this.size = 0;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }

    public ListNode getHead(){
        return head;
    }

    public int get(int index) {
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            if (count == index) {
                return cur.val;
            }
            cur = cur.next;
            count++;
        }
        return -1;
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = head;
        head = node;
        size++;
    }

    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
        } else {
            ListNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
       size++;
    }

    public void addAtIndex(int index, int val) {
        // 如果index小于等于0，直接添加头节点
        if (index <= 0) {
            this.addAtHead(val);
            return;
        }
        // 如果index等于链表长度，直接添加到末尾
        if (index == size) {
            this.addAtTail(val);
            return;
        }
        if (index > size) {
            return;
        }
        ListNode dump = new ListNode(-1);
        dump.next = head;
        ListNode pre = dump;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        ListNode node = new ListNode(val);
        node.next = pre.next;
        pre.next = node;
        size++;

       /*
       int count = 0;
       ListNode cur = head;

        while (cur != null) {
            if (count == index) {
                pre.next = node;
                node.next = cur;
                break;
            }
            pre = cur;
            cur = cur.next;
            count++;
        }
        size++;
        head = dump.next;*/
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        ListNode dump = new ListNode(-1);
        dump.next = head;
        ListNode pre = dump;

        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        size--;

        /*
           int count = 0;
        ListNode cur = head;
        while (cur != null) {
            if (count == index) {
                preNode.next = cur.next;
                break;
            }
            preNode = cur;
            cur = cur.next;
            count++;
        }
        size--;
        head = dump.next;*/
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test3(){
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3, 0);
        myLinkedList.deleteAtIndex(2);
        myLinkedList.addAtHead(6);
        myLinkedList.addAtTail(4);
        System.out.println(myLinkedList.get(4));
        myLinkedList.addAtHead(4);
        myLinkedList.addAtIndex(5, 0);
        myLinkedList.addAtHead(6);
        System.out.println(myLinkedList.getHead());
    }

    private static void test2(){
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtIndex(0, 10);
        myLinkedList.addAtIndex(0, 20);
        myLinkedList.addAtIndex(1, 30);
        System.out.println(myLinkedList.get(0));
    }

    private static void test1(){
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(2);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtTail(4);
        myLinkedList.addAtTail(5);
        System.out.println(myLinkedList.get(2));
        myLinkedList.addAtHead(0);
        System.out.println(myLinkedList.getHead());
        myLinkedList.addAtTail(6);
        System.out.println(myLinkedList.getHead());

        myLinkedList.addAtIndex(3, 100);
        System.out.println(myLinkedList.getHead());

        myLinkedList.deleteAtIndex(3);
        System.out.println(myLinkedList.getHead());
    }
}
