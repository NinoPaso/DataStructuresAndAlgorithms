package com.wyj.linkedlist;

public class SingleLinkedListDemo {
	public static void main(String[] args) {
		//创建节点
		HeroNode h1 = new HeroNode(1,"宋江","及时雨");
		HeroNode h2 = new HeroNode(2,"卢俊义","玉麒麟");
		HeroNode h3 = new HeroNode(3,"吴用","智多星");
		HeroNode h4 = new HeroNode(4,"林冲","豹子头");
		//创建链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.addByOrder(h1);
		singleLinkedList.addByOrder(h4);
		singleLinkedList.addByOrder(h3);
		singleLinkedList.addByOrder(h4);
		singleLinkedList.addByOrder(h4);
		singleLinkedList.list();
		HeroNode newHeroNode = new HeroNode(4,"林冲er","豹子头eeeeeee");
		singleLinkedList.update(newHeroNode);
		singleLinkedList.list();
		singleLinkedList.del(3);
		singleLinkedList.list();
	}
}

class SingleLinkedList{
	//初始化头节点，头结点不要动，不存放具体的数据
	private HeroNode head = new HeroNode(0,"","");
	//定义添加的方法：找到链表的最后的节点，将这个节点的next指向新的节点
	public void add(HeroNode node) {
		//定义temp中间变量
		HeroNode temp = head;
		//遍历已有的链表，找到最后的节点。
		while(true) {
			if(temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = node;
	}
	//显示链表
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//头节点不能动   定义辅助变量来进行遍历
		HeroNode temp = head.next;
		while(true) {
			if(temp == null) {
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
		while(true) {
			if(temp.next == null) {//说明此时已经到达最后一个节点
				break;
			}
			if(temp.next.no > node.no) {//说明此时temp的下一个节点就是插入的位置
				break;
			}else if(temp.next.no == node.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			System.out.printf("准备插入的节点编号 %d 已经存在，不能加入\n",node.no);
		}else {	
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
		if(temp.next == null) {
			System.out.println("链表为空！");
			return;
		}
		while(true) {
			if(temp.next == null) {
				break;
			}
			if(temp.next.no == heroNode.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			temp.next.name = heroNode.name;
			temp.next.nickname = heroNode.nickname;
		}else {
			System.out.printf("没有找到编号 %d 的节点，不能进行修改！",heroNode.no);
		}
	}
	//删除节点
	/**
	 * 1.定义测试节点进行遍历
	 * 2.通过比较no找到要删除节点的前一个英雄
	 * 3.将temp.next = temp.next.next
	 * @param no
	 */
	public void del(int no){
		HeroNode temp = head;
		//定义是否找到的标识
		boolean flag = false;
		while(true) {
			if(temp.next == null) {
				break;
			}
			if(temp.next.no == no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {//说明找到
			temp.next = temp.next.next;
		}else {
			System.out.println("该节点不存在，无法进行删除!");
		}
	}
}

class HeroNode{
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;
	//构造器
	public HeroNode(int no,String name,String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
	
}
