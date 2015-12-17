package org.zhangmz.simpleframe.biscuit.threadlocal;

/**
 * 
 * @ClassName:SequenceA 
 * @Description:没有使用ThreadLocal的序号发生器
 * @author:张孟志
 * @date:2015年12月17日 下午4:01:12 
 * @version V1.0
 * 说明：在不使用ThreadLocal的情况下，并发时通常会发生错误。
 */
public class SequenceA implements Sequence {

	private static int number = 0;
	
	@Override
	public int getNumber() {
		return ++number;
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
		SequenceA sequence = new SequenceA();
		
		ClientThread t1 = new ClientThread(sequence);
		ClientThread t2 = new ClientThread(sequence);
		ClientThread t3 = new ClientThread(sequence);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
