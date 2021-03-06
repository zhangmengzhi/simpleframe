package org.zhangmz.simpleframe.joke.service;

import java.util.List;
import java.util.Map;
import org.zhangmz.simpleframe.joke.helper.DatabaseHelper;
import org.zhangmz.simpleframe.joke.model.Customer;

/**
 * 提供客户数据服务
 */
public class CustomerService {

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList() {
    	// 更简单的写法
        // return DatabaseHelper.queryEntityList(Customer.class);
    	
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id) {
    	// 更简单的写法
        // return DatabaseHelper.queryEntity(Customer.class, id);
    	
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    /**
     * 更新客户
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    /**
     * 删除客户
     */
    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}
