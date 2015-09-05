package com.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Three {
	static int briageLength = 0;               //桥长度
	static int S,T,M;                          // S：最小距离 T：最大距离 M：石子个数
	static int compressA[];               //压缩石头数组
	static int isPass[];                    // 某坐标下是否有石子
	static int[] shiZi;                   //石子数组
	static int MAX_L = 100;                 //石子间距
	static int f[];       //动态规划数组
	static List<Integer> list = new ArrayList<Integer>();              //存放结果list
	
	public static void main(String args[]){
	
		Scanner c = new Scanner(System.in);
		//桥长度
		briageLength = Integer.parseInt(c.nextLine());
		String[] p = c.nextLine().split(" ");
	    String[] m = c.nextLine().split(" ");        //读取石子
	  //赋值给 S T M
	    S = Integer.parseInt(p[0]);
	    T = Integer.parseInt(p[1]);
	    M = Integer.parseInt(p[2]);
	   // MinNumber = mineNumber(S,T);                 //S和T的最小公倍数
	    shiZi = new int[m.length];            //建立石子数组
	    for(int index = 0; index < shiZi.length; index++){
	    	shiZi[index] = Integer.parseInt(m[index]);          //把String转化成int
	    }
	    shiZi = s(shiZi);         //石子排序	
	   
	    if( S == T){               //如果S == T
	    	spec(S);               //特殊情况处理
	    }
	    else{
	    	compressA = compressAs(shiZi);              //压缩石子
	    	f = new int[compressA[compressA.length-1]+T+1];
	    	isPass = new int[compressA[compressA.length-1]+T+1];
	    	initPass();                         //初始化isPass数组
	    	setShiZi();                         //isPass赋值
	    	initF();                        //初始化f
	    	//动态规划
	    	for(int index = 0; index < compressA[compressA.length-1]+T+1; index++){
	    		int min = 1000;
	    		for(int j = index-T;j <= index-S;j++){
	    					if(j > 0 && f[j] < min){
			    				min = f[j];
			    			}
	    		}
	    		if(index == S || index == T){
	    			min = 0;
	    		}
	    		f[index] = min + isPass[index];
	    	}
	    	 // f = s(f);        //排序
	    	  System.out.println(f[ compressA[compressA.length-1]+T]);
	    }
	  
	    
	}
	
	//排序
	public static int[] s(int[] a){
		int b[] = a;
		for(int i = 0; i < a.length; i++){
			for(int j = i; j < a.length; j++){
				if(b[i] > b[j]){
					b[i] = b[i] + b[j];
					b[j] = b[i] - b[j];
					b[i] = b[i] - b[j];
				}
			}
		}
		return b;
	}
	
	//压缩石子数组
	public static int[] compressAs(int[] a){
			int l;        //缩短宽度
			if(a[0] > MAX_L){
				
				l = a[0] - MAX_L;
				for(int index = 0; index < a.length; index++){
					a[index] = a[index] -l;
				}
			}
			
			for(int index = 0; index < a.length-1; index++){
				if((a[index+1] - a[index]) > MAX_L){
					l = a[index+1] - a[index] - MAX_L;
					for(int j = index+1; j < a.length; j++){
						a[j] = a[j] - l;
					}
				}
			}
			return a;
		}
	
	//特殊情况处理
	public static void spec(int j){
		int count = 0;
		for(int i = 0; i < shiZi.length; i++){
			if(shiZi[i]%j == 0){
				count++;
			}
		}
		System.out.println(count);
	}
	//初始化isPass
	public static void initPass(){
		for(int i = 0; i < isPass.length; i++){
			isPass[i] = 0;
		}
	}
	//初始化f
		public static void initF(){
			for(int i = 0; i < f.length; i++){
				f[i] = 100;
			}
		}
	//isPass赋值
	public static void setShiZi(){
		for(int index = 0; index < compressA.length; index++){
			isPass[compressA[index]] = 1;                       //有石子的l下标设为false
		}
	}
	
}
