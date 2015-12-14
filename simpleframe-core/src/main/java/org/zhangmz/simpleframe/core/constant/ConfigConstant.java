package org.zhangmz.simpleframe.core.constant;

/**
 * 
 * @ClassName:ConfigConstant 
 * @Description:定义框架配置项
 * @author:张孟志
 * @date:2015年12月13日 下午4:12:28 
 * @version V1.0
 * 说明：提供相关配置项常量
 */
public interface ConfigConstant {
	
	// 默认的配置文件
    String CONFIG_FILE = "simple.properties";

    // 数据库连接参数
    String JDBC_DRIVER = "simple.framework.jdbc.driver";
    String JDBC_URL = "simple.framework.jdbc.url";
    String JDBC_USERNAME = "simple.framework.jdbc.username";
    String JDBC_PASSWORD = "simple.framework.jdbc.password";

    // 项目基础参数
    String APP_BASE_PACKAGE = "simple.framework.app.base_package";
    String APP_JSP_PATH = "simple.framework.app.jsp_path";         // 默认/WEB-INF/view/
    String APP_ASSET_PATH = "simple.framework.app.asset_path";     // 默认/asset/
    String APP_UPLOAD_LIMIT = "simple.framework.app.upload_limit"; // 默认10
}
