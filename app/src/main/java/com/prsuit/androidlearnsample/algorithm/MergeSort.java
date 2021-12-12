package com.prsuit.androidlearnsample.algorithm;

/**
 * @Description: 归并排序 时间复杂度O(N*logN) 空间O(N) 稳定(相同元数的相对顺序不变)
 * @Author: sh
 * @Date: 2021/11/24
 */
class MergeSort {
    /*对于归并排序而言，思想可以概括为:分而治之。也就是将一个数组，首先划分为一堆单个的数，
    然后再一个接一个的，进行两两有序合并，最后就得到了一个有序数组。步骤:
    1. 将待排序的数列分成若干个长度为1的子数列
    2. 然后将这些数列两两合并;得到若干个长度为2的有序数列
    3. 再将这些数列两两合并;得到若干个长度为4的有序数列
    4. 再将它们两两合并;直接合并成一个数列为止
    5. 这样就得到了我们想要的排序结果
    */

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 4, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
        printArr(arr);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (checkNull(arr)) return;
        // 当left == right时，不需要再划分
        if (left < right) {
//            int mid = (left + right) / 2;
            int mid = left + ((right - left) >> 1);
            // 左右往下拆分
            mergeSort(arr, temp, left, mid);//左半部分排好序
            mergeSort(arr, temp, mid + 1, right);//右半部分排好序
            // 拆分结束后返回结果进行合并
            mergeSortedArray(arr, temp, left, mid, right);
        }
    }

    //合并两个有序子序列
    private static void mergeSortedArray(int[] arr, int[] temp, int left, int mid, int right) {
//        int temp[ right - left + 1] 定义临时存放数组
        int i = left;
        int j = mid + 1;
        int k = 0;
        //比较合并左右数组，放入temp中
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }

        // 取完其中一组数组后，将非空的那组拼入，左边非空时
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        //右边非空时
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 把temp数据复制回原数组
        for (i = 0; i < k; i++) {
            arr[left + i] = temp[i];
        }
    }

    private static boolean checkNull(int[] arr) {
        if (null == arr || arr.length < 2) {
            return true;
        }
        return false;
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }


    class ListNode {
        ListNode next;

        //链表反转
        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                //temp记录下一个节点，head是当前节点
                ListNode temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }
            return prev;
        }
    }

}
