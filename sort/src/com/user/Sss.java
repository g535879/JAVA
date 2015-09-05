package com.user;

public class Sss {
	public static void main(String args[]){
		int[] a = new int[10];
		a[0] = 1111;
		a[1] = 1175;
		a[2] = 1176;
		a[3] = 11177;
		a[4] = 11178;
		a[5] = 11800;
		a[6] = 11805;
		a[7] = 111000;
		a[8] = 111210;
		a[9] = 111401;
		int[] b = compressAs(a);
		for(int index=0; index < b.length; index++){
			System.out.println(b[index]);
		}
	}
	
	//压缩石子数组
			public static int[] compressAs(int[] a){
					int l;        //缩短宽度
					if(a[0] > 100){
						
						l = a[0] - 100;
						for(int index = 0; index < a.length; index++){
							a[index] = a[index] -l;
						}
					}
					
					for(int index = 0; index < a.length-1; index++){
						if((a[index+1] - a[index]) > 100){
							l = a[index+1] - a[index] - 100;
							for(int j = index+1; j < a.length; j++){
								a[j] = a[j] - l;
							}
						}
					}
					return a;
				}

	//最大公约数
	public static int mineNumber(int a,int b){
		int _a = a;
		int _b = b;
		int maxNumber = 0; //最大公约数
		if(a == b){
			return a;
		}
		else if(b % a == 0){
			return b;
		}
		else{
			while(b % a != 0){
				 maxNumber = b % a;
				 b = a;
				 a = maxNumber;
			}
			return (_a * _b) / maxNumber;
		}
	}
	/*//寻找当前位置前面最近的石子
		public static int status(int number,int[] shiZi,int type){
			for(int i = 0; i < shiZi.length; i++){
				if(number <  shiZi[i]){           //找到最近的石子
					if((shiZi[i] - number) > MinNumber){               //大于最小公倍数
						if((shiZi[i] - number) % MinNumber == 0){
							return ((((shiZi[i] - number)/MinNumber)*MinNumber)-type);
						}
						else{
							return ((shiZi[i] - number)/MinNumber)*MinNumber;
						}
					}
					break;
				}
				else if(number > shiZi[shiZi.length-1]){          //后面没有石子
					return briageLength;
				}
			}
			return 0;
		}*/
	
}
