package nuc.ss.entity;

public class User {
    private String id;
    private String password = "123456";
    private String name;
    private String sex;
    private String phoneNumber;
    private String type = "普通成员";

    public User() {
    }

    public User(String id, String password, String name, String sex, String phoneNumber, String type) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", phoneNumber=" + phoneNumber
                + ", sex=" + sex + ", type=" + type + "]";
    }

}
