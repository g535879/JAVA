package com.user;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestStack {
	public static void main(String args[]){
		String e = "(3-1)+4";
		Pattern pattern = Pattern.compile("(\\d{1,5}|.)");
		Matcher matcher = pattern.matcher(e);
		List<String> list = new ArrayList<String>();
		while(matcher.find()){
			list.add(matcher.group());
		}
		String result  = justNumber(list);             //计算
		System.out.println(result);                    //输出结果
	} 
	//计算
	public static String justNumber(List<String> list){
		Stack<String> s = new Stack<String>();
		String operate = null;           //记录操作符
		for(int i = 0; i < list.size();i++){
			if(list.get(i).matches("\\d{1,5}")){
				s.push(list.get(i));
			}
			else if(list.get(i).equals("(")){    //读取了左括号
				Map<String,Object> resultMap = k(list,i); //处理括号
				List<String> childList = (List<String>)resultMap.get("childList");
				int cCount = (Integer)resultMap.get("count");
				i+= cCount;
				s.push(justNumber(childList));
			}
			else{      //如果判断为操作符
				if(operate == null || operate == ""){
					s.push(list.get(i));             //操作符入栈
					operate = list.get(i);         //记录操作符
				}
				else{             //如果操作符不为空则说明为第二个操作符
					if(justOperate(operate, list.get(i))){          //如果前面的操作符优先级高
						s.push(c(s.pop(),s.pop(),s.pop(),true));
						s.push(list.get(i));
						operate = list.get(i);
					}
					else{
						if(!list.get(++i).matches("\\d{1,5}")){      //如果读取的不是数字
							Map<String,Object> resultMap = k(list,i);
							List<String> cList = (List<String>)resultMap.get("childList");
							int cCount = (Integer)resultMap.get("count");
							s.push(c(s.pop(), list.get(i-1), justNumber(cList),false));
							i+= cCount;
						}
						else{
							s.push(c(s.pop(), list.get(i-1), list.get(i),false));
						}
						
					}
				}
				
			}
		}
		int count = 0;                                     //统计目前栈中的元素个数
		Enumeration<String> items = s.elements();     //得到栈中的所有元素
		String result = "";                      //存储栈中的元素
		while(items.hasMoreElements()){
			result += items.nextElement();
			count++;
		}
		if(count > 2){
			s.push(c(s.pop(), s.pop(), s.pop(),true));          //最后一个操作符计算
		}
		else{
			return result;
		}
		return s.pop();
	}
	
  //判断操作符优先级
	public static boolean justOperate(String op1,String op2){
		boolean isPrivate = false;
		if(numberOperate(op1) >= numberOperate(op2)){
			isPrivate = true;
		}
		return isPrivate;
	}
	//操作符优先级赋值
	public static int numberOperate(String op){
		if(op.equals("+") || op.equals("-")){
			return 2;
		}
		else if(op.equals("*")){
			return 3;
		}
		else if(op.equals("^")){
			return 4;
		}
		else if(op.equals("(") || op.equals(")")){
			return 5;
		}
		return 1;
	}
	//根据操作符不同计算结果.type=1为前面的操作
	public static String c(String number1,String op, String number2,boolean type){
		int a,b;
		int result = -1;
		if(type){
			 a = Integer.parseInt(number2);
			 b = Integer.parseInt(number1);
		}
		else{
			a = Integer.parseInt(number1);
			 b = Integer.parseInt(number2);
		}
		if(op.equals("+")){
			result = a + b;
		}
		else if(op.equals("-")){
			result = a - b;
		}
		else if(op.equals("*")){
			result = a * b;
		}
		else if(op.equals("^")){
			result = (int) Math.pow(a, b);
		}
		return result+"";
	}
	//处理括号的情况
	public static Map<String,Object> k(List<String> list,int i){
		int count = i;                                     //统计i增加的个数
		Map<String,Object> map = new HashMap<String,Object>();
		i++;
		int leftK = 1;               //左括号统计
		int rightK = 0;            //右括号统计
		List<String> childList = new ArrayList<String>();
		while(true){             //循环直到左右括号个数相等
			if(list.get(i).equals("(")){
				leftK ++;
			}
			else if(list.get(i).equals(")")){
				rightK ++;
			}
			if(leftK != rightK){
				childList.add(list.get(i++));       //保存子表达式
			}
			else{
				break;
			}
		}
		map.put("count", i - count);
		map.put("childList", childList);
		return map;
	}
}