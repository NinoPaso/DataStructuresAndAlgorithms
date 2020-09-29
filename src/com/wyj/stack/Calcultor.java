package com.wyj.stack;

/**
 * @ClassName Calcultor
 * @Description TODO： 1. 第一版实现简单的单位数计算功能 2. 通过数字栈的处理实现多位数的计算
 * @Author 86133
 * @Date 2020/9/20 20:18
 * @Version 1.0
 **/
public class Calcultor {
    public static void main(String[] args) {
        // 定义运算表达式
        String expression = "1*30+4-1";
        // 计算逻辑
        // 创建两个栈 一个符号栈 一个数字栈
        CalStack numStack = new CalStack(10);
        CalStack operStack = new CalStack(10);
        // 定义需要的变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";// 定义最终入数字栈的数字字符串
        while (true) {
            // 取得每个字符
            ch = expression.substring(index, index+1).charAt(0);
            // 判断ch进行相应的判断
            if (operStack.isOper(ch)) {
                if (!operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority(operStack.getStackTop())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 运算结果入栈
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch); // 如果优先级大于 则直接入栈
                    }
                } else {
                    // 如果为空 直接入栈
                    operStack.push(ch);
                }
            } else {
                // 数字入栈的时候的判断：继续判断后一位 是否为符号 不是的话 再结束拼接
//                numStack.push(ch - 48); // 根据ASCII码表中 数字字符对照 1->49 差值为48  第一版
                keepNum += ch;
                // 这里需要进行最后一位的判断 如果index已经指向表达式的最后一位 则直接push数字栈
                if (index == expression.length()-1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index+1, index+2).charAt(0))) {
                        // 如果下一位是运算符 直接入栈
                        numStack.push(Integer.parseInt(keepNum));
                        // 重置keepNum
                        keepNum = "";
                    }
                }
            }
            index++;
            // 当index值大于 表达式长度 退出循环
            if (index >= expression.length()) {
                break;
            }
        }
        // 当表达式遍历完毕 对两个栈进行运算
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            // 运算结果入栈
            numStack.push(res);
        }
        int res2 = numStack.pop();
        // 数字栈中的最后一个数 就是结果
        System.out.printf("表达式%s = %d", expression, res2);
    }
}

/**
 * 栈数据结构
 */
class CalStack {

    // 定义数组存放
    private int[] array;
    // 栈的大小
    private int maxSize;
    // 定义栈顶元素 给定初始值 -1
    private int stackTop = -1;

    // 构造
    public CalStack(int size) {
        this.maxSize = size;
        this.array = new int[maxSize];
    }

    // 返回栈顶元素的方法
    public int getStackTop() {
        return array[stackTop];
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

    /**
     * 以下添加相关计算器实现的方法
     */
    // 判断是否为符号
    public boolean isOper(char val) {
        return val == '*' || val == '/' || val == '+' || val == '-';
    }

    // 判断优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 实际计算方法的实现
    public int cal(int num1, int num2, int oper) {
        // 定义返回变量
        int res = 0;
        switch (oper) {
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            default:
                break;
        }
        return res;
    }
}
