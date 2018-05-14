package sorted_lists;

public class RotateLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

        ListNode result = new Solution().rotateList(listNode, 4);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode rotateList(ListNode head, int k) {

            if (head == null) {
                return null;
            }

            int listSize = 0;
            ListNode counter = head;
            while (counter != null) {
                listSize++;
                counter = counter.next;
            }

            k %= listSize;

            ListNode secondPartStart = new ListNode(-1);
            ListNode secondPart = secondPartStart;

            while (k != listSize) {
                listSize--;
                secondPart.next = new ListNode(head.val);
                head = head.next;
                secondPart = secondPart.next;
            }

            secondPart.next = null;

            ListNode resultStart = new ListNode(-1);
            ListNode result = resultStart;

            while (k != 0) {
                k--;
                result.next = new ListNode(head.val);
                head = head.next;
                result = result.next;
            }

            result.next = secondPartStart.next;

            return resultStart.next;
        }
    }
}
