package com.jvm;

/**
 * @ClassName ClassLoaderTest
 * @Description TODO:测试类加载器
 * @Author 86133
 * @Date 2020/10/3 22:02
 * @Version 1.0
 **/
public class ClassLoaderTest {
    public static void main(String[] args) {
        // 获取系统的类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2
        // 通过系统类加载器获取上层扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader); // sun.misc.Launcher$ExtClassLoader@1b6d3586
        // 再通过扩展类加载器获取上层
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader); // null
        // 获取自定义类ClassLoaderTest的类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2
        // 获取String类的类加载器
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1); // null
        /**
         * 得出结论：Java的核心类库都是通过引导类加载器进行加载的，自定义类默认是通过系统类加载器进行加载
         * 而引导类加载器是通过c/c++实现
         */

    }
}
