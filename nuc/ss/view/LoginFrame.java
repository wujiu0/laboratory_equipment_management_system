package nuc.ss.view;

import javax.swing.*;

import nuc.ss.dao.DAOFactory;
import nuc.ss.dao.UserDAO;
import nuc.ss.entity.User;

public class LoginFrame extends JFrame {
    private JLabel l_id, l_password;
    private JTextField t_id;
    private JPasswordField t_password;
    private JButton b_login, b_reset;

    public LoginFrame() {
        setSize(300, 250);
        setLocation(800, 400);
        setTitle("用户登录");
        setResizable(false);// 设置窗口大小不可变
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置用户在此窗体上发起 "close" 时默认执行的操作

    }

    private void init() {
        this.setLayout(null);

        l_id = new JLabel("账号: ", JLabel.CENTER);
        l_password = new JLabel("密码: ", JLabel.CENTER);
        t_id = new JTextField();
        t_password = new JPasswordField();
        b_login = new JButton("登录");
        b_reset = new JButton("重置");

        this.add(l_id);
        l_id.setBounds(25, 10, 100, 50);
        this.add(t_id);
        t_id.setBounds(120, 15, 120, 30);
        this.add(l_password);
        l_password.setBounds(25, 60, 100, 50);
        this.add(t_password);
        t_password.setBounds(120, 65, 120, 30);
        this.add(b_login);
        b_login.setBounds(40, 140, 80, 30);
        this.add(b_reset);
        b_reset.setBounds(180, 140, 80, 30);

        b_login.addActionListener(e -> {
            String id = t_id.getText();
            String password = new String(t_password.getPassword());
            if (id.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(this, "用户名或密码为空");
                return;
            }
            boolean result = login(id, password);
            if (result) {
                dispose();
            }
        });
        b_reset.addActionListener(e -> {
            t_id.setText("");
            t_password.setText("");
        });
        setVisible(true);
    }

    public boolean login(String id, String password) {
        UserDAO userDao = DAOFactory.getUserDAO();
        boolean flag = userDao.check(id, password);

        if (flag) {
            User user = userDao.query(id);
            if (user.getType().equals("管理员")) {
                new ManagerFrame(user);
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "请获取管理员身份");
            }
        } else {
            JOptionPane.showMessageDialog(b_login, "用户名或密码错误", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }

}
