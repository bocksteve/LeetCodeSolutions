/* Leetcode.com Problem #23 (https://leetcode.com/problems/merge-k-sorted-lists/description/)
 * 
 * Description:
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */

//Strategy:  Create PrioirityQueue and override its native compare function so that it can prioritize based on value of each ListNode
//Time complexity is O(n log k)

/**
 * Definition for singly-linked list.  (Defined in separate class)
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode dummy2 = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode l1,ListNode l2){
                return l1.val - l2.val;
            }
        });
        
        for (int i = 0; i < lists.length; i++){
            if (lists[i] != null) queue.add(lists[i]);
        }
        
        while (!queue.isEmpty())
        {
            dummy2.next = queue.poll();
            dummy2 = dummy2.next;
            
            if (dummy2.next != null)
            {
                queue.add(dummy2.next);
            }
        }
        return dummy.next;
        
        
    }
}