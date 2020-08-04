package com.wyj.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
	public static void main(String[] args) {
		ArrayQueue arrayQueue = new ArrayQueue(3);
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		System.out.println("s(show):显示队列");
		System.out.println("e(sxit):退出程序");
		System.out.println("a(add):添加数据队列");
		System.out.println("g(get):从队列中获取数据");
		System.out.println("h(head):查看队列头的数据");
		while(loop) {
			key = scanner.next().charAt(0);
			switch(key) {
			case 's':
				try {
					arrayQueue.show();
				}catch(Exception e){
					System.out.println(e.getMessage());
				}				
				break;
			case 'e':
				scanner.close();
				loop = false;
				break;
			case 'a':
				System.out.println("请输入一个数据：");
				int value = scanner.nextInt();
				arrayQueue.addQueue(value);
				break;
			case 'g':
				try {
					int value1 = arrayQueue.getQueue();
					System.out.print("获取的数据为："+value1);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}		
				break;
			case 'h':
				try {
					arrayQueue.headQueue();
				}catch(Exception e){
					System.out.println(e.getMessage());
				}	
				break;
			default:
				break;
			}
		}
		System.out.println("--------------程序退出----------");
	}
}

//定义数组实现队列的数据结构
class ArrayQueue{
	private int maxSize;//数组容量
	private int front;//指针头
	private int rear;//指针尾
	private int[] arr;//数组实现
	/**
	 * 构造函数进行变量初始化
	 */
	public ArrayQueue(int maxSize) {
		super();
		this.maxSize = maxSize;
		this.front = -1;
		this.rear = -1;
		this.arr = new int[maxSize];
	}
	/**
	 * 判断队列是否满的方法
	 */
	public boolean isFull() {
		return rear == maxSize-1;
	}
	/**
	 * 判断队列是否为空
	 */
	public boolean isEmpty() {
		return rear == front;
	}
	/**
	 * 添加数据到队列
	 */
	public void addQueue(int n) {
		//判断队列是否满
		if(isFull()) {
			System.out.println("队列已满，不能添加数据~");
			return;
		}
		rear++;
		arr[rear] = n;
	}
	/**
	 * 获取队列的数据，出地列
	 */
	public int getQueue() {
		//判断队列是否为空
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能获取数据");
		}
		front++;
		return arr[front];
	}
	/**
	 * 显示队列的所有数据
	 */
	public void show() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能获取数据");
		}
		for(int i=0;i<arr.length;i++) {
			System.out.printf("arr[%d]=%d\n",i,arr[i]);
		}
	}
	/**
	 * 显示投头数据
	 */
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能获取数据");
		}
		return arr[front+1];
	}
}
