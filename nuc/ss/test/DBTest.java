package nuc.ss.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nuc.ss.dao.DAOFactory;
import nuc.ss.dao.EquipmentDAO;
import nuc.ss.entity.Equipment;

/**
 * DBTest
 */
public class DBTest {
    EquipmentDAO equipmentDao = null;

    @Before
    public void init() {
        equipmentDao = DAOFactory.getEquipmentDAO();
    }

    @After
    public void close() {

    }

    @Test
    public void testQuery() {
        List<Equipment> list = equipmentDao.query("lent", "hello");
        for (Equipment equipment : list) {
            System.out.println(equipment);
        }
    }

    @Test
    public void testInsert() {
        Equipment equipment = new Equipment();
        equipment.setId("e0001");
        equipment.setName("实验设备1");
        equipment.setUser("无");
        equipment.setType("类型1");
        equipmentDao.insert(equipment);
    }

    @Test
    public void testQueryByLent() {
        List<Equipment> list = equipmentDao.queryByLent(true);
        for (Equipment equipment : list) {
            System.out.println(equipment);
        }
    }

    @Test
    public void testQueryByuser() {
        List<Equipment> list = equipmentDao.queryByUser(null);
        for (Equipment equipment : list) {
            System.out.println(equipment);
        }
    }

    @Test
    public void testQueryByScrap() {
        List<Equipment> list = equipmentDao.queryByScrap(true);
        for (Equipment equipment : list) {
            System.out.println(equipment);
        }
    }

    @Test
    public void testQueryByType() {
        List<Equipment> list = equipmentDao.queryByType("类型2");
        for (Equipment equipment : list) {
            System.out.println(equipment);
        }
    }

    @Test
    public void testRemove() {
        equipmentDao.remove("e0001");
    }

    @Test
    public void testUpdate() {

    }

}