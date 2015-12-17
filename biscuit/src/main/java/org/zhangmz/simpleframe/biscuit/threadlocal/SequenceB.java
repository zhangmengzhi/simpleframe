package org.zhangmz.simpleframe.biscuit.threadlocal;

/**
 * 
 * @ClassName:SequenceA 
 * @Description:使用ThreadLocal的序号发生器
 * @author:张孟志
 * @date:2015年12月17日 下午4:01:12 
 * @version V1.0
 * 说明：使用ThreadLocal的情况。
 */
public class SequenceB implements Sequence {

	private static ThreadLocal<Integer> number = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}		
	};
	
	@Override
	public int getNumber() {
		number.set(number.get() + 1);
		return number.get();
	}

	/**
	 * 
	 * @Title: main 
	 * @Description: 
	 * @param args
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月17日 下午4:12:38
	 * 说明：测试一下这个序号发生器
	 */
	public static void main(String[] args) {
		SequenceB sequence = new SequenceB();
		
		ClientThread t1 = new ClientThread(sequence);
		ClientThread t2 = new ClientThread(sequence);
		ClientThread t3 = new ClientThread(sequence);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
