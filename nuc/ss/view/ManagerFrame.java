package nuc.ss.view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import nuc.ss.dao.DAOFactory;
import nuc.ss.dao.EquipmentDAO;
import nuc.ss.dao.UserDAO;
import nuc.ss.entity.Equipment;
import nuc.ss.entity.User;

public class ManagerFrame extends JFrame {
    public static void main(String[] args) {
        new ManagerFrame(new User());
    }

    User user;
    JLabel l_message, l_queryCondition, l_queryValue;
    JMenuBar menuBar;
    JMenu menu, subMenu;
    JMenuItem item1, item2, item3;
    JTree tree;
    JPanel p_Equipment, p_User;
    JTable jtable;
    JScrollPane jScrollPane;
    DefaultTableModel tModel;
    JComboBox<String> c_queryKey_Equipment;
    JTextField t_queryValue_Equipment;
    JButton b_queryEquipment, b_addEquipment;

    JButton b_addUser;

    public ManagerFrame(User user) {
        this.user = user;
        this.setTitle("实验设备管理系统");
        this.setSize(1200, 900);
        this.setResizable(false);
        this.setLocation(400, 100);
        init(this);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void init(JFrame jFrame) {
        this.setLayout(null);
        menuBar = new JMenuBar();
        menu = new JMenu("菜单");
        subMenu = new JMenu("子菜单");
        item1 = new JMenuItem("菜单项1");
        item2 = new JMenuItem("菜单项2");
        item3 = new JMenuItem("菜单项3");

        menu.add(item1);
        menu.add(subMenu);
        menu.add(item2);
        subMenu.add(item3);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        l_message = new JLabel("欢迎，" + user.getName() + "(" + user.getType() + ")");
        l_message.setFont(l_message.getFont().deriveFont(20.0F));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode equipmentManager = new DefaultMutableTreeNode(DAOFactory.getEquipmentDAO());
        DefaultMutableTreeNode userManager = new DefaultMutableTreeNode(DAOFactory.getUserDAO());
        root.add(equipmentManager);
        root.add(userManager);
        tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setFont(tree.getFont().deriveFont(20.0F));

        p_Equipment = new JPanel(null);
        p_Equipment.setBackground(Color.WHITE);

        l_queryCondition = new JLabel("查询条件：");
        p_Equipment.add(l_queryCondition);
        l_queryCondition.setBounds(70, 10, 100, 30);
        c_queryKey_Equipment = new JComboBox<String>();
        c_queryKey_Equipment.addItem("所有设备");
        c_queryKey_Equipment.addItem("领用人");
        c_queryKey_Equipment.addItem("设备类型");
        c_queryKey_Equipment.addItem("是否借用");
        c_queryKey_Equipment.addItem("是否故障");
        p_Equipment.add(c_queryKey_Equipment);
        c_queryKey_Equipment.setBounds(150, 10, 150, 30);
        l_queryValue = new JLabel("查询值：");
        p_Equipment.add(l_queryValue);
        l_queryValue.setBounds(330, 10, 100, 30);
        t_queryValue_Equipment = new JTextField();
        p_Equipment.add(t_queryValue_Equipment);
        t_queryValue_Equipment.setBounds(400, 10, 100, 30);
        b_queryEquipment = new JButton("查询");
        p_Equipment.add(b_queryEquipment);
        b_queryEquipment.setBounds(520, 10, 100, 30);
        b_addEquipment = new JButton("添加设备");
        p_Equipment.add(b_addEquipment);
        b_addEquipment.setBounds(730, 10, 100, 30);

        tModel = new DefaultTableModel();
        jtable = new JTable(tModel);
        jtable.setFont(jtable.getFont().deriveFont(14F));
        jtable.getTableHeader().setFont(jtable.getFont().deriveFont(16F));
        jtable.getTableHeader().setFont(jtable.getFont().deriveFont(Font.BOLD));
        jtable.setRowHeight(30);
        jtable.setAlignmentX(JTable.CENTER_ALIGNMENT);
        jtable.setAlignmentY(JTable.CENTER_ALIGNMENT);
        jScrollPane = new JScrollPane(jtable);
        p_Equipment.add(jScrollPane);
        // 要先添加再设置size才可生效（原因不明）
        jScrollPane.setBounds(1, 100, 900, 600);

        b_addUser = new JButton("添加成员");
        p_User.add(b_addEquipment);
        b_addUser.setBounds(730, 10, 100, 30);

        this.add(l_message);
        l_message.setBounds(10, 10, 200, 30);
        this.add(tree);
        tree.setBounds(10, 50, 200, 700);
        this.add(p_Equipment);
        p_Equipment.setBounds(230, 50, 900, 700);

        initEquipmentPanel(DAOFactory.getEquipmentDAO());
        tree.addTreeSelectionListener(tse -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            Object obj = node.getUserObject();
            if (obj instanceof UserDAO) {
                initUserPanel(obj);
            }
            if (obj instanceof EquipmentDAO) {
                initEquipmentPanel(obj);
                // DefaultTableModel tModel = new DefaultTableModel(data, columnsNames);
                // jtable = new JTable(tModel);
            }

            // jScrollPane = new JScrollPane(jtable);
            // jFrame.remove(p1);
            // p1.remove(jScrollPane);
            // jScrollPane = new JScrollPane(jtable);
            // jScrollPane.setSize(900, 700);
            // p1.add(jScrollPane);
            // jFrame.add(p1);
        });

        b_queryEquipment.addActionListener(l -> {
            String keyString = c_queryKey_Equipment.getSelectedItem().toString();
            if (keyString.equals("所有设备")) {
                initEquipmentPanel(DAOFactory.getEquipmentDAO());
                return;
            }
            String valueString = t_queryValue_Equipment.getText();
            String key = convertString(keyString);

            Object value = null;
            if (key.equals("lent") || key.equals("scrap")) {
                if (valueString.equals("是") || valueString.equals("否")) {
                    valueString = convertString(valueString);
                    value = Boolean.parseBoolean(valueString);
                } else {
                    JOptionPane.showMessageDialog(this, "请输入是或否");
                    return;
                }
            } else {
                value = valueString;
            }

            List<Equipment> list = DAOFactory.getEquipmentDAO().query(key, value);
            setEquipmentData(list);
        });

        b_addEquipment.addActionListener(l -> new AddEquipmentFrame());

    }

