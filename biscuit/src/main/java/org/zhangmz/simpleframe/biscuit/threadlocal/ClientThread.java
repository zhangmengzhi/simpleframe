package org.zhangmz.simpleframe.biscuit.threadlocal;

public class ClientThread extends Thread {

	private Sequence sequence;
	
	public ClientThread(Sequence sequence) {
		this.sequence = sequence;
	}
	
	@Override
	public void run() {
		// 用序号发生器循环获取序号。
		for (int i = 0; i < 3; i++) {
			System.out.println(
					Thread.currentThread().getName() + " -> " + this.sequence.getNumber());
		}
	}

}
