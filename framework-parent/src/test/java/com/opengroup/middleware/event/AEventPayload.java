package com.opengroup.middleware.event;

import java.io.Serializable;

public class AEventPayload implements Serializable {

    /**  */
    private static final long serialVersionUID = 4631710682811167368L;

    private String            testValue;

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

}
