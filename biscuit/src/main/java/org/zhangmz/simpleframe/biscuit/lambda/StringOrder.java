package org.zhangmz.simpleframe.biscuit.lambda;

/**
 * 
 * @ClassName:StringOrder 
 * @Description:字符串排序算法
 * @author:张孟志
 * @date:2015年12月24日 下午2:21:03 
 * @version V1.0
 * 说明：字符串排序算法
 */
public class StringOrder {

	public static int byLength(String str1, String str2){
		return str1.length() - str2.length();
	}
	
	public static int byLexicography(String str1, String str2) {
		return str1.compareTo(str2);
	}
	
	public static int byLexicographyIgnoreCase(String str1, String str2) {
		return str1.compareToIgnoreCase(str2);
	}
}
