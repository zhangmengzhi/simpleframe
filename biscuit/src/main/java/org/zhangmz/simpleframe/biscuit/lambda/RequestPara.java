package org.zhangmz.simpleframe.biscuit.lambda;

/**
 * 
 * @ClassName:RequestPara
 * @Description:带参数的接口
 * @author:张孟志
 * @date:2015年12月24日 上午10:51:24 
 * @version V1.0
 * 说明：带参数的接口，包括输入参数，返回值。
 */
public interface RequestPara {
	public String execute(String forString, boolean forBoolean);
	
	/**
	 * 
	 * @Title: doServer 
	 * @Description: 接口中的默认实现方法
	 * @return
	 * @throws 
	 * 增加人:张孟志
	 * 增加日期:2015年12月24日 上午11:26:23
	 * 说明：接口中的默认实现方法
	 */
	default public String doServer() {
		return "Hello World !";
	}
}
