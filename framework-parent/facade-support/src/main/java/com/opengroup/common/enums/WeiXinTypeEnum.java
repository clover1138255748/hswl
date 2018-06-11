package com.opengroup.common.enums;

/**
 * 微信帐号枚举
 * @author mingzhou.lu
 * @version $Id: WeiXinEnum.java, v 0.1 2017年8月23日 下午6:43:31 mingzhou.lu Exp $
 */
public enum WeiXinTypeEnum {    
        /** 天天运煤 */
        TIANTIAN_TAKE_COAL(0,"wxaff58d87f5ea8e72", "45d6c49a1533cfdd756e1403adb2c84e","TIANTIAN_TAKE_COAL_TOKEN"),
        /** 媒体人*/
        COAL_PERSON(1,"wxd206dfb1fc1cbe98", "e853d2baba68c94d7dfefe776db5441c","COAL_PERSON_TOKEN"),
        /** 天天拉货 */
        CAR_GO_EVERUDAY(2, "wx4ef88d74bb726992","9076a64327d0c273f4e0ea146e25fdce","CAR_GO_EVERUDAY_TOKEN"),
        /** 红狮物流 */
        RED_LION_LOGISTICS(3, "wxd14beed2dd00ad17","c80b13a7bd03f164163fc12628c5b60c","RED_LION_LOGISTICS_TOKEN");
        private int code;
        private String appid;
        private String secret;
        private String remark;
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getAppid() {
            return appid;
        }
        public void setAppid(String appid) {
            this.appid = appid;
        }
        public String getSecret() {
            return secret;
        }
        public void setSecret(String secret) {
            this.secret = secret;
        }
        public String getRemark() {
            return remark;
        }
        public void setRemark(String remark) {
            this.remark = remark;
        }
        private WeiXinTypeEnum(int code, String appid, String secret, String remark) {
            this.code = code;
            this.appid = appid;
            this.secret = secret;
            this.remark = remark;
        }
        
}