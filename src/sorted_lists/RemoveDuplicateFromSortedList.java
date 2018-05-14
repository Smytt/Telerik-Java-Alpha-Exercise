package sorted_lists;

public class RemoveDuplicateFromSortedList {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(3);

        ListNode result = new Solution().deleteDuplicates(null);

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
        public ListNode deleteDuplicates(ListNode head) {

            if(head == null) {
                return  null;
            }

            ListNode start = new ListNode(-1);
            ListNode node = start;

            while (head.next != null) {
                if (head.val != head.next.val) {
                    node.next = new ListNode(head.val);
                    node = node.next;
                }
                head = head.next;
            }

            node.next = new ListNode(head.val);

            return start.next;
        }
    }
}
