package nuc.ss.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import nuc.ss.dao.DAOFactory;
import nuc.ss.dao.EquipmentDAO;
import nuc.ss.dao.UserDAO;
import nuc.ss.entity.Equipment;
import nuc.ss.entity.User;

public class ManagerFrame extends JFrame {

    private User user;
    private JLabel l_message, l_queryCondition, l_queryValue;
    // private JMenuBar menuBar;
    // private JMenu menu, subMenu;
    // private JMenuItem item1, item2, item3;
    private JTree tree;
    private JPanel p_main, p_Equipment, p_User;
    private JTable jtable_equipment, jtable_user;
    private JScrollPane jScrollPane;
    private DefaultTableModel tModel_equipment, tModel_User;
    private JComboBox<String> c_queryKey_Equipment;
    private JTextField t_queryValue_Equipment;
    private JButton b_queryEquipment, b_addEquipment, b_deleteEquipment;
    private JButton b_addUser, b_deleteUser;

    public ManagerFrame(User user) {
        this.user = user;
        this.setTitle("实验设备管理系统");
        this.setSize(1200, 850);
        this.setResizable(false);
        this.setLocation(400, 100);
        init(this);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void init(JFrame jFrame) {
        this.setLayout(null);
        // menuBar = new JMenuBar();
        // menu = new JMenu("菜单");
        // subMenu = new JMenu("子菜单");
        // item1 = new JMenuItem("菜单项1");
        // item2 = new JMenuItem("菜单项2");
        // item3 = new JMenuItem("菜单项3");

        // menu.add(item1);
        // menu.add(subMenu);
        // menu.add(item2);
        // subMenu.add(item3);
        // menuBar.add(menu);
        // this.setJMenuBar(menuBar);

        l_message = new JLabel("欢迎，" + user.getName() + "(" + user.getType() + ")");
        l_message.setFont(l_message.getFont().deriveFont(20.0F));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode equipmentManager = new DefaultMutableTreeNode(DAOFactory.getEquipmentDAO());
        DefaultMutableTreeNode userManager = new DefaultMutableTreeNode(DAOFactory.getUserDAO());
        root.add(equipmentManager);
        root.add(userManager);
        tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setFont(tree.getFont().deriveFont(22.0F));
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                    boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                String str = value.toString();
                if (str.equals("设备管理")) {
                    this.setIcon(new ImageIcon("src\\imgs\\equipment.png"));
                } else if (str.equals("成员管理")) {
                    this.setIcon(new ImageIcon("src\\imgs\\user.png"));
                }
                return this;
            }
        });

        p_Equipment = new JPanel(null);
        p_Equipment.setBackground(Color.WHITE);
        p_User = new JPanel(null);
        p_User.setBackground(Color.WHITE);

        l_queryCondition = new JLabel("查询条件：");
        p_Equipment.add(l_queryCondition);
        l_queryCondition.setBounds(70, 30, 100, 30);
        c_queryKey_Equipment = new JComboBox<String>();
        c_queryKey_Equipment.addItem("所有设备");
        c_queryKey_Equipment.addItem("领用人");
        c_queryKey_Equipment.addItem("设备类型");
        c_queryKey_Equipment.addItem("是否借用");
        c_queryKey_Equipment.addItem("是否故障");
        p_Equipment.add(c_queryKey_Equipment);
        c_queryKey_Equipment.setBounds(150, 30, 150, 30);
        l_queryValue = new JLabel("查询值：");
        p_Equipment.add(l_queryValue);
        l_queryValue.setBounds(330, 30, 100, 30);
        t_queryValue_Equipment = new JTextField();
        p_Equipment.add(t_queryValue_Equipment);
        t_queryValue_Equipment.setBounds(400, 30, 100, 30);
        b_queryEquipment = new JButton("查询");
        p_Equipment.add(b_queryEquipment);
        b_queryEquipment.setBounds(520, 30, 100, 30);
        b_addEquipment = new JButton("添加设备");
        p_Equipment.add(b_addEquipment);
        b_addEquipment.setBounds(730, 10, 100, 30);
        b_deleteEquipment = new JButton("删除设备");
        p_Equipment.add(b_deleteEquipment);
        b_deleteEquipment.setBounds(730, 50, 100, 30);
        /**
         * 创建设备信息的表格，设置第一列不可编辑
         */
        tModel_equipment = new DefaultTableModel();
        jtable_equipment = new JTable(tModel_equipment) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return true;
            }
        };
        jtable_equipment.setFont(jtable_equipment.getFont().deriveFont(14F));
        jtable_equipment.getTableHeader().setFont(jtable_equipment.getFont().deriveFont(16F));
        jtable_equipment.getTableHeader().setFont(jtable_equipment.getFont().deriveFont(Font.BOLD));
        jtable_equipment.setRowHeight(30);
        jtable_equipment.setAlignmentX(JTable.CENTER_ALIGNMENT);
        jtable_equipment.setAlignmentY(JTable.CENTER_ALIGNMENT);
        jScrollPane = new JScrollPane(jtable_equipment);
        p_Equipment.add(jScrollPane);
        // 要先添加再设置size才可生效（原因不明）
        jScrollPane.setBounds(1, 100, 900, 600);

        b_addUser = new JButton("添加成员");
        p_User.add(b_addUser);
        b_addUser.setBounds(230, 30, 100, 30);
        b_deleteUser = new JButton("删除成员");
        p_User.add(b_deleteUser);
        b_deleteUser.setBounds(530, 30, 100, 30);
        tModel_User = new DefaultTableModel();
        jtable_user = new JTable(tModel_User);
        jtable_user.setFont(jtable_user.getFont().deriveFont(14F));
        jtable_user.getTableHeader().setFont(jtable_user.getFont().deriveFont(16F));
        jtable_user.getTableHeader().setFont(jtable_user.getFont().deriveFont(Font.BOLD));
        jtable_user.setRowHeight(30);
        jtable_user.setAlignmentX(JTable.CENTER_ALIGNMENT);
        jtable_user.setAlignmentY(JTable.CENTER_ALIGNMENT);
        jScrollPane = new JScrollPane(jtable_user);
        p_User.add(jScrollPane);
        jScrollPane.setBounds(1, 100, 900, 600);

        this.add(l_message);
        l_message.setBounds(10, 10, 200, 30);
        this.add(tree);
        tree.setBounds(10, 50, 200, 700);
        p_main = p_Equipment;
        this.add(p_main);
        p_main.setBounds(230, 50, 900, 700);

        initEquipmentPanel(DAOFactory.getEquipmentDAO());

        tree.addTreeSelectionListener(tse -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            Object obj = node.getUserObject();
            if (obj instanceof UserDAO) {
                initUserPanel(obj);
                jFrame.remove(p_main);
                p_main = p_User;
                this.add(p_main);
                p_main.repaint();
                p_main.setBounds(230, 50, 900, 700);
            }
            if (obj instanceof EquipmentDAO) {
                initEquipmentPanel(obj);
                jFrame.remove(p_main);
                p_main = p_Equipment;
                this.add(p_main);
                p_main.repaint();
                p_main.setBounds(230, 50, 900, 700);
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

        /**
         * 查询设备按钮的监听器
         */
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
        b_deleteEquipment.addActionListener(l -> {
            int[] rows = jtable_equipment.getSelectedRows();
            if (rows.length == 0) {
                JOptionPane.showMessageDialog(this, "请选中一行设备再点击删除");
                return;
            }
            for (int i = 0; i < rows.length; i++) {
                String id = tModel_equipment.getValueAt(rows[i], 0).toString();
                DAOFactory.getEquipmentDAO().remove(id);
            }
            JOptionPane.showMessageDialog(this, "删除成功");
        });
        b_addUser.addActionListener(l -> new AddUserFrame());
        b_deleteUser.addActionListener(l -> {
            int row = jtable_user.getSelectedRow();
            String id = tModel_User.getValueAt(row, 0).toString();
            if (id.equals(user.getId())) {
                JOptionPane.showMessageDialog(this, "操作失败，不可删除自己");
                return;
            }
            DAOFactory.getUserDAO().remove(id);
            JOptionPane.showMessageDialog(this, "删除成功");
        });

        /**
         * 修改表格内容的监听器
         */
        tModel_equipment.addTableModelListener(l -> {
            int firstRow = l.getFirstRow();
            if (firstRow == -1) {
                return;
            }
            int lastRow = l.getLastRow();
            int column = l.getColumn();
            if (column == 0) {
                JOptionPane.showMessageDialog(this, "设备编号不可变");
                return;
            }
            int eventType = l.getType();
            if (eventType == TableModelEvent.UPDATE) {
                for (int row = firstRow; row <= lastRow; row++) {
                    Object idObj = tModel_equipment.getValueAt(row, 0);
                    Object nameObj = tModel_equipment.getValueAt(row, 1);
                    Object userObj = tModel_equipment.getValueAt(row, 2);
                    Object typeObj = tModel_equipment.getValueAt(row, 3);
                    Object lentObj = tModel_equipment.getValueAt(row, 4);
                    Object scrapObj = tModel_equipment.getValueAt(row, 5);

                    String id = idObj.toString();
                    // 把对象值转换为数值
                    String name = nameObj.toString();
                    String user = userObj.toString();
                    String type = typeObj.toString();

                    String lentStr = lentObj.toString();
                    boolean lent = false;
                    if (lentStr.equals("是") || lentStr.equals("否")) {
                        lentStr = convertString(lentStr);
                        lent = Boolean.parseBoolean(lentStr);
                    } else {
                        JOptionPane.showMessageDialog(this, "是否借用只能填是或否");
                        return;
                    }

                    String scrapStr = scrapObj.toString();
                    boolean scrap = false;
                    if (scrapStr.equals("是") || scrapStr.equals("否")) {
                        scrapStr = convertString(scrapStr);
                        scrap = Boolean.parseBoolean(scrapStr);
                    } else {
                        JOptionPane.showMessageDialog(this, "是否故障只能填是或否");
                        return;
                    }

                    // System.out.println(id);
                    Equipment equipment = DAOFactory.getEquipmentDAO().queryById(id);
                    equipment.setName(name);
                    equipment.setUser(user);
                    equipment.setType(type);
                    equipment.setLent(lent);
                    equipment.setScrap(scrap);
                    DAOFactory.getEquipmentDAO().update(equipment);
                    JOptionPane.showMessageDialog(this, "修改成功");
                }
            }
        });

        tModel_User.addTableModelListener(l -> {
            int firstRow = l.getFirstRow();
            if (firstRow == -1) {
                return;
            }
            int lastRow = l.getLastRow();
            int column = l.getColumn();
            if (column == 0) {
                JOptionPane.showMessageDialog(this, "账号不可修改");
                return;
            }
            int eventType = l.getType();
            if (eventType == TableModelEvent.UPDATE) {
                for (int row = firstRow; row <= lastRow; row++) {
                    Object idObj = tModel_User.getValueAt(row, 0);
                    Object passwordObj = tModel_User.getValueAt(row, 1);
                    Object nameObj = tModel_User.getValueAt(row, 2);
                    Object sexObj = tModel_User.getValueAt(row, 3);
                    Object phoneObj = tModel_User.getValueAt(row, 4);
                    Object typeObj = tModel_User.getValueAt(row, 5);

                    String id = idObj.toString();
                    // 把对象值转换为数值
                    String password = passwordObj.toString();
                    String name = nameObj.toString();
                    String sex = sexObj.toString();
                    String phoneNumber = phoneObj.toString();
                    String type = typeObj.toString();
                    if (!(sex.equals("男") || sex.equals("女"))) {
                        JOptionPane.showMessageDialog(this, "性别只能为男或女");
                        return;
                    }
                    if (!(type.equals("管理员") || type.equals("普通用户"))) {
                        JOptionPane.showMessageDialog(this, "身份设置错误，只能为管理员或者普通用户");
                        return;
                    }
                    User user = DAOFactory.getUserDAO().query(id);
                    user.setPassword(password);
                    user.setName(name);
                    user.setSex(sex);
                    user.setPhoneNumber(phoneNumber);
                    user.setType(type);
                    // System.out.println(id);
                    DAOFactory.getUserDAO().update(user);
                    JOptionPane.showMessageDialog(this, "修改成功");
                }
            }
        });
    }

    private void initUserPanel(Object obj) {
        UserDAO userDAO = (UserDAO) obj;
        List<User> dataList = userDAO.queryAll();
        setUserData(dataList);
    }

    private void setUserData(List<User> dataList) {
        String[] columnsNames = { "账号", "密码", "姓名", "性别", "电话", "身份" };
        String[][] data = new String[dataList.size()][columnsNames.length];
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
        tModel_User.setDataVector(data, columnsNames);
    }

    private void initEquipmentPanel(Object obj) {
        EquipmentDAO equipmentDAO = (EquipmentDAO) obj;
        List<Equipment> dataList = equipmentDAO.queryAll();
        setEquipmentData(dataList);
        jtable_equipment.getColumn("是否借用").setCellRenderer(new MyTableCellRenderer_lent());
        jtable_equipment.getColumn("是否故障").setCellRenderer(new MyTableCellRenderer_scrap());
    }

    private void setEquipmentData(List<Equipment> dataList) {
        String[] columnsNames = { "设备编号", "设备名称", "领用人", "设备类型", "是否借用", "是否故障" };
        String[][] data = new String[dataList.size()][columnsNames.length];
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
        tModel_equipment.setDataVector(data, columnsNames);
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

    public static class MyTableCellRenderer_lent extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {

            String lentStr = table.getModel().getValueAt(row, column).toString();
            if (lentStr.equals("是")) {
                this.setForeground(Color.BLUE);
            } else {
                this.setForeground(Color.BLACK);
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    public static class MyTableCellRenderer_scrap extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {

            String lentStr = table.getModel().getValueAt(row, column).toString();
            if (lentStr.equals("是")) {
                // this.setText("<html><font color='red'>是1</font>");
                this.setForeground(Color.RED);
            } else {
                this.setForeground(Color.BLACK);
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
