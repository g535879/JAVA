package com.user;

import java.util.Scanner;

public class TitleOne {
	
	public static void main(String[] args){
		int number = -1;     //学生总数
		String[] b = new String[7];        //学生数据
		int count = 0;           //循环次数
		int maxMoney = 0;        //钱最多的人的坐标
		int money = 0;           //钱总数
		Scanner c = new Scanner(System.in);
		//读取用户输入人数
		number = Integer.parseInt(c.nextLine());
		//建立用户数组。
		String[][] a = new String[number][b.length];
		//读取键盘数据，分配到用户数组
		while(count < number){
			b = c.nextLine().split(" ");
			for(int j = 0; j < 6; j++){
				a[count][j] = b[j];
			}
			count++;
			
		}
		//循环便利判断
		for(int i = 0; i < number; i++){
			a[i][6]  = just(a,i);       //计算奖学金
		}
		money = Integer.parseInt(a[0][6]);
		maxMoney = 0;
		for(int i = 1;i < number; i++){
			money += Integer.parseInt(a[i][6]);         //把钱加起来
			if(Integer.parseInt(a[maxMoney][6]) < Integer.parseInt(a[i][6])){
				maxMoney = i;
			}
		}
		
		//输出结果
		System.out.println(a[maxMoney][0]);
		System.out.println(a[maxMoney][6]);
		System.out.print(money);
	}
	public static String just(String[][] data,int i){
		int money = 0;
		if(Integer.parseInt(data[i][1]) > 80 && Integer.parseInt(data[i][5]) >= 1){
			money += 8000;
		}
		if(Integer.parseInt(data[i][1]) > 85 && Integer.parseInt(data[i][2]) > 80){
			money += 4000;
		}
		if(Integer.parseInt(data[i][1]) > 90){
			money += 2000;
		}
		if(Integer.parseInt(data[i][1]) > 85 && data[i][4].equals("Y")){
			money += 1000;
		}
		if(Integer.parseInt(data[i][2]) > 80 && data[i][3].equals("Y")){
			money  += 850;
		}
		return money+"";
	}
}