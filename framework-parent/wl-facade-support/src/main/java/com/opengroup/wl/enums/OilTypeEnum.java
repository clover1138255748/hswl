package com.opengroup.wl.enums;

/**
 * ����ƽ̨���ͻ���Ʒ����
 * 
 * @author jys
 * @version $Id: OilTypeEnum.java, v 0.1 2018��4��28�� ����10:28:20 jys Exp $
 */
public enum OilTypeEnum {

    OIL_0_DIESEL(21, "0#����"), OIL_NEGATIVE_10_DIESEL(23, "-10#����");
    /**
     * code
     */
    private int    value;

    /**
     * ����
     */
    private String description;

    private OilTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int valueOf() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static OilTypeEnum parse(int value) {
        for (OilTypeEnum item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        return null;
    }

}
