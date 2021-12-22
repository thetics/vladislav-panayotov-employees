public class NodeAddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 2;
        l1.val = 4;
        l1 = {1,2};
        ListNode l2 = new ListNode();
        l2.val = 5;
        l2.val = 6;
        l2.val = 4;
        System.out.println(addTwoNumbers(l1,l2));

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0);
        ListNode curr = temp;
        int carry = 0;

        System.out.println();
        while (l1 != null || l2 != null || carry == 1) {
            int sum = 0;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            sum += carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            curr.next = node;
            curr = curr.next;

        }

        return temp.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }
}
