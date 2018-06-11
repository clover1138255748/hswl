package com.opengroup.wl.enums;

/**
 * 物流平台加油机油品类型
 * 
 * @author jys
 * @version $Id: OilTypeEnum.java, v 0.1 2018年4月28日 上午10:28:20 jys Exp $
 */
public enum OilTypeEnum {

    OIL_0_DIESEL(21, "0#柴油"), OIL_NEGATIVE_10_DIESEL(23, "-10#柴油");
    /**
     * code
     */
    private int    value;

    /**
     * 描述
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
