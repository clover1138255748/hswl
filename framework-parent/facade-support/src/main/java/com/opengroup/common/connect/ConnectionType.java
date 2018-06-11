package com.opengroup.common.connect;

/**
 * 沟通方式 1.电话 2：微信 3：qq 4：短信 99：其他
 * @author David
 * @version $Id: ConnectionType.java, v 0.1 2016年12月17日 下午5:41:06 David Exp $
 */
public enum ConnectionType {

    PHONE(1, "电话"),

    WE_CHAT(2, "微信"),

    QQ(3, "QQ"),

    MESSAGE(4, "短信"),

    OTHER(99, "其他");

    private int type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    private ConnectionType(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static ConnectionType prase(int type) {
        for (ConnectionType item : ConnectionType.values()) {
            if (item.getType() == type) {
                return item;
            }
        }
        return null;
    }

}
