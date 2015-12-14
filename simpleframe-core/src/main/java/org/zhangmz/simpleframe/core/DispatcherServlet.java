package org.zhangmz.simpleframe.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.zhangmz.simpleframe.core.bean.Data;
import org.zhangmz.simpleframe.core.bean.Handler;
import org.zhangmz.simpleframe.core.bean.Param;
import org.zhangmz.simpleframe.core.bean.View;
import org.zhangmz.simpleframe.core.helper.*;
import org.zhangmz.simpleframe.core.util.JsonUtil;
import org.zhangmz.simpleframe.core.util.ReflectionUtil;
import org.zhangmz.simpleframe.core.util.StringUtil;

/**
 * 
 * @ClassName:DispatcherServlet 
 * @Description:请求转发器
 * @author:张孟志
 * @date:2015年12月14日 下午8:45:45 
 * @version V1.0
 * 说明：处理所有请求，根据请求信息从ControllerHelper中获取相应的Action方法，
 *      然后通过反射技术调用Action方法，同时需要具体的传入方法参数，最后拿到
 *      返回值并判断其类型，进行相应的处理。
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        HelperLoader.init();

        ServletContext servletContext = servletConfig.getServletContext();

        registerServlet(servletContext);
    }

    private void registerServlet(ServletContext servletContext) {
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/favicon.ico");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.init(request, response);
        try {
        	// 获取请求方法与请求路径
            String requestMethod = request.getMethod().toLowerCase();
            String requestPath = request.getPathInfo();
            // 获取Action处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
            if (handler != null) {
            	// 获取Controller类及其Bean实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);
                // 创建请求参数对象
                Param param = RequestHelper.createParam(request);

                Object result;
                Method actionMethod = handler.getActionMethod();
                if (param.isEmpty()) {
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
                } else {
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
                }

                // Action方法必须返回View/Data
                if (result instanceof View) {
                    handleViewResult((View) result, request, response);
                } else if (result instanceof Data) {
                    handleDataResult((Data) result, response);
                }
            }
        } finally {
            ServletHelper.destroy();
        }
    }

    /**
     * 
     * @Title: handleViewResult 
     * @Description: JSP页面返回
     * @param view
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws 
     * 增加人:张孟志
     * 增加日期:2015年12月14日 下午8:50:01
     * 说明：返回JSP页面
     */
    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
    }

    /**
     * 
     * @Title: handleDataResult 
     * @Description: JSON结果返回
     * @param data
     * @param response
     * @throws IOException
     * @throws 
     * 增加人:张孟志
     * 增加日期:2015年12月14日 下午8:49:21
     * 说明：返回JSON数据
     */
    private void handleDataResult(Data data, HttpServletResponse response) throws IOException {
        Object model = data.getModel();
        if (model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }
}
