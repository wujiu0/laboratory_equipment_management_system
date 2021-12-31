package nuc.ss.dao;

/**
 * DAO工厂类，用来获取DAO接口的实现类
 */
public class DAOFactory {
    public static EquipmentDAO getEquipmentDAO() {
        return new EquipmentDAOImpl();
    }

    public static UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
}
