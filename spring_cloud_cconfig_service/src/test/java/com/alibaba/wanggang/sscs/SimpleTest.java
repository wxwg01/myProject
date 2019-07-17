package com.alibaba.wanggang.sscs;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wb-wg471966@alibaba-inc.com
 * @date : 2019/7/9
 */
public class SimpleTest {

    public static void main(String[] args) {
        //String a = "1200";
        //String b = "1200";
        //
        //System.out.println(a==b);


        //int[] nums = {17,2,6,4,8,11,56,34,85,34,6,1,77,88,5,3,9,10,41,62,85};
        int[] nums = {2,4,1,3};
        System.out.println(JSONObject.toJSONString(nums));
        quickSort(nums,0 , nums.length-1);
        System.out.println(JSONObject.toJSONString(nums));
    }


    public static void quickSort(int[] nums,int low,int high){
        if(nums==null || low<0 || high < 0){
            return ;
        }
        if(low>high){
            return;
        }
        int i=low,j=high;
        int temp = nums[low];
        while (i<j){
            // 先处理 右侧-》左侧  比起始小
            // 当ij处于相邻时（或者大小规则相邻），因为j还没有修改，所以可以-1 减完之后 ij相等
            while (temp<=nums[j] && i<j){
                j--;
            }
            // 再处理 左侧-》右侧 比起始大
            // 当ij处于相邻时，因为j已经-1，i一定会》=j
            while (temp>=nums[i] && i<j){
                i++;
            }
            if(i<j){
                int x = nums[j];
                int y = nums[i];
                nums[i] = x;
                nums[j] = y;
                System.out.println(JSONObject.toJSONString(nums)+" "+i+" "+j);
            }
        }
        nums[low] = nums[i];
        nums[i] = temp;
        System.out.println(JSONObject.toJSONString(nums)+" "+nums[i]+" "+i+" "+j);
        quickSort(nums,low,j-1);
        quickSort(nums,j+1 , high);
    }
}
