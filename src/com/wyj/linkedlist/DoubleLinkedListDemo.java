package com.wyj.linkedlist;

/**
 * @ClassName DoubleLinkedListDemo
 * @Description TODO: 双线链表的实现
 * @Author 86133
 * @Date 2020/9/6 22:56
 * @Version 1.0
 **/
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNodeDouble h1 = new HeroNodeDouble(1, "宋江", "及时雨");
        HeroNodeDouble h2 = new HeroNodeDouble(2, "卢俊义", "玉麒麟");
        HeroNodeDouble h3 = new HeroNodeDouble(3, "吴用", "智多星");
        HeroNodeDouble h4 = new HeroNodeDouble(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(h1);
        doubleLinkedList.add(h2);
        doubleLinkedList.add(h3);
        doubleLinkedList.add(h4);
        doubleLinkedList.list();
        System.out.println("update-----------------------------");
        doubleLinkedList.update(new HeroNodeDouble(4, "悟空", "齐天大圣"));
        doubleLinkedList.list();
        System.out.println("delete-----------------------------");
        doubleLinkedList.delete(new HeroNodeDouble(3, "吴用", "智多星"));
        doubleLinkedList.list();
        doubleLinkedList.delete(new HeroNodeDouble(3, "吴用", "智多星"));
        doubleLinkedList.list();
    }
}

// 创建双线链表的实现类
class DoubleLinkedList {
    // 定义头节点
    private HeroNodeDouble head = new HeroNodeDouble(0,"","");

    // 获取头节点
    public HeroNodeDouble getHead() {
        return head;
    }

    // 遍历双向链表
    public void list() {
        if (null == head.next) {
            System.out.println("链表为空");
            return;
        }
        // 定义temp
        HeroNodeDouble temp = head.next;
        while (null != temp) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 添加节点 遍历找到最后一个节点
    public void add(HeroNodeDouble node) {
        HeroNodeDouble temp = head;
        while (null != temp.next) {
            temp = temp.next;
        }
        // 退出循环 即最后一个节点
        temp.next = node;
        node.pre = temp;
    }

    // 修改双向链表的节点
    public void update(HeroNodeDouble heroNodeDouble) {
        // 判断链表是否为空
        if (null == head.next) {
            System.out.println("链表为空");
            return;
        }
        // 根据Node中的no遍历找到需要修改的节点
        HeroNodeDouble temp = head.next;
        // 定义标识是否找到节点
        boolean flag = false;
        while (null != temp) {
            if (temp.no == heroNodeDouble.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNodeDouble.name;
            temp.nickname = heroNodeDouble.nickname;
        } else {
            System.out.println("没有找到匹配节点！不能修改");
        }
    }

    // 双向链表删除节点
    public void delete(HeroNodeDouble heroNodeDouble) {
        // 首先判断受否为空
        if (null == head.next) {
            System.out.println("链表为空");
            return;
        }
        // 遍历找到需要删除的节点
        HeroNodeDouble temp = head.next;
        boolean flag = false;
        while (null != temp) {
            if (temp.no == heroNodeDouble.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            // 切断pre指向的时候需要考虑删除的是否是最后一个节点：temp.next = null null.pre就空指针异常
            if (null != temp.next) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("删除的节点未找到！");
        }
    }



}

// 创建双线链表的数据结构
class HeroNodeDouble {
    public int no;
    public String name;
    public String nickname;
    public HeroNodeDouble next;
    public HeroNodeDouble pre;

    //构造器
    public HeroNodeDouble(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
