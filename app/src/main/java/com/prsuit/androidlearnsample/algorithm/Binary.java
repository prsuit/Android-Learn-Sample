package com.prsuit.androidlearnsample.algorithm;

/**
 * @Description: 二分查找
 * @Author: sh
 * @Date: 2021/11/24
 */
public class Binary {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 6};
        int pos = binarySearch(arr, 2);
        System.out.println(pos);
        int pos1 = binarySearch1(arr, 0, arr.length - 1, 2);
        System.out.println(pos1);
        //寻找大于等于目标值最左侧位置
        System.out.println(binarySearchLeft(arr, 2));
    }

    //有序数组二分查找 非递归
    private static int binarySearch(int[] arr, int target) {
        if (null == arr || arr.length == 0) return -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else if (arr[mid] > target) right = mid - 1;
        }
        return -1;
    }

    //递归
    private static int binarySearch1(int[] arr, int left, int right, int target) {
        if (null == arr || arr.length == 0 || left > right) return -1;
        int mid = (left + right) / 2;
        if (arr[mid] < target) {
            return binarySearch1(arr, mid + 1, right, target);
        } else if (arr[mid] > target) {
            return binarySearch1(arr, left, mid - 1, target);
        } else {
            return mid;
        }
    }

    /**
     * 有序数组中寻找大于等于目标值最左侧位置
     */
    public static int binarySearchLeft(int[] arr, int num) {
        if (null == arr) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle] >= num) {
                index = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return index;
    }

    // This is Arrays.binarySearch(), but doesn't do any argument validation.
    static int binarySearch(int[] array, int size, int value) {
        if (null == array || size == 0) return -1;
        int lo = 0;
        int hi = size - 1;

        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;//>>>无符号右移，高位补0; <<左移低位补0
            final int midVal = array[mid];

            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        return ~lo;  // value not present
    }
}
