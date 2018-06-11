package com.opengroup.common.connect;

/**
 * ��ͨ��ʽ 1.�绰 2��΢�� 3��qq 4������ 99������
 * @author David
 * @version $Id: ConnectionType.java, v 0.1 2016��12��17�� ����5:41:06 David Exp $
 */
public enum ConnectionType {

    PHONE(1, "�绰"),

    WE_CHAT(2, "΢��"),

    QQ(3, "QQ"),

    MESSAGE(4, "����"),

    OTHER(99, "����");

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