    private void initUserPanel(Object obj) {
        UserDAO userDAO = (UserDAO) obj;
        List<User> dataList = userDAO.queryAll();
        setUserData(dataList);

    }

    private void setUserData(List<User> dataList) {
        String[] columnsNames = { "账号", "密码", "姓名", "性别", "电话", "身份" };
        String[][] data = new String[10][columnsNames.length];
        User u = null;
        for (int i = 0; i < dataList.size(); i++) {
            u = dataList.get(i);
            data[i][0] = u.getId();
            data[i][1] = u.getPassword();
            data[i][2] = u.getName();
            data[i][3] = u.getSex();
            data[i][4] = u.getPhoneNumber();
            data[i][5] = u.getType();
        }
        tModel.setDataVector(data, columnsNames);
    }

    private void initEquipmentPanel(Object obj) {
        EquipmentDAO equipmentDAO = (EquipmentDAO) obj;
        List<Equipment> dataList = equipmentDAO.queryAll();
        setEquipmentData(dataList);
    }

    private void setEquipmentData(List<Equipment> dataList) {
        String[] columnsNames = { "设备编号", "设备名称", "领用人", "设备类型", "是否借用", "是否故障" };
        String[][] data = new String[10][columnsNames.length];
        Equipment e = null;
        for (int i = 0; i < dataList.size(); i++) {
            e = dataList.get(i);
            data[i][0] = e.getId();
            data[i][1] = e.getName();
            data[i][2] = e.getUser();
            data[i][3] = e.getType();
            data[i][4] = convertBoolean(e.isLent());
            data[i][5] = convertBoolean(e.isScrap());
        }

        tModel.setDataVector(data, columnsNames);
    }

    private static String convertString(String key) {
        switch (key) {
            case "领用人":
                key = "user";
                break;
            case "设备类型":
                key = "type";
                break;
            case "是否借用":
                key = "lent";
                break;
            case "是否故障":
                key = "scrap";
                break;
            case "是":
                key = "true";
                break;
            case "否":
                key = "false";
                break;

        }
        return key;
    }

    private static String convertBoolean(boolean b) {
        String str = b + "";
        switch (str) {
            case "true":
                str = "是";
                break;
            case "false":
                str = "否";
                break;
        }
        return str;
    }
}
