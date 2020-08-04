package com.leetcode.queue;

public class MyCircularQueue {
	public static void main(String[] args) {
		MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3

		circularQueue.enQueue(1);  // 返回 true

		circularQueue.enQueue(2);  // 返回 true

		circularQueue.enQueue(3);  // 返回 true

		circularQueue.enQueue(4);  // 返回 false，队列已满

		circularQueue.Rear();  // 返回 3

		circularQueue.isFull();  // 返回 true

		circularQueue.deQueue();  // 返回 true

		circularQueue.enQueue(4);  // 返回 true

		circularQueue.Rear();  // 返回 4
	}
    private int maxSize;//队列的长度
    private int front;//指针头
    private int rear;//指针尾
    private int[] arr;//数组实现循环队列
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {//初始化循环队列
        this.arr = new int[k];
        this.maxSize = k;
        this.front = -1;
        this.rear = -1;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        //插入之前判断队列是否满
        if(isFull()){      
            System.out.println("false");        
            return false;
        }   
        if(isEmpty()) {
        	front = 0;
        }
        rear = (rear+1)%maxSize;
        arr[rear] = value;
		System.out.println("true");        
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        //判断队列是否为空
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能获取数据");
		}
		//1.先把front对应的值保存到一个临时变量。
		//2.将front后移，考虑取模
		//3.将临时保存的变量返回
		front = (front+1)%maxSize;
		int value = arr[front];		
		System.out.println("true");
		return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        //判断队列是否为空
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能获取数据");
		}
                           		System.out.println(arr[front]);        

        return arr[front];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
    	 //判断队列是否为空
		if(isEmpty()) {
			throw new RuntimeException("队列为空，不能获取数据");
		}
                   		System.out.println(arr[rear]);        
 
        return arr[rear];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return rear == front;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (rear+1)%maxSize == front;
    }
}