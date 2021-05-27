package com.jvm;

/**
 * @ClassName EscapeTest
 * @Description TODO: 对象的逃逸分析测试
 * -Xmx1G -Xms1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails  非逃逸对象不经过GC栈上分配，速度大大快于堆
 * @Author 86133
 * @Date 2020/10/31 14:47
 * @Version 1.0
 **/
public class EscapeTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // 起始时间
        for (int i = 0; i < 100000; i++) {
            allocate();
        }
        long endTime = System.currentTimeMillis(); // 结束时间
        System.out.println(endTime - startTime + "ms");

        try {
            Thread.sleep(1000000); // 查看JVisualVM
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void allocate() {
        // 对象在方法内部 没有被引用 即没有发生逃逸
        User user = new User();
    }

    static class User {

    }
}
