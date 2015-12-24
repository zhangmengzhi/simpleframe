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
		innerClassMethod();

		//　2、JDK8 lambda表达式
		lambda();
		
		/**************************************************************************************
		 * 带参数接口的案例
		 **************************************************************************************/
		// 3、编写带参数的接口，使用匿名类语法实现
		innerClassMethodWithPara();
				
		// 4、编写带参数的接口，JDK8 lambda表达式
		lambdaWithPara();
		
		/**************************************************************************************
		 * 接口中的默认实现方法
		 **************************************************************************************/
		defaultMethod();
		
		/**************************************************************************************
		 * 方法参考（Method Reference）
		 **************************************************************************************/
		methodReference();
	}
	
	/**
	 * 
	 * @Title: innerClassMethod 
	 * @Description: 使用匿名类语法实现
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月24日 下午3:03:33
	 * 说明：使用匿名类语法实现
	 */
	public static void innerClassMethod() {
		Request request1 = new Request() {			
			@Override
			public void execute() {
				System.out.println("request1处理XX事件，时间点：" + System.currentTimeMillis());
			}
		};		
		request1.execute();		
	}
	
	/**
	 * 
	 * @Title: lambda 
	 * @Description: JDK8 lambda表达式
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月24日 下午3:05:04
	 * 说明：JDK8 lambda表达式
	 */
	public static void lambda() {
		Request request2 = () -> System.out.println("request2处理XX事件，时间点：" + System.currentTimeMillis());
		request2.execute();
	}
	
	/**
	 * 
	 * @Title: innerClassMethodWithPara 
	 * @Description: 编写带参数的接口，使用匿名类语法实现
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月24日 下午3:06:40
	 * 说明：编写带参数的接口，使用匿名类语法实现
	 */
	public static void innerClassMethodWithPara() {
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
	}

	/**
	 * 
	 * @Title: lambdaWithPara 
	 * @Description: 编写带参数的接口，JDK8 lambda表达式
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月24日 下午3:08:50
	 * 说明：编写带参数的接口，JDK8 lambda表达式
	 */
	public static void lambdaWithPara() {
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
	}
	
	/**
	 * 
	 * @Title: defaultMethod 
	 * @Description: 接口中的默认实现方法
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月24日 下午3:12:28
	 * 说明：接口中的默认实现方法
	 */
	public static void defaultMethod() {
		RequestPara requestPara3 = (forString, forBoolean) -> null;
		System.out.println(requestPara3.doServer());
	}
	
	/**
	 * 
	 * @Title: methodReference 
	 * @Description: 方法参考（Method Reference）
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月24日 下午3:11:10
	 * 说明：方法参考（Method Reference）
	 */
	public static void methodReference() {
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
		// Comparator<String> byLength = StringOrder::byLength;
		// Arrays.sort(names, byLength);
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
