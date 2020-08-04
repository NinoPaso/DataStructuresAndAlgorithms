package com.wyj.sparsearray;
/*
 * 稀疏数组的实现
 */
public class SparseArray {
	public static void main(String[] args) {
		//定义二维数组棋盘,0位空，1为黑子，2为白子
		int[][] array = new int[11][11];
		array[1][2] = 1;
		array[2][3] = 2;
		System.out.println("-----------------原始的二维数组----------------");
		//输出原始的二维数组
		for(int[] row:array) {
			for(int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		//将此二维数组转为稀疏数组
		int sum = 0;
		//遍历二维数组确定非0个数
		for(int i=0;i<11;i++) {
			for(int j=0;j<11;j++) {
				if(array[i][j] != 0) {
					sum++;
				}
			}
		}
		//创建数组并赋值第一行
		int sparseArr[][] = new int[sum+1][3];
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;
		//定义count记录出现非0数据的次数
		int count = 0;
		for(int i=0;i<11;i++) {
			for(int j=0;j<11;j++) {
				if(array[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = array[i][j];
				}
			}
		}
		//输出稀疏数组
		System.out.println("·············得到的稀疏数组为·············");
		for(int i=0;i<sparseArr.length;i++) {
			System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
		}
		//将稀疏数组恢复
		//先读取稀疏数组的第一行，根据第一行的数据创建原始的二维数组
		int chessArr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		//遍历稀疏数组进行原数组的赋值
		for(int i=1;i<sparseArr.length;i++) {
			chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2]; 
		}
		System.out.println("·············恢复后的数组为·············");
		//输出原始的二维数组
		for(int[] row:chessArr) {
			for(int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
	}
}
