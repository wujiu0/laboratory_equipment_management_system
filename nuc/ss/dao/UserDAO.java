package nuc.ss.dao;

import java.util.List;

import nuc.ss.entity.User;

public interface UserDAO {

    /**
     * 添加用户
     * 
     * @param user
     */
    void insert(User user);

    /**
     * 修改用户信息
     */
    void update(User user);

    /**
     * 删除用户
     * 
     * @param id
     */
    void remove(String id);

    /**
     * 查询用户信息
     * 
     * @param id
     * @return user对象
     */
    User query(String id);

    /**
     * 获取所有用户
     * 
     * @return 用户列表
     */
    List<User> queryAll();

    /**
     * 验证账号密码是否存在
     * 
     * @param id
     * @param password
     * @return
     */
    boolean check(String id, String password);

}
