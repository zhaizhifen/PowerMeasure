package speedata.com.powermeasure.bean;

import java.util.List;

/**
 * Created by 张明_ on 2016/8/11.
 */
public class AlListClass {


    /**
     * RT_LIST : [{"WO_STATUS_ID":"05","UNIT_NAME":"自动化仓储系统01号库","ALARM_TIME":"2016-09-09 10:37:29","EQUIP_NO":"1501303001001133000000","DEVICE_NAME":"自动化仓储系统01号库滚筒线","ALARM_CAUSE_NAME":"任务路径写入错误","EQUIP_NAME":"自动化仓储系统01号库001楼滚筒线133号输送单元","ALARM_LEVEL":"严重","WORK_ORDER_NO":"D2016090910212000490","WO_STATUS":"待反馈","ALARM_LEVEL_ID":"01"},{"WO_STATUS_ID":"07","UNIT_NAME":"自动化仓储系统01号库","ALARM_TIME":"2016-09-09 10:37:29","EQUIP_NO":"1501303001001133000000","DEVICE_NAME":"自动化仓储系统01号库滚筒线","ALARM_CAUSE_NAME":"任务路径写入错误","EQUIP_NAME":"自动化仓储系统01号库001楼滚筒线133号输送单元","ALARM_LEVEL":"严重","WORK_ORDER_NO":"D2016090910212000488","WO_STATUS":"待维修","ALARM_LEVEL_ID":"01"},{"WO_STATUS_ID":"05","UNIT_NAME":"自动化仓储系统01号库","ALARM_TIME":"2016-09-09 10:37:45","EQUIP_NO":"1501303001004004000000","DEVICE_NAME":"自动化仓储系统01号库滚筒线","ALARM_CAUSE_NAME":"条码枪扫描异常","EQUIP_NAME":"自动化仓储系统01号库001楼滚筒线004号条码阅读器","ALARM_LEVEL":"严重","WORK_ORDER_NO":"D2016090910212000489","WO_STATUS":"待反馈","ALARM_LEVEL_ID":"01"},{"WO_STATUS_ID":"05","UNIT_NAME":"自动化仓储系统01号库","ALARM_TIME":"2016-09-09 10:38:00","EQUIP_NO":"1501303001001133000000","DEVICE_NAME":"自动化仓储系统01号库滚筒线","ALARM_CAUSE_NAME":"接货超时","EQUIP_NAME":"自动化仓储系统01号库001楼滚筒线133号输送单元","ALARM_LEVEL":"严重","WORK_ORDER_NO":"D2016090910212000490","WO_STATUS":"待反馈","ALARM_LEVEL_ID":"01"},{"WO_STATUS_ID":"07","UNIT_NAME":"自动化仓储系统01号库","ALARM_TIME":"2016-09-09 10:38:00","EQUIP_NO":"1501303001001133000000","DEVICE_NAME":"自动化仓储系统01号库滚筒线","ALARM_CAUSE_NAME":"接货超时","EQUIP_NAME":"自动化仓储系统01号库001楼滚筒线133号输送单元","ALARM_LEVEL":"严重","WORK_ORDER_NO":"D2016090910212000488","WO_STATUS":"待维修","ALARM_LEVEL_ID":"01"},{"WO_STATUS_ID":"05","UNIT_NAME":"自动化仓储系统01号库","ALARM_TIME":"2016-09-09 14:51:47","EQUIP_NO":"1501303003001025000000","DEVICE_NAME":"自动化仓储系统01号库滚筒线","ALARM_CAUSE_NAME":"任务路径异常","EQUIP_NAME":"自动化仓储系统01号库003楼滚筒线025号输送单元","ALARM_LEVEL":"严重","WORK_ORDER_NO":"D2016090910212000502","WO_STATUS":"待反馈","ALARM_LEVEL_ID":"01"},{"WO_STATUS_ID":"05","UNIT_NAME":"自动化仓储系统01号库","ALARM_TIME":"2016-09-09 14:52:22","EQUIP_NO":"1501303003001025000000","DEVICE_NAME":"自动化仓储系统01号库滚筒线","ALARM_CAUSE_NAME":"任务路径异常","EQUIP_NAME":"自动化仓储系统01号库003楼滚筒线025号输送单元","ALARM_LEVEL":"严重","WORK_ORDER_NO":"D2016090910212000502","WO_STATUS":"待反馈","ALARM_LEVEL_ID":"01"},{"WO_STATUS_ID":"05","UNIT_NAME":"自动化仓储系统01号库","ALARM_TIME":"2016-09-09 10:47:44","EQUIP_NO":"1501303001001133000000","DEVICE_NAME":"自动化仓储系统01号库滚筒线","ALARM_CAUSE_NAME":"接货超时","EQUIP_NAME":"自动化仓储系统01号库001楼滚筒线133号输送单元","ALARM_LEVEL":"严重","WORK_ORDER_NO":"D2016090910212000490","WO_STATUS":"待反馈","ALARM_LEVEL_ID":"01"},{"WO_STATUS_ID":"07","UNIT_NAME":"自动化仓储系统01号库","ALARM_TIME":"2016-09-09 10:47:44","EQUIP_NO":"1501303001001133000000","DEVICE_NAME":"自动化仓储系统01号库滚筒线","ALARM_CAUSE_NAME":"接货超时","EQUIP_NAME":"自动化仓储系统01号库001楼滚筒线133号输送单元","ALARM_LEVEL":"严重","WORK_ORDER_NO":"D2016090910212000488","WO_STATUS":"待维修","ALARM_LEVEL_ID":"01"}]
     * RT_F : 1
     * RT_D : 系统接口提示,报警工单查询成功!
     */

