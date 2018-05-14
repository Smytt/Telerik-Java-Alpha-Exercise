package sorted_lists;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(5);

        ListNode result = new Solution().reverseList(listNode);

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
        public ListNode reverseList(ListNode head) {

            ListNode result = null;

            while (head != null) {
                ListNode newNode = new ListNode(head.val);
                newNode.next = result;
                result = newNode;
                head = head.next;
            }

            return result;
        }
    }
}

