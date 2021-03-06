package speedata.com.powermeasure.bean;

import java.util.List;

/**
 * Created by 74118 on 2016/8/10.
 */
public class OperLoginClass {

    /**
     * RT_LIST : [{"PROC_CODE":"IOM01","COUNT":0,"PROC_NAME":"设备巡检管理"},{"PROC_CODE":"IOM02","COUNT":2,"PROC_NAME":"专项维保管理"},{"PROC_CODE":"IOM03","COUNT":0,"PROC_NAME":"定期维保管理"},{"PROC_CODE":"IOM04","COUNT":0,"PROC_NAME":"设备检修管理"},{"PROC_CODE":"IOM05","COUNT":0,"PROC_NAME":"疑难故障管理"},{"PROC_CODE":"IOM06","COUNT":0,"PROC_NAME":"设备更换管理"},{"PROC_CODE":"IOM07","COUNT":0,"PROC_NAME":"设备升级管理"}]
     * OPER_NO : CW_DEVELOP
     * RT_F : 1
     * DEPT_NO : 10210
     * DEPT_NAME : 浙江涵普电力科技有限公司
     * OPER_NAME : 系统管理员
     * RT_D : 系统接口提示,系统首页数据获取成功!
     */

    private String OPER_NO;
    private String RT_F;
    private String DEPT_NO;
    private String DEPT_NAME;
    private String OPER_NAME;
    private String RT_D;
    private String IOM_PATH;
    private String ROLE_NO;
    private String ROLE_NAME;

    public String getROLE_NO() {
        return ROLE_NO;
    }

    public void setROLE_NO(String ROLE_NO) {
        this.ROLE_NO = ROLE_NO;
    }

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME;
    }

    public String getIOM_PATH() {
        return IOM_PATH;
    }

    public void setIOM_PATH(String IOM_PATH) {
        this.IOM_PATH = IOM_PATH;
    }

    /**
     * PROC_CODE : IOM01
     * COUNT : 0
     * PROC_NAME : 设备巡检管理
     */

    private List<RTLISTBean> RT_LIST;

    public String getOPER_NO() {
        return OPER_NO;
    }

    public void setOPER_NO(String OPER_NO) {
        this.OPER_NO = OPER_NO;
    }

    public String getRT_F() {
        return RT_F;
    }

    public void setRT_F(String RT_F) {
        this.RT_F = RT_F;
    }

    public String getDEPT_NO() {
        return DEPT_NO;
    }

    public void setDEPT_NO(String DEPT_NO) {
        this.DEPT_NO = DEPT_NO;
    }

    public String getDEPT_NAME() {
        return DEPT_NAME;
    }

    public void setDEPT_NAME(String DEPT_NAME) {
        this.DEPT_NAME = DEPT_NAME;
    }

    public String getOPER_NAME() {
        return OPER_NAME;
    }

    public void setOPER_NAME(String OPER_NAME) {
        this.OPER_NAME = OPER_NAME;
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

    public static class RTLISTBean {
        private String PROC_CODE;
        private int COUNT;
        private String PROC_NAME;

        public String getPROC_CODE() {
            return PROC_CODE;
        }

        public void setPROC_CODE(String PROC_CODE) {
            this.PROC_CODE = PROC_CODE;
        }

        public int getCOUNT() {
            return COUNT;
        }

        public void setCOUNT(int COUNT) {
            this.COUNT = COUNT;
        }

        public String getPROC_NAME() {
            return PROC_NAME;
        }

        public void setPROC_NAME(String PROC_NAME) {
            this.PROC_NAME = PROC_NAME;
        }
    }
}
