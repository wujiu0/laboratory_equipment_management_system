package nuc.ss.dao;

import java.util.List;

import nuc.ss.entity.Equipment;

/**
 * 定义数据库操作方法
 */
public interface EquipmentDAO {
    /**
     * 录入设备
     */
    void insert(Equipment equipment);

    /**
     * 修改设备
     */
    void update(Equipment equipment);

    /**
     * 删除设备
     */
    void remove(String id);

    /**
     * 按照领用人查询
     */
    List<Equipment> queryByUser(String user);

    /**
     * 按设备类型查询
     */
    List<Equipment> queryByType(String type);

    /**
     * 按照借出状态查询
     */
    List<Equipment> queryByLent(boolean lent);

    /**
     * 按照报废状态查询
     */
    List<Equipment> queryByScrap(boolean scrap);

    /**
     * 按照给定参数查询设备
     * 
     * @param key   参数类型
     * @param value 参数值
     * @return
     */
    List<Equipment> query(String key, Object value);

    /**
     * 获取所有设备
     */
    List<Equipment> queryAll();

}
