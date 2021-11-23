package com.prsuit.androidlearnsample.algorithm;

/**
 * @Description:
 * @Author: sh
 * @Date: 2021/11/23
 */
public class Sort {
    public static void main(String[] args) {

        int[] arr ={5,3,1,4,2};
        testBubbleSort(arr);
        for (int i: arr) {
            System.out.println(i);
        }
    }

    private static void testBubbleSort(int[] arr) {
        for (int e = arr.length - 1; e > 0; e--) {//每次需要排序的长度
            for (int i = 0; i < e; i++) {// 从第一个元素到第i个元素
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }
}
