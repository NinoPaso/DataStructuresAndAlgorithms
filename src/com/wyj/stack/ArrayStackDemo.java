package com.wyj.stack;

/**
 * @ClassName ArrayStackDemo
 * @Description TODO: 使用数组实现栈的数据结构
 * @Author 86133
 * @Date 2020/9/18 21:53
 * @Version 1.0
 **/
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack as = new ArrayStack(5);
        as.push(1);
        as.push(3);
        as.push(5);
        as.push(2);
        as.list();
        as.pop();
        as.list();
    }

}

/**
 * 栈数据结构
 */
class ArrayStack {

    // 定义数组存放
    private int[] array;
    // 栈的大小
    private int maxSize;
    // 定义栈顶元素 给定初始值 -1
    private int stackTop = -1;

    // 构造
    public ArrayStack(int size) {
        this.maxSize = size;
        this.array = new int[maxSize];
    }

    // 判断栈空
    public boolean isEmpty() {
        return stackTop == -1;
    }

    // 判断栈满
    public boolean isFull() {
        return stackTop == maxSize - 1;
    }

    // 入栈  判断栈满 栈顶上移 加入数组
    public void push(int value) {
        if (this.isFull()) {
            System.out.println("栈已满！");
            return;
        }
        stackTop++;
        this.array[stackTop] = value;
    }

    // 出栈 判断栈空 弹出元素 栈顶下移
    public int pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        int value = array[stackTop];
        stackTop--;
        return value;
    }

    // 遍历栈 从栈顶 开始循环获取 数组的值
    public void list() {
        if (this.isEmpty()) {
            System.out.println("栈为空！");
            return;
        }
        for (int i = stackTop; i > -1; i--) {
            System.out.printf("stack[%d]=%d\n", i, array[i]);
        }
    }
}
