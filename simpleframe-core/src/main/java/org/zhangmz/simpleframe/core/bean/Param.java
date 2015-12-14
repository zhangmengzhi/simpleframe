package org.zhangmz.simpleframe.core.bean;

import java.util.Map;
import org.zhangmz.simpleframe.core.util.CastUtil;
import org.zhangmz.simpleframe.core.util.CollectionUtil;

/**
 * 
 * @ClassName:Param 
 * @Description:请求参数对象
 * @author:张孟志
 * @date:2015年12月14日 下午8:18:31 
 * @version V1.0
 * 说明：请求参数对象
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
    
    /**
     * 验证参数是否为空
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(this.paramMap);
    }

    /**
     * 获取请求参数映射
     */
    public Map<String, Object> getFieldMap() {
        return this.paramMap;
    }

    /**
     * 根据参数名获取 String 型参数值
     */
    public String getString(String name) {
        return CastUtil.castString(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 double 型参数值
     */
    public double getDouble(String name) {
        return CastUtil.castDouble(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 long 型参数值
     */
    public long getLong(String name) {
        return CastUtil.castLong(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 int 型参数值
     */
    public int getInt(String name) {
        return CastUtil.castInt(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取 boolean 型参数值
     */
    public boolean getBoolean(String name) {
        return CastUtil.castBoolean(getFieldMap().get(name));
    }
}
