package nuc.ss.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import nuc.ss.dao.DAOFactory;
import nuc.ss.dao.EquipmentDAO;
import nuc.ss.entity.Equipment;

public class AddEquipmentFrame extends JFrame {

    private JLabel l_id, l_name, l_type;
    private JTextField t_id, t_name, t_type;
    private JButton b_add, b_reset;

    public AddEquipmentFrame() {
        setSize(400, 400);
        setLocation(750, 300);
        setTitle("添加设备");
        setResizable(false);
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void init() {
        setLayout(new GridLayout(5, 2, 5, 5));

        l_id = new JLabel("设备编号", JLabel.CENTER);
        l_name = new JLabel("设备名称", JLabel.CENTER);
        l_type = new JLabel("设备类型", JLabel.CENTER);
        t_id = new JTextField();
        t_name = new JTextField();
        t_type = new JTextField();

        t_type = new JTextField();
        b_add = new JButton("添加");
        b_reset = new JButton("重置");

        add(l_id);
        add(t_id);
        add(l_name);
        add(t_name);
        add(l_type);
        add(t_type);
        add(new JLabel());
        add(new JLabel());
        add(b_add);
        add(b_reset);

        b_add.addActionListener(e -> {
            String id = t_id.getText();
            String name = t_name.getText();
            String type = t_type.getText();
            if (id.length() > 5) {
                JOptionPane.showMessageDialog(this, "设备编号长度不可以超过5位");
                return;
            }
            // // 判断输入是否为空
            if (isEmpty(id, name, type)) {
                return;
            }

            Equipment equipment = new Equipment();
            equipment.setId(id);
            equipment.setName(name);
            equipment.setType(type);
            addEquipment(equipment);
        });

        b_reset.addActionListener(e -> {
            t_id.setText("");
            t_name.setText("");
            t_type.setText("");
        });
    }

    private void addEquipment(Equipment equipment) {
        EquipmentDAO equipmentDAO = DAOFactory.getEquipmentDAO();
        // 判断设备是否存在
        List<Equipment> equipmentList = equipmentDAO.query("id", equipment.getId());
        if (!equipmentList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "该设备编号已存在", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        equipmentDAO.insert(equipment);
        JOptionPane.showMessageDialog(this, "添加成功");
    }

    private boolean isEmpty(String id, String name, String type) {
        if (id.equals("")) {
            JOptionPane.showMessageDialog(this, "设备编号不能为空", "错误提示", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "设备名称不能为空", "错误提示", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if (type.equals("")) {
            JOptionPane.showMessageDialog(this, "设备类型不能为空", "错误提示", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

}
