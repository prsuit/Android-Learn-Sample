package com.prsuit.androidlearnsample.algorithm;

/**
 * @Description: 快速排序 时间复杂度O(N*logN) 空间O(logN) 不稳定(相同元数的相对顺序会改变)
 * @Author: sh
 * @Date: 2021/12/12
 */
class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1, 4, 2};
        quickSort(arr, 0, arr.length - 1);
        printArr(arr);
    }

    /**
     * 快速排序的思想，可以简单的概括为:两边包抄、一次一个。
     * 每选一个基准点，一次排序后确定它的最终位置，一步到位。
     * 1、先从数列中取出一个数作为基准数
     * 2、分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边
     * 3、再对左右区间重复第二步，直到各区间只有一个数
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int pivot = partition(arr, low, high);//确定基准数据位置，将数组分为两部分
        quickSort(arr, low, pivot - 1); //递归排序左子数组
        quickSort(arr, pivot + 1, high);//递归排序右子数组
    }

    //挖坑填数+分治法
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];//基准数，数组第一个数,或随机选一个数
        while (low < high) {
            // 注意 low 设为 pivot 后，必须先走 high,先走右边
            //队尾大于等于基准数时向前移到high，从后往前
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            //当队尾出现小于基准数则将其赋值给arr[low]
            arr[low] = arr[high];//交换比基准小的数到左端

            //队首小于等于时，向前移动low,从前往后
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            //当队首出现大于基准数则将其赋值给all[high]
            arr[high] = arr[low];//交换比基准大的数到右端
        }
        //扫描完成，基准到位
        //退出循环时low和high相等，此时low/high位置就是基准的排序位置
        arr[low] = pivot;
        return low;
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
