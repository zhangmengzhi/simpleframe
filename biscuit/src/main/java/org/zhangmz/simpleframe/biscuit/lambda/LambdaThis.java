package org.zhangmz.simpleframe.biscuit.lambda;

/**
 * 
 * @ClassName:Lambda 
 * @Description:lambda表达式 this的面目
 * @author:张孟志
 * @date:2015年12月24日 上午10:50:06 
 * @version V1.0
 * 说明：演示lambda表达式中this对应的对象
 *      ＴＯＤＯ：还有final的表现需要演示
 *      ＪＤＫ８之前，如果要在匿名内部类中存取局部变量，则局部变量必须为final，否则有编译错误
 *      ＪＤＫ８中，如果变量本身等效于final局部变量（变量在匿名内部类中没有重新指定的动作），
 *      可以不用加final关键词。
 */
public class LambdaThis {

	// 匿名写法
	Runnable r1 = new Runnable() {
		
		@Override
		public void run() {
			System.out.println(this);
		}
	};
	
	Runnable r2 = new Runnable() {
		
		@Override
		public void run() {
			System.out.println(toString());  // 事实执行的是this.toString()
		}
	};
	
	// lambda表达式
	Runnable r3 = () -> System.out.println(this);
	
	Runnable r4 = () -> System.out.println(toString());
	
	public String toString() {
		return "Hello World !";
	}
	
	public static void main(String[] args) {
		LambdaThis lt  = new LambdaThis();
		lt.r1.run();  // org.zhangmz.simpleframe.biscuit.lambda.LambdaThis$1@1b3ebeb
		lt.r2.run();  // org.zhangmz.simpleframe.biscuit.lambda.LambdaThis$2@8c4f57
		lt.r3.run();  // Hello World !
		lt.r4.run();  // Hello World !
	}
}
