package com.wyj.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName Bubble
 * @Description TODO: 实现各个排序算法 并进行比较
 * @Author 86133
 * @Date 2020/9/23 11:25
 * @Version 1.0
 **/
public class Sort {
    public static void main(String[] args) {
        Integer[] integers = new Integer[10000];
        for (int i = 0; i < 10000; i++) {
            integers[i] = (int) (Math.random() * 10000);
        }
        List<Integer> list = Arrays.asList(integers);
        List<Integer> list1 = Arrays.asList(new Integer[]{2, 4, 1, 7, 3, 9});
//        // 时间测试
//        long startTime = System.currentTimeMillis();
////        quickSort(list, 0, list.size()-1);
//        int[] temp = new int[list.size()];
//        spilt(list, 0, list.size() - 1, temp);
//        long endTime = System.currentTimeMillis();
//        System.out.println("归并排序10000个数据排序消耗总时间是：" + (endTime - startTime) + "ms");
//        radixSort(list);
//        System.out.println(list);
        shellSort2(list);
//        insertSort(list);
//        selectSort(list);
//        bubbleSort(list);
//        list.stream().forEach(ele-> System.out.println(ele));
    }

    /**
     * Bubble实现
     * 1. 冒泡排序（从小到大）： 从第一个元素开始遍历整个数组，如果大于当前元素 交换位置
     * * 2. 再依次拿后续元素进行遍历
     * * 3. 即for循环的嵌套实现
     * * PS： 时间复杂度为N平方
     * * 优化： 如果经过前几次排序 已经成为有序的  则后面不需要再进行排序。 （冒泡排序的优化）
     */
    public static List<Integer> bubbleSort(List<Integer> list) {
        // 时间测试
        long startTime = System.currentTimeMillis();
        // 定义是否发生交换的标识
        boolean flag = false;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    Integer temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;// 每次循环完成后需要重置
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("冒泡排序10000个数据排序消耗总时间是：" + (endTime - startTime) + "ms");
        return list;
    }

    /**
     * 选择排序: 1. 循环查找到最小的元素 和第一个元素进行交换
     * 2. 从第二个元素开始 再次进行。。。以此类推
     */
    public static List<Integer> selectSort(List<Integer> list) {
        // 时间测试
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < list.size() - 1; j++) {
            // 定义最小值 假定为第一个元素
            int min = list.get(j);
            int index = j;
            for (int i = j + 1; i < list.size(); i++) {
                if (min > list.get(i)) {
                    min = list.get(i);
                    index = i;
                }
            }
            // 遍历完成进行交换
            list.set(index, list.get(j));
            list.set(j, min);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("选择排序10000个数据排序消耗总时间是：" + (endTime - startTime) + "ms");
        return list;
    }

    /**
     * 插入排序：把N个待排序的元素看成一个有序表，一个无序表，排序过程中 拿出无序元素 选择插入到有序表中。
     * PS: 存在的问题，如果较小的数在最后 那么需要将数据整体后移一遍  随着数据量的增大 效率影响较大
     */
    public static List<Integer> insertSort(List<Integer> list) {
        // 时间测试
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < list.size(); i++) {
            // 定义待插入变量
            int insertVal = list.get(i);
            // 定义有序表索引变量
            int index = i - 1;

            while (index >= 0 && insertVal < list.get(index)) {
                list.set(index + 1, list.get(index));// 后移
                index--;
            }
            // 如果退出循环 即找到要插入的位置
            list.set(index + 1, insertVal);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("插入排序10000个数据排序消耗总时间是：" + (endTime - startTime) + "ms");
        return list;
    }

    /**
     * 希尔排序：
     * 介绍：其本身也是一种插入排序，经过改良后 更加高效 也成为缩小增量排序 用于解决上述问题
     * 实现：1. 使用循环 /2 进行确定分组步长 进行元素交换 即交换法
     * 2. 使用循环 /2 进行确定分组的步长  同时将分组内元素进行插入排序 即移位法
     */
    public static List<Integer> shellSort(List<Integer> list) {
        // 时间测试
        long startTime = System.currentTimeMillis();
        for (int gap = list.size() / 2; gap > 0; gap /= 2) {
            // gap每轮的步长
            for (int i = gap; i < list.size(); i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (list.get(j) > list.get(j + gap)) {
                        int temp = list.get(j);
                        list.set(j, list.get(j + gap));
                        list.set(j + gap, temp);
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("希尔排序（交换法）10000个数据排序消耗总时间是：" + (endTime - startTime) + "ms");
        return list;
    }

    public static List<Integer> shellSort2(List<Integer> list) {
        // 时间测试
        long startTime = System.currentTimeMillis();
        for (int gap = list.size() / 2; gap > 0; gap /= 2) {
            // gap每轮的步长
            for (int i = gap; i < list.size(); i++) {
                int j = i;
                // 定义即将插入的元素变量
                int insertVal = list.get(j);
                // while 循环找到对应的位置
                while (j - gap >= 0 && insertVal < list.get(j - gap)) {
                    list.set(j, list.get(j - gap));
                    j -= gap;
                }
                // 退出循环说明找到位置
                list.set(j, insertVal);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("希尔排序（移位法）10000个数据排序消耗总时间是：" + (endTime - startTime) + "ms");
        return list;
    }

    /**
     * 快速排序：是对冒泡排序方法的一种改进。其基本的思想是，通过一趟排序，将要排序的部分分割成独立的两部分，保证 其中一部分的数据比另一部分的数据都要大
     * 再对这两部分数据进行相同的排序。 此程序递归的进行 完成后即排序完成
     */
    public static List<Integer> quickSort(List<Integer> list, int left, int right) {
        // 定义左右下标 以及中间变量
        int l = left;
        int r = right;
        int pivot = list.get((left + right) / 2);
        // while 循环的目的 让比pivot小的值放到左边
        while (l < r) {
            // 在中轴左边循环找 找到比中轴值大的 退出
            while (list.get(l) < pivot) {
                l += 1;
            }
            // 在中轴的右边循环找 找到比中轴小的值 退出
            while (list.get(r) > pivot) {
                r -= 1;
            }
            // 退出循环的条件 如果左下表移动到 和 右边的下标重合 说明 左边的值 已经全部小于右边的值 此次的循环退出
            if (l >= r) {
                break;
            }
            // 上两个while循环找到 能进行交换的值的时候 进行交换
            int temp = list.get(l);
            list.set(l, list.get(r));
            list.set(r, temp);

            // 存在当前交换完了的值可能等于 中轴值 防止出现中轴值 和 左右元素值相等的情况
            if (list.get(l) == pivot) {
                // 将下标又移位
                r -= 1;
            }
            if (list.get(r) == pivot) {
                l += 1;
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 以下进行递归
        if (left < r) {
            quickSort(list, left, r);
        }
        if (l < right) {
            quickSort(list, l, right);
        }
        return list;
    }

    /**
     * @Description: 归并排序：利用归并的思想实现的排序，算法采用经典的分治思想，即将问题分解成一系列小问题，递归求解
     * 分而治之
     * @Author: WangYangjun
     * @Date: 2020/9/27 22:08
     * @param: list->待排序的集合
     * @param: left->最左边的索引
     * @param: mid->中间的索引
     * @param: right->最右边的做因
     * @param: temp->中间数组
     */
    // 分的方法
    public static void spilt(List<Integer> list, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左递归分解
            spilt(list, left, mid, temp);
            // 向右递归进行分解
            spilt(list, mid + 1, right, temp);
            // 递归调用合并方法
            mergeSort(list, left, mid, right, temp);
        }
    }

    // 以下是合并的方法
    public static void mergeSort(List<Integer> list, int left, int mid, int right, int[] temp) {
        // 初始化参数
        int i = left; // 左边初始下标
        int j = mid + 1; // 右边的初始下标
        int t = 0; // 指向temp数组的下标
        // 1. 先将两边分开的数组 按照规则比较 依次放入到temp数组中
        while (i <= mid && j <= right) { // 说明还没有结束循环到达终止条件->即一边操作完毕
            if (list.get(i) <= list.get(j)) {
                temp[t] = list.get(i);
                i += 1;
            } else {
                temp[t] = list.get(j);
                j += 1;
            }
            t += 1;
        }
        // 2. 直到两边有一边操作完毕 结束
        // 3. 将剩余的放入temp 考虑左边 右边的剩余的情况
        while (i <= mid) {
            temp[t] = list.get(i);
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = list.get(j);
            t += 1;
            j += 1;
        }
        // 4. 将temp拷贝到原来的数组
        t = 0; // 将t置为0
        int tempLeft = left;
        while (tempLeft <= right) {
            list.set(tempLeft, temp[t]);
            t += 1;
            tempLeft += 1;
        }
    }

    /**
     * 基数排序（桶排序）：
     * 1. radixSort属于分配式排序，又称为桶子法，其核心思想是将各个数的每个位拿出来，单独进行排序。个位-最高位
     * 2. 位数不够使用0 进行替代  这样的话 使用十个数组（代表0-9） 最后一轮排完 取出后 就是有序
     * 3. 思考如果包含负数呢： 可以找到最小值  取其绝对值  将所有的数值进行+绝对值  全部转换为整数进行排序 ！
     * 4. 基数排序呢 是最稳定的排序，而实是效率最高的稳定排序
     * 5. 它是桶排序的扩展 经典的空间换取时间的算法 所以呢，它的风险点就是 会占用很大的内存空间 所以当数据量特别大的时候
     *  需要注意，内存溢出
     */
    public static List<Integer> radixSort(List<Integer> list) {
        // 时间测试
        long startTime = System.currentTimeMillis();
        // 循环找到集合中最大值 得到位数 定义 方法体的循环次数
        Integer max = list.stream().max((ele1, ele2) -> ele1 > ele2 ? 1 : -1).get();
        int counts = (max + "").length();

        // 定义一个二位数组 表示存放的 10个桶
        int[][] buckets = new int[10][list.size()]; // 最坏的情况就是 所有元素 同一位 数字相同 全放入一个桶
        // 定义一个一维数组存放 每个桶中对应元素数量数量 即下标
        int[] eleCounts = new int[10];

        for (int a = 0, n = 1; a < counts; a++, n *= 10) { // 定义n 的到每次除数 接着进行取余
            // 循环进行处理 拿出每位数 放到对应的桶
            for (int i = 0; i < list.size(); i++) {
                // 取出每位
                int digitOfEle = list.get(i) / n % 10;
                // 放入对应的桶中
                buckets[digitOfEle][eleCounts[digitOfEle]] = list.get(i);
                eleCounts[digitOfEle]++;//表示该个桶 的元素数量
            }
            // 按照桶中元素的顺序去除数字 放回集合
            int index = 0; // 新集合的索引
            for (int j = 0; j < eleCounts.length; j++) {
                if (eleCounts[j] != 0) { // 对应的一维数组个数不为0 才说明有值
                    for (int k = 0; k < eleCounts[j]; k++) {
                        list.set(index++, buckets[j][k]);
                    }
                }
                eleCounts[j] = 0;// 遍历完成需要将 eleCounts 数组置为空
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("基数排序10000个数据排序消耗总时间是：" + (endTime - startTime) + "ms");
        return list;
    }
}
