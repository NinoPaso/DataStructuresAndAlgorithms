package com.wyj.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode h1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode h2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode h3 = new HeroNode(3, "吴用", "智多星");
        HeroNode h4 = new HeroNode(4, "林冲", "豹子头");
        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(h1);
        singleLinkedList.addByOrder(h4);
        singleLinkedList.addByOrder(h3);
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(h2);
        singleLinkedList2.list();
//        HeroNode newHeroNode = new HeroNode(4, "林冲er", "豹子头eeeeeee");
//        singleLinkedList.update(newHeroNode);
//        singleLinkedList.list();
//        singleLinkedList.del(3);
//        singleLinkedList.list();
//        System.out.println("----------------------------");
//        reverseList(singleLinkedList.head);
//        singleLinkedList.list();
        System.out.println("------------------------------");
//        reversePrint(singleLinkedList.head);
        SingleLinkedList list = mergeTwoList(singleLinkedList, singleLinkedList2);
        list.list();

    }

    /**
     * 反转节点
     * 自己的思路：创建一个新的链表，创建头节点。遍历原来的链表，将每个节点添加到新链表的头部
     * 实际写存在的问题：不能正确修改节点的指针  导致死循环或者链表断掉
     * 解决数据结构的算法问题：试着去画图 更好的理解和解决
     */
    public static void reverseList(HeroNode head) {
        // 如果第一个节点为空或者只有一个节点不需要反转
        if (null == head.next || null == head.next.next) {
            return;
        }
        // 定义辅助的指针 遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向cur的下一个节点  防止链表断掉
        HeroNode reverseHead = new HeroNode(0, "", "");// 反转链表的头节点
        // 遍历原来的链表，每取出来一个节点，将其放到reverse的最前端。
        while (cur != null) {
            next = cur.next;// 先保留下一个节点
            cur.next = reverseHead.next;
            reverseHead.next = cur; //连接
            cur = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 逆序打印链表内容：
     * 方案： 此类问题首先想到可以利用栈这个数据结构 先进后出  实现逆序打印 同时不改变链表本身的数据结构
     * 当然上一个方法中的反转链表也可以进行实现
     */
    public static void reversePrint(HeroNode head) {
        // 首先判断链表本身
        if (null == head.next) {
            System.out.println("链表为空，不能进行打印");
        }
        // 利用stack进行反转打印
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (null != temp) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
    /**
     * 合并两个有序的单链表
     * 方案：
     * 用一个单链表为基础 遍历另一个链表  有序添加的基础链表
     */
    public static SingleLinkedList mergeTwoList(SingleLinkedList list1, SingleLinkedList list2) {
        // 遍历第二个链表
        if (null == list2.head.next) {
            System.out.println("存在空链表，无需合并");
        }
        HeroNode temp = list2.head.next;
        HeroNode next = null; // 保留下一个节点  保证添加的新节点next为null防止出现  添加节点出现链式
        while (null != temp) {
            next = temp.next;
            temp.next = null;
            list1.addByOrder(temp);
            temp = next;
        }
        return list1;
    }

}

class SingleLinkedList {
    //初始化头节点，头结点不要动，不存放具体的数据
    public HeroNode head = new HeroNode(0, "", "");

    //定义添加的方法：找到链表的最后的节点，将这个节点的next指向新的节点
    public void add(HeroNode node) {
        //定义temp中间变量
        HeroNode temp = head;
        //遍历已有的链表，找到最后的节点。
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //头节点不能动   定义辅助变量来进行遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //按照编号顺序进行人物添加
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        //定义节点是否存在的标识
        Boolean flag = false;
        while (true) {
            if (temp.next == null) {//说明此时已经到达最后一个节点
                break;
            }
            if (temp.next.no > node.no) {//说明此时temp的下一个节点就是插入的位置
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的节点编号 %d 已经存在，不能加入\n", node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    //修改节点
    public void update(HeroNode heroNode) {
        //辅助接点
        HeroNode temp = head;
        //定义是否找到标识
        Boolean flag = false;
        if (temp.next == null) {
            System.out.println("链表为空！");
            return;
        }
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next.name = heroNode.name;
            temp.next.nickname = heroNode.nickname;
        } else {
            System.out.printf("没有找到编号 %d 的节点，不能进行修改！", heroNode.no);
        }
    }
    //删除节点

    /**
     * 1.定义测试节点进行遍历
     * 2.通过比较no找到要删除节点的前一个英雄
     * 3.将temp.next = temp.next.next
     *
     * @param no
     */
    public void del(int no) {
        HeroNode temp = head;
        //定义是否找到的标识
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {//说明找到
            temp.next = temp.next.next;
        } else {
            System.out.println("该节点不存在，无法进行删除!");
        }
    }

    /**
     * 在链表头部添加节点
     */
    public void addHead(HeroNode heroNode) {
        // 判断链表此时不为空
        if (null == head.next) {
            head.next = heroNode;
            return;
        } else {
            HeroNode temp = head.next;
            // 将头节点指向新节点 新节点的next指向原来的节点
            head.next = heroNode;
            heroNode.next = temp;
        }
    }

}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
