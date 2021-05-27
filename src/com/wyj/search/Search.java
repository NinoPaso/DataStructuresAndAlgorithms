package com.wyj.search;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName Search
 * @Description TODO: 查找算法的相关内容
 * Java中常用的查找方法：
 * 1. 线性查找
 * 2. 二分法
 * 3. 插值查找
 * 4. 斐波那契查找
 * @Author 86133
 * @Date 2020/9/29 22:05
 * @Version 1.0
 **/
public class Search {
    public static void main(String[] args) {
        seqSearch(Stream.of(1,3,5,2,1,4).collect(Collectors.toList()), 2);
    }

    /**
     * 线性查找 逐一比对
     */
    public static void seqSearch(List<Integer> list, int value) {
        list.forEach(temp -> {
            if (value == temp) {
                System.out.println(list.indexOf(temp));
            }
        });
    }

    /**
     * 二分查找算法： 前提是有序集合中进行  如果是无序的 首先要进行排序 再进行二分查找
     */

}
