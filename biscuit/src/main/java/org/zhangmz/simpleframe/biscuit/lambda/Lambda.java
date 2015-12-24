package org.zhangmz.simpleframe.biscuit.lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @ClassName:Lambda 
 * @Description:lambda表达式
 * @author:张孟志
 * @date:2015年12月24日 上午10:50:06 
 * @version V1.0
 * 说明：演示lambda表达式
 */
public class Lambda {

	public static void main(String[] args) {
		
		/**************************************************************************************
		 * 简单接口案例
		 **************************************************************************************/
		// 1、使用匿名类语法实现
		Request request1 = new Request() {			
			@Override
			public void execute() {
				System.out.println("request1处理XX事件，时间点：" + System.currentTimeMillis());
			}
		};		
		request1.execute();
		
		//　2、JDK8 lambda表达式
		Request request2 = () -> System.out.println("request2处理XX事件，时间点：" + System.currentTimeMillis());
		request2.execute();

		/**************************************************************************************
		 * 带参数接口的案例
		 **************************************************************************************/
		// 3、编写带参数的接口，使用匿名类语法实现
		RequestPara requestPara1 = new RequestPara() {			
			@Override
			public String execute(String forString, boolean forBoolean) {
				if(forBoolean) {
					return forString.toUpperCase();
				} else {
					return forString.toLowerCase();
				}
			}
		};
		System.out.println(requestPara1.execute("Hello", true));
		System.out.println(requestPara1.execute("Hello", false));
				
		// 4、编写带参数的接口，JDK8 lambda表达式
		// public String execute(String forString, boolean forBoolean);
		RequestPara requestPara2 = (forString, forBoolean) -> {
			if(forBoolean) {
				return forString.toUpperCase();
			} else {
				return forString.toLowerCase();
			}
		};
		System.out.println(requestPara2.execute("World", true));
		System.out.println(requestPara2.execute("World", false));
		
		/**************************************************************************************
		 * 接口中的默认实现方法
		 **************************************************************************************/
		RequestPara requestPara3 = (forString, forBoolean) -> null;
		System.out.println(requestPara3.doServer());
		
		/**************************************************************************************
		 * 方法参考（Method Reference）
		 **************************************************************************************/
		// 1、将用户名称依长度进行排序
		String[] names = {"Justin", "caterpillar", "Bush"};		
		System.out.println("起始数组：" + Arrays.toString(names));
		
		Arrays.sort(names, new Comparator<String>() {

			@Override
			public int compare(String str1, String str2) {
				return str1.length() - str2.length();
			}
		
		});
		
		System.out.println("1、将用户名称依长度进行排序。排序数组：" + Arrays.toString(names));
		
		// 2、改善可读性	
		Comparator<String> byLength = new Comparator<String>() {

			@Override
			public int compare(String str1, String str2) {
				return str1.length() - str2.length();
			}
		
		};
		
		Arrays.sort(names, byLength);
		
		System.out.println("2、改善可读性。排序数组：" + Arrays.toString(names));
		
		// 3、lambda表达式
		Arrays.sort(names, (str1, str2) -> str1.length() - str2.length());
		System.out.println("3、lambda表达式。排序数组：" + Arrays.toString(names));
		
		// 4、方法参考
		Arrays.sort(names, StringOrder::byLength);
		System.out.println("4、方法参考。byLength排序数组：" + Arrays.toString(names));

		// Arrays.sort(names, String::compareTo);
		Arrays.sort(names, StringOrder::byLexicography);
		System.out.println("4、方法参考。byLexicography排序数组：" + Arrays.toString(names));

		// Arrays.sort(names, String::compareToIgnoreCase);
		Arrays.sort(names, StringOrder::byLexicographyIgnoreCase);
		System.out.println("4、方法参考。byLexicographyIgnoreCase排序数组：" + Arrays.toString(names));
		
	}

}
