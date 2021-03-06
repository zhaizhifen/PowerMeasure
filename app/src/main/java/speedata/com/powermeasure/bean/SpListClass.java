package speedata.com.powermeasure.bean;

import java.util.List;

/**
 * Created by 张明_ on 2016/8/11.
 */
public class SpListClass {

    /**
     * RT_LIST : [{"FILE_NAME":"B10400.png","ORDER_NO":"B2016091210210000865","END_TIME":"2016-09-30 15:12:00","START_TIME":"2016-09-04 15:12:00","FILE_PATH":"/home/app/iomfile\\zxwb\\"}]
     * RT_F : 1
     * RT_D : 系统接口提示,专项维保列表查询成功!
     */

    private String RT_F;
    private String RT_D;
    /**
     * FILE_NAME : B10400.png
     * ORDER_NO : B2016091210210000865
     * END_TIME : 2016-09-30 15:12:00
     * START_TIME : 2016-09-04 15:12:00
     * FILE_PATH : /home/app/iomfile\zxwb\
     */

    private List<RTLISTBean> RT_LIST;

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

    public static class RTLISTBean {
        private String FILE_NAME;
        private String ORDER_NO;
        private String END_TIME;
        private String START_TIME;
        private String FILE_PATH;
        private String ATMT_NAME;

        public String getATMT_NAME() {
            return ATMT_NAME;
        }

        public void setATMT_NAME(String ATMT_NAME) {
            this.ATMT_NAME = ATMT_NAME;
        }

        public String getFILE_NAME() {
            return FILE_NAME;
        }

        public void setFILE_NAME(String FILE_NAME) {
            this.FILE_NAME = FILE_NAME;
        }

        public String getORDER_NO() {
            return ORDER_NO;
        }

        public void setORDER_NO(String ORDER_NO) {
            this.ORDER_NO = ORDER_NO;
        }

        public String getEND_TIME() {
            return END_TIME;
        }

        public void setEND_TIME(String END_TIME) {
            this.END_TIME = END_TIME;
        }

        public String getSTART_TIME() {
            return START_TIME;
        }

        public void setSTART_TIME(String START_TIME) {
            this.START_TIME = START_TIME;
        }

        public String getFILE_PATH() {
            return FILE_PATH;
        }

        public void setFILE_PATH(String FILE_PATH) {
            this.FILE_PATH = FILE_PATH;
        }
    }
}
