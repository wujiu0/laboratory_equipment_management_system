package nuc.ss.view;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import nuc.ss.dao.DAOFactory;
import nuc.ss.dao.UserDAO;
import nuc.ss.entity.User;

public class AddUserFrame extends JFrame {

    public static void main(String[] args) {
        new AddUserFrame();
    }

    private JLabel l_id, l_name, l_phoneNumber, l_sex, l_type;
    private JTextField t_id, t_name, t_phoneNumber;
    private JRadioButton r_sex1, r_sex2;
    private JComboBox<String> c_type;
    private JButton b_add, b_reset;

    public AddUserFrame() {
        setSize(400, 500);
        setLocation(750, 300);
        setTitle("添加成员");
        setResizable(false);
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void init() {
        setLayout(new GridLayout(7, 2, 5, 5));

        l_id = new JLabel("账号", JLabel.CENTER);
        l_name = new JLabel("姓名", JLabel.CENTER);
        l_sex = new JLabel("性别", JLabel.CENTER);
        l_phoneNumber = new JLabel("电话号码", JLabel.CENTER);
        l_type = new JLabel("身份", JLabel.CENTER);
        t_id = new JTextField();
        t_name = new JTextField();
        t_phoneNumber = new JTextField();

        r_sex1 = new JRadioButton("男", true);
        r_sex2 = new JRadioButton("女");
        ButtonGroup bg_sex = new ButtonGroup();
        bg_sex.add(r_sex1);
        bg_sex.add(r_sex2);
        c_type = new JComboBox<>();
        c_type.addItem("普通成员");
        c_type.addItem("管理员");
        b_add = new JButton("添加");
        b_reset = new JButton("重置");

        add(l_id);
        add(t_id);
        add(l_name);
        add(t_name);
        add(l_sex);
        add(new JPanel() {
            {
                setLayout(new GridLayout(1, 2, 5, 5));
                add(r_sex1);
                add(r_sex2);
            }

        });
        add(l_phoneNumber);
        add(t_phoneNumber);
        add(l_type);
        add(c_type);
        add(new JLabel());
        add(new JLabel());
        add(b_add);
        add(b_reset);

        b_add.addActionListener(e -> {
            String id = t_id.getText();
            String name = t_name.getText();
            String phoneNumber = t_phoneNumber.getText();
            String sex = "";
            if (r_sex1.isSelected()) {
                sex = "男";
            } else if (r_sex2.isSelected()) {
                sex = "女";
            }
            String type = (String) c_type.getSelectedItem();

            // // 判断输入是否为空
            if (isEmpty(id, name, phoneNumber)) {
                return;
            }

            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setSex(sex);
            user.setPhoneNumber(phoneNumber);
            user.setType(type);
            addUser(user);
        });

        b_reset.addActionListener(e -> {
            t_id.setText("");
            t_name.setText("");
            t_phoneNumber.setText("");
        });
    }

    private boolean isEmpty(String id, String name, String phoneNumber) {
        if (id.equals("")) {
            JOptionPane.showMessageDialog(this, "账号不能为空", "错误提示", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "姓名不能为空", "错误提示", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if (phoneNumber.equals("")) {
            JOptionPane.showMessageDialog(this, "电话不能为空", "错误提示", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    private void addUser(User user) {
        UserDAO userDao = DAOFactory.getUserDAO();

        // 判断账号是否已经存在
        User result = userDao.query(user.getId());
        if (result != null) {
            JOptionPane.showMessageDialog(this, "该账号已存在", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        userDao.insert(user);
    }
}
