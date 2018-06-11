package com.opengroup.common.support;

import java.io.Serializable;

public class TextValuePair implements Serializable {
    private static final long serialVersionUID = -6021087077362655162L;

    private String            value;

    private String            text;

    public TextValuePair() {
        super();
    }

    public TextValuePair(String value, String text) {
        super();
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{'value':'").append(value).append("';'text':'").append(text).append("'}");
        return builder.toString();
    }

}
