package speedata.com.powermeasure.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import common.adapter.CommonAdapter;
import common.adapter.ViewHolder;
import common.base.App;
import common.base.act.FragActBase;
import common.event.ViewMessage;
import common.http.MResponse;
import common.http.MResponseListener;
import common.view.CustomTitlebar;
import speedata.com.powermeasure.R;
import speedata.com.powermeasure.bean.SpListClass;
import speedata.com.powermeasure.model.WebModel;

@EActivity(R.layout.activity_special)
public class SpecialAct extends FragActBase {
    @ViewById
    CustomTitlebar titlebar;
    @ViewById
    ListView lv_insp;
    private List<SpListClass.RTLISTBean> special_list;
    private static final int Get_SPLIST_SUCCESS=0;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                //获得维保单 （没完成）**
                case Get_SPLIST_SUCCESS:
                    CommonAdapter commonAdapter=new CommonAdapter(mContext,special_list,
                            R.layout.adapter_sp_new) {
                        @Override
                        public void convert(ViewHolder helper, Object item, int position) {
                            helper.setText(R.id.tv_sp_startTime,
                                    special_list.get(position).getSTART_TIME());
                            helper.setText(R.id.tv_sp_endTime,
                                    special_list.get(position).getEND_TIME());
                            helper.setText(R.id.tv_sp_method,
                                    special_list.get(position).getORDER_NO());
                            helper.setText(R.id.tv_ATMT_NAME,
                                    special_list.get(position).getATMT_NAME());
                            if (position%2!=0){
                                helper.setLLBackgroundIV(R.id.ll_sp,R.drawable.green);
                            }else {
                                helper.setLLBackgroundIV(R.id.ll_sp,R.drawable.blue);
                            }
                        }
                    };
                    lv_insp.setAdapter(commonAdapter);
                    lv_insp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            showLoading("跳转中...");
                            setXml("ORDER_NO",special_list.get(position).getORDER_NO());
                            setXml("START_TIME",special_list.get(position).getSTART_TIME());
                            setXml("END_TIME",special_list.get(position).getEND_TIME());
                            setXml("FILE_NAME",special_list.get(position).getFILE_NAME());
                            setXml("FILE_PATH",special_list.get(position).getFILE_PATH());
                            openAct(SpecialSecondAct.class,true);
                        }
                    });
                    break;
            }
        }
    };
    @AfterViews
    protected void main() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        App.getInstance().addActivity(SpecialAct.this);
        initTitlebar();
        setSwipeEnable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DoGetSpecialList();
    }

    private void DoGetSpecialList() {
        //专项维保列表查询
        showLoading("查询中...");
        WebModel.getInstance().getSpecialList(new MResponseListener() {
            @Override
            public void onSuccess(MResponse response) {
                String callWebService = String.valueOf(response.data);
//                callWebService="[{\"RT_F\":\"1\",\"RT_D\":\"\",\"SPECIAL_LIST\":[{\"ORDER_NO\":\"B2016070212334\",\"START_TIME\":\"2016-08-02 12：00\",\n" +
//                        "\"END_TIME\":\"2016-08-02 17：00\",\"FILE_NAME\":\"123456\",\"FILE_PATH\":\"/home/app/\"}]}]";
                List<SpListClass> spListClasses = JSON.parseArray(callWebService
                        , SpListClass.class);
                SpListClass spListClass = spListClasses.get(0);
                String rt_f = spListClass.getRT_F();
                if (rt_f.equals("1")){
                    special_list = spListClass.getRT_LIST();
                    Message message=new Message();
                    message.what=Get_SPLIST_SUCCESS;
                    handler.sendMessage(message);
                }else {
                    showToast("查询失败，"+spListClass.getRT_D());
                    finish();
                }

                hideLoading();
            }

            @Override
            public void onError(final MResponse response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.msg != null) {
                            showToast(response.msg.toString());
                        } else {
                            showToast("出错了！");
                        }
                        hideLoading();
                    }
                });
            }
        },"IOM02",getXml("OPER_NO", ""));
    }

    @Override
    protected Context regieterBaiduBaseCount() {
        return null;
    }

    @Override
    protected void initTitlebar() {
        titlebar.setTitlebarStyle(CustomTitlebar.TITLEBAR_STYLE_NORMAL);
        titlebar.setAttrs(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "专项维保",
                getXml("OPER_NAME",""),getXml("DEPT_NAME",""),getXml("ROLE_NAME","运维"),null);
    }

    @Override
    public void onEventMainThread(ViewMessage viewMessage) {

    }
}