    private String RT_F;
    private String RT_D;
    /**
     * WO_STATUS_ID : 05
     * UNIT_NAME : 自动化仓储系统01号库
     * ALARM_TIME : 2016-09-09 10:37:29
     * EQUIP_NO : 1501303001001133000000
     * DEVICE_NAME : 自动化仓储系统01号库滚筒线
     * ALARM_CAUSE_NAME : 任务路径写入错误
     * EQUIP_NAME : 自动化仓储系统01号库001楼滚筒线133号输送单元
     * ALARM_LEVEL : 严重
     * WORK_ORDER_NO : D2016090910212000490
     * WO_STATUS : 待反馈
     * ALARM_LEVEL_ID : 01
     */

    private List<RTLISTBean> RT_LIST;
    private List<DYLISTBean> DY_LIST;

    public List<DYLISTBean> getDY_LIST() {
        return DY_LIST;
    }

    public void setDY_LIST(List<DYLISTBean> DY_LIST) {
        this.DY_LIST = DY_LIST;
    }

    public String getRT_F() {
        return RT_F;
    }

    public void setRT_F(String RT_F) {
        this.RT_F = RT_F;
    }

    public String getRT_D() {
        return RT_D;
    }

    public void setRT_D(String RT_D) {
        this.RT_D = RT_D;
    }

    public List<RTLISTBean> getRT_LIST() {
        return RT_LIST;
    }

    public void setRT_LIST(List<RTLISTBean> RT_LIST) {
        this.RT_LIST = RT_LIST;
    }
    public static class DYLISTBean {
        private String SYS_NO;
        private String CNT;

        public String getSYS_NO() {
            return SYS_NO;
        }

        public void setSYS_NO(String SYS_NO) {
            this.SYS_NO = SYS_NO;
        }

        public String getCNT() {
            return CNT;
        }

        public void setCNT(String CNT) {
            this.CNT = CNT;
        }
    }
    public static class RTLISTBean {
        private String WO_STATUS_ID;
        private String UNIT_NAME;
        private String ALARM_TIME;
        private String EQUIP_NO;
        private String DEVICE_NAME;
        private String ALARM_CAUSE_NAME;
        private String EQUIP_NAME;
        private String ALARM_LEVEL;
        private String WORK_ORDER_NO;
        private String WO_STATUS;
        private String ALARM_LEVEL_ID;
        private String SYS_NO;
        private String PLAN_BGN_TIME;
        private String PLAN_END_TIME;
        private String SECFLAG;


        public String getSECFLAG() {
            return SECFLAG;
        }

        public void setSECFLAG(String SECFLAG) {
            this.SECFLAG = SECFLAG;
        }

        public String getSYS_NO() {
            return SYS_NO;
        }

        public void setSYS_NO(String SYS_NO) {
            this.SYS_NO = SYS_NO;
        }

        public String getPLAN_BGN_TIME() {
            return PLAN_BGN_TIME;
        }

        public void setPLAN_BGN_TIME(String PLAN_BGN_TIME) {
            this.PLAN_BGN_TIME = PLAN_BGN_TIME;
        }

        public String getPLAN_END_TIME() {
            return PLAN_END_TIME;
        }

        public void setPLAN_END_TIME(String PLAN_END_TIME) {
            this.PLAN_END_TIME = PLAN_END_TIME;
        }

        public String getWO_STATUS_ID() {
            return WO_STATUS_ID;
        }

        public void setWO_STATUS_ID(String WO_STATUS_ID) {
            this.WO_STATUS_ID = WO_STATUS_ID;
        }

        public String getUNIT_NAME() {
            return UNIT_NAME;
        }

        public void setUNIT_NAME(String UNIT_NAME) {
            this.UNIT_NAME = UNIT_NAME;
        }

        public String getALARM_TIME() {
            return ALARM_TIME;
        }

        public void setALARM_TIME(String ALARM_TIME) {
            this.ALARM_TIME = ALARM_TIME;
        }

        public String getEQUIP_NO() {
            return EQUIP_NO;
        }

        public void setEQUIP_NO(String EQUIP_NO) {
            this.EQUIP_NO = EQUIP_NO;
        }

        public String getDEVICE_NAME() {
            return DEVICE_NAME;
        }

        public void setDEVICE_NAME(String DEVICE_NAME) {
            this.DEVICE_NAME = DEVICE_NAME;
        }

        public String getALARM_CAUSE_NAME() {
            return ALARM_CAUSE_NAME;
        }

        public void setALARM_CAUSE_NAME(String ALARM_CAUSE_NAME) {
            this.ALARM_CAUSE_NAME = ALARM_CAUSE_NAME;
        }

        public String getEQUIP_NAME() {
            return EQUIP_NAME;
        }

        public void setEQUIP_NAME(String EQUIP_NAME) {
            this.EQUIP_NAME = EQUIP_NAME;
        }

        public String getALARM_LEVEL() {
            return ALARM_LEVEL;
        }

        public void setALARM_LEVEL(String ALARM_LEVEL) {
            this.ALARM_LEVEL = ALARM_LEVEL;
        }

        public String getWORK_ORDER_NO() {
            return WORK_ORDER_NO;
        }

        public void setWORK_ORDER_NO(String WORK_ORDER_NO) {
            this.WORK_ORDER_NO = WORK_ORDER_NO;
        }

        public String getWO_STATUS() {
            return WO_STATUS;
        }

        public void setWO_STATUS(String WO_STATUS) {
            this.WO_STATUS = WO_STATUS;
        }

        public String getALARM_LEVEL_ID() {
            return ALARM_LEVEL_ID;
        }

        public void setALARM_LEVEL_ID(String ALARM_LEVEL_ID) {
            this.ALARM_LEVEL_ID = ALARM_LEVEL_ID;
        }
    }
}
