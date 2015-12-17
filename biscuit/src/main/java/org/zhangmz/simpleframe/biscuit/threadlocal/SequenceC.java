package org.zhangmz.simpleframe.biscuit.threadlocal;

/**
 * 
 * @ClassName:SequenceA 
 * @Description:使用MyThreadLocal的序号发生器
 * @author:张孟志
 * @date:2015年12月17日 下午4:01:12 
 * @version V1.0
 * 说明：使用MyThreadLocal的情况。
 */
public class SequenceC implements Sequence {

	private static MyThreadLocal<Integer> number = new MyThreadLocal<Integer>() {
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
		SequenceC sequence = new SequenceC();
		
		ClientThread t1 = new ClientThread(sequence);
		ClientThread t2 = new ClientThread(sequence);
		ClientThread t3 = new ClientThread(sequence);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
