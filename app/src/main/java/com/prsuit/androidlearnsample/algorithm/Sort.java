package com.prsuit.androidlearnsample.algorithm;

/**
 * @Description: 冒泡/选择/插入排序
 * @Author: sh
 * @Date: 2021/11/23
 */
public class Sort {
    public static void main(String[] args) {

        int[] arr = {5, 3, 1, 4, 2};
        bubbleSort(arr);
//        selectionSort(arr);
//        insertSort(arr);
    }

    //冒泡 时间复杂O(n^2) 两数相抱转个圈
    // N个数字要排序完成，总共进行N-1趟排序，每i趟的排序次数为(N-i)次，
    private static void bubbleSort(int[] arr) {
        //判断边界条件
        if (checkNull(arr)) return;
        boolean swap;
        //进行N-1趟排序
        for (int i = 0; i < arr.length - 1; i++) {
            // 增加一个swap的标志，当前一轮没有进行交换时，说明数组已经有序 swap = false;
            swap = false;
            //第i趟比较次数为(N-i)次
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapXOR(arr, j, j + 1);
                    swap = true;
                }
            }
            if (!swap) break;
        }
        printArr(arr);
    }

    //选择排序 角标互换
    //只是记录着最小值的下标，接着用最小值的下标继续和后面的数进行比较
    private static void selectionSort(int[] arr) {
        if (checkNull(arr)) return;
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swapTemp(arr, i, minIndex);
            }
        }
        printArr(arr);
    }

    //插入排序 扑克牌往回插 与前面的值比较
    private static void insertSort(int[] arr) {
        if (checkNull(arr)) return;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        printArr(arr);
    }

    private static void swapTemp(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //不开辟额外空间交换两个独立内存的变量 异或 0^N=N N^N=0
    // 两者可以相等，但是不能是指向同一块内存
    private static void swapXOR(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
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
}
