package com.prsuit.androidlearnsample.algorithm;

/**
 * @Description: 堆排序 堆的结构是树
 * @Author: sh
 * @Date: 2021/12/12
 */
class HeapSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1, 4, 2};
        heapSort(arr);
        printArr(arr);
    }

    /**
     *  堆(英语:heap)是计算机科学中一类特殊的数据结构的统称。
     *  将根节点最大的堆叫做最大堆或大根堆，根节点最小的堆叫做最小堆或小根堆。
     *
     *堆通常是一个可以被看做一棵树的数组对象。堆总是满足下列性质:
     * 1. 堆中某个节点的值总是不大于或不小于其父节点的值;
     * 2. 堆总是一棵完全二叉树。
     *
     *
     * 优先队列(priority queue) 优先队列是一种抽象数据类型，
     */


    public static void heapSort(int[] arr){
        if (arr == null || arr.length <2)
            return;
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);//交换最后一个和第一个值，heapSize-1，每次让最后一个值进行heapify
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    //某个数处在index位置，往上继续移动
    public static void heapInsert(int[] arr, int index) {
        //当元素比父位置的大则交换
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //某个数处在index位置，能否往下移动  将堆变成大根堆
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 +1;//left左孩子的下标, left+1右孩子下标
        while (left < heapSize) { //下方还有孩子的时候
            //两个孩子中，谁的值大，把下标给largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1 : left;
            //父节点和较大孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                //说明父节点已经是最大的
                break;
            }
            //否则让父节点与较大孩子节点交换
            swap(arr, largest, index);
            index = largest;
            //继续往下判断
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
