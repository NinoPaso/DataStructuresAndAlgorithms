package com.wyj.linkedlist;

/**
 * @ClassName JosephCircle
 * @Description TODO: 约瑟夫环单链表实现
 * @Author 86133
 * @Date 2020/9/10 23:29
 * @Version 1.0
 **/
public class JosephCircle {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addBoy(5);
        circleLinkedList.showList();
        circleLinkedList.popBoy(3, 2, 5);
    }
}

/**
 * 环形链表实现
 */
class CircleLinkedList {
    // 定义第一个节点
    private Boy first = null;

    // 添加节点方法实现 nums: 创建节点的数量
    public void addBoy(int nums) {
        // 判断nums 是否合法
        if (nums < 1) {
            System.out.println("nums参数不合法！");
            return;
        }
        // 辅助节点
        Boy cur = null;
        // 循环创建节点
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            // 第一个节点
            if (i == 1) {
                first = boy;
                first.next = first;
                cur = first;
            } else {
                cur.next = boy;
                boy.next = first;
                cur = boy;
            }
        }
    }

    // 遍历环形链表
    public void showList() {
        if (null == first) {
            System.out.println("链表为空");
            return;
        }
        // 辅助节点
        Boy cur = first;
        while (true) {
            System.out.println("节点编号：" + cur.no);
            if (first == cur.next) {
                break;
            }
            cur = cur.next;
        }
    }

     /**
      * @Description:
      * @Author: WangYangjun
      * @Date: 2020/9/12 23:09
      * @Param: startNo: 开始出圈编号 count：出圈的计数 nums：总共的节点数
      */
    public void popBoy(int startNo, int count, int nums) {
        // 先判断参数是否合法：
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数不合法！");
            return;
        }
        // 以下循环出圈
        Boy temp = first;
        // 先将temp指针指向first的前一个节点 即最后一个节点
        while (true) {
            if (temp.next == first) {
                break;
            }
            temp = temp.next;
        }
        // 再将temp 和 first移动到startNo
        for (int i = 0; i < startNo-1; i++) {
            first = first.next;
            temp = temp.next;
        }
        // 开始计数 然后出圈 知道圈内剩下最后一个节点（temp 和 first指向了同一个节点）
        while (true) {
            if (temp == first) {
                break;
            }
            // 循环报数进行出圈
            for (int j = 0; j < count-1; j++) {
                first = first.next;
                temp = temp.next;
            }
            System.out.printf("此时出圈的小孩编号%d\n", first.no);
            // 出圈逻辑
            first = first.next;
            temp.next = first;
        }
        System.out.println("最后圈内剩下的编号为"+first.no);
    }
}

/**
 * 单链表数据结构
 */
class Boy {

    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

}
