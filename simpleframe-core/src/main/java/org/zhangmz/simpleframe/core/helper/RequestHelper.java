package org.zhangmz.simpleframe.core.helper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.zhangmz.simpleframe.core.bean.Param;
import org.zhangmz.simpleframe.core.util.ArrayUtil;
import org.zhangmz.simpleframe.core.util.CodecUtil;
import org.zhangmz.simpleframe.core.util.StreamUtil;
import org.zhangmz.simpleframe.core.util.StringUtil;

/**
 * 
 * @ClassName:RequestHelper 
 * @Description:请求助手类
 * @author:张孟志
 * @date:2015年12月14日 下午8:40:12 
 * @version V1.0
 * 说明：封装请求参数
 */
public final class RequestHelper {

    /**
     * 创建请求对象
     */
    public static Param createParam(HttpServletRequest request) throws IOException {
    	Map<String, Object> paramMap = parseParameterNames(request);
    	paramMap.putAll(parseInputStream(request));
        return new Param(paramMap) ;
    }

    private static Map<String, Object> parseParameterNames(HttpServletRequest request) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String fieldName = paramNames.nextElement();
            String[] fieldValues = request.getParameterValues(fieldName);
            if (ArrayUtil.isNotEmpty(fieldValues)) {
                Object fieldValue;
                if (fieldValues.length == 1) {
                    fieldValue = fieldValues[0];
                } else {
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < fieldValues.length; i++) {
                        sb.append(fieldValues[i]);
                        if (i != fieldValues.length - 1) {
                            sb.append(StringUtil.SEPARATOR);
                        }
                    }
                    fieldValue = sb.toString();
                }
                paramMap.put(fieldName, fieldValue);
            }
        }
        return paramMap;
    }

    private static Map<String, Object> parseInputStream(HttpServletRequest request) throws IOException {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
        if (StringUtil.isNotEmpty(body)) {
            String[] kvs = StringUtil.splitString(body, "&");
            if (ArrayUtil.isNotEmpty(kvs)) {
                for (String kv : kvs) {
                    String[] array = StringUtil.splitString(kv, "=");
                    if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                        String fieldName = array[0];
                        String fieldValue = array[1];
                        paramMap.put(fieldName, fieldValue);
                    }
                }
            }
        }
        return paramMap;
    }
}
