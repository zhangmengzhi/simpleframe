package org.zhangmz.simpleframe.biscuit.threadlocal;

/**
 * 
 * @ClassName:Sequence 
 * @Description:序号发射器接口
 * @author:张孟志
 * @date:2015年12月17日 下午3:58:28 
 * @version V1.0
 * 说明：序号发生器的程序可能同时会有多个线程并发访问它，要保证每个线程
 *      得到的序号都是自增的，而不能相互干扰。
 */
public interface Sequence {
	int getNumber();
}
