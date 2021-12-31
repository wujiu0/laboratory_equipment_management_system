package nuc.ss.entity;

public class Equipment {
    private String id;// 设备编号
    private String name;// 设备名称
    private String user = "无"; // 领用人
    private String type;// 设备类型
    private boolean lent = false; // 设备状态（是否借出）
    private boolean scrap = false; // 是否是报废设备

    public Equipment() {
    }

    public Equipment(String id, String name, String user, String type, boolean lent, boolean scrap) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.type = type;
        this.lent = lent;
        this.scrap = scrap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLent() {
        return lent;
    }

    public void setLent(boolean lent) {
        this.lent = lent;
    }

    public boolean isScrap() {
        return scrap;
    }

    public void setScrap(boolean scrap) {
        this.scrap = scrap;
    }

    @Override
    public String toString() {
        return "Equipment [id=" + id + ", lent=" + lent + ", name=" + name + ", user=" + user + ", scrap="
                + scrap + ", type=" + type + "]";
    }

}
