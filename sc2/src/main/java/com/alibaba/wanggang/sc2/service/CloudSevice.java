package com.alibaba.wanggang.sc2.service;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/4/26
 */
public class CloudSevice {

    public static void main(String[] args) throws InterruptedException {
        /*BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();
        System.out.println(blockingQueue.offer(1)+" ");
        System.out.println(blockingQueue.offer(2)+" ");
        System.out.println(blockingQueue.offer(3)+" ");
        System.out.println(blockingQueue.take()+" ");
        System.out.println(blockingQueue.size()+" ");*/

        //int[] arr = {3, 2, 5, 8, 4, 7, 6, 9};
        //int[] arr = {3, 2, 4};
        //ListNode head = new ListNode(arr);
        //head = removeNthFromEnd(head, 2);
        //System.out.println(head.toString());

        //System.out.println(JSONObject.toJSONString(Solution.twoSum(arr, 6)));

        //int[] a = {1,8};
        //int[] b = {0};


        //int[] a = {2,4,3};
        //int[] b = {5,6,4};
        //System.out.println(Solution.addTwoNumbers(new ListNode(a), new ListNode(b)).toString());

        Solution solution = new Solution();
        //System.out.println(solution.lengthOfLongestSubstring("abcbacbb"));

        System.out.println(solution.longestPalindrome("abcddcbaaaaaaaa"));

        List<String> strings = Arrays.asList("6", "1", "3", "1","2");

        Collections.sort(strings);//sort方法在这里
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode a = head;
        ListNode b = head;

        for (int i = 0; i < n; i++) {
            a = a.next;
        }

        if (a.next == null) {
            head = head.next;
            return head;
        }

        // 循环后 a 为最后一个 b为倒数第n-1个
        while (a.next != null) {
            a = a.next;
            b = b.next;
        }

        b.next = b.next.next;

        return head;

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can to be empty");
        }
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}

class Solution {
    public static int[] twoSum(int[] nums, int target) {
       /* for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length ; j++) {
               if(nums[i]+nums[j]==target){
                   return new int[]{i,j};
               }
            }
        }
        return null;*/

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int k = 0; k < nums.length; k++) {
            if (map.get(target - nums[k]) != null && map.get(target-nums[k])!=k) {
                return new int[]{k,map.get(target-nums[k])};
            }
        }
        return null;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode node = new ListNode(0);
        ListNode node1 = node;
        int r = 0;
        while( l1!=null || l2!=null || r>0){
            int a = l1==null?0:l1.val;
            int b = l2==null?0:l2.val;
            int n = a+b+r;

            r = n/10;
            n = n%10;
            node.next =new ListNode(n);
            node = node.next;

            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }
        }
        //node1=node1.next;
        return node1.next;
    }

    public int lengthOfLongestSubstring(String s) {
       /* int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;*/
        int longest = 0;
        int pre = -1;
        for (int i = 0; i < s.length(); i++) {
            // 判断i之前到重复点之间的字符是否重复
            // 如果重复就标记重复字符为重复点，并且从重复点重新计算长度，长度为i-重复点
            for(int j = pre + 1; j < i; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    pre = j;
                    break;
                }
            }
            longest = Math.max(i - pre, longest);
        }
        return longest;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0;
    }

    //给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    //输入: "babad"
    //输出: "bab"
    //注意: "aba" 也是一个有效答案。

    public String longestPalindrome(String s) {
        if(s==null || s.length()==0){
            return null;
        }
        // 从头开始，先拿一个字符，获取最后的位置，再递减 判断-1字符是不是相等 ？？


        // 从头开始，认为索引位置为对称中心点，往外扩比较一致性
        int st = 0;
        int en = 0;

        for (int i = 0; i < s.length(); i++) {
            // i为对称轴
            int n1 = checkSymmetric(s, i, i);
            // 长度偶数，i为中心对称侧
            int n2 = checkSymmetric(s, i, i+1);
            int n = Math.max(n1, n2);

            if(n> en-st){
                st = i - (n-1) /2;
                en = i+ n/2;
            }

        }
        return s.substring(st,en+1);
    }
    // 往外扩散
    private int checkSymmetric(String s, int i, int j){
        while (i>=0 && j<s.length() && s.charAt(i) == s.charAt(j)){
            // 当i=0的时候，是允许i-1变成-1的
            i--;
            j++;
        }
        // ij坐标都是在符合条件的范围外+1，需要扣除
        return j-i-1;
    }


    public String convert(String s, int numRows) {
        // 判断一轮有多少
        int per = numRows * 2 -2;

        int a = s.length() % per;
        int page = a  == 0 ? s.length()/per : s.length()/per +1;


        for (int i = per; i > a; i--) {
            s +=" ";
        }

        //String b = "";
        //for (int i = 0; i < page; i++) {
        //    for (int j = 0; j < per; j++) {
        //        int index = per*i+j;
        //
        //        for (int k = 0; k < ; k++) {
        //
        //        }
        //
        //
        //    }
        //
        //}
        return null;
    }

}
