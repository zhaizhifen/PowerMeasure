package speedata.com.powermeasure.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import common.base.App;
import common.base.act.FragActBase;
import common.event.ViewMessage;
import common.http.MResponse;
import common.http.MResponseListener;
import common.view.CustomTitlebar;
import speedata.com.powermeasure.R;
import speedata.com.powermeasure.bean.AlKnlgClass;
import speedata.com.powermeasure.bean.LoginOutClass;
import speedata.com.powermeasure.model.WebModel;

@EActivity(R.layout.activity_alarm_third_new)
public class AlarmThirdAct extends FragActBase {
    private static final int AL_KNLG_SUCCESS = 0;
    @ViewById
    CustomTitlebar titlebar;
    @ViewById
    EditText et_alSecond_beizhu;
    @ViewById
    EditText et_althird_repair;
    @ViewById
    Button btn_althird_fankui;
    @ViewById
    Button btn_althird_postpone;
    @ViewById
    Button btn_althird_toTrouble;
    @ViewById
    CheckBox cb_alSecond_cause1;
    @ViewById
    CheckBox cb_alSecond_cause2;
    @ViewById
    CheckBox cb_alSecond_cause3;
    @ViewById
    CheckBox cb_alSecond_deal1;
    @ViewById
    CheckBox cb_alSecond_deal2;
    @ViewById
    CheckBox cb_alSecond_deal3;
    @ViewById
    TextView tv_alSecond_time;
    @ViewById
    TextView tv_alSecond_reason;
    @ViewById
    TextView tv_alSecond_method;
    @ViewById
    LinearLayout cb_dealLL;
    @ViewById
    LinearLayout cb_causeLL;
    @ViewById
    RadioGroup dialog_al_rg;
    @ViewById
    RadioGroup deal_al_rg;
    @ViewById
    LinearLayout ll_alSecond_planStart;
    @ViewById
    LinearLayout ll_alSecond_planEnd;
    @ViewById
    TextView tv_alSecond_planStart;
    @ViewById
    TextView tv_alSecond_planEnd;
    @ViewById
    EditText et_alSecond_cl;
    @ViewById
    EditText et_alSecond_yy;
    private List<AlKnlgClass.REALCAUSELISTBean> real_cause_list;
    private List<AlKnlgClass.REALDEALLISTBean> real_deal_list;
    private List<RadioButton> causeLists;
    private List<RadioButton> dealLists;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AL_KNLG_SUCCESS:
                    //显示实际原因选项
                    AlKnlgClass.REALCAUSELISTBean bean=new AlKnlgClass.REALCAUSELISTBean();
                    bean.setREAL_CAUSE("99999999");
                    bean.setREAL_CAUSE_NAME("误报");
                    real_cause_list.add(bean);
                    for (int i = 0; i < real_cause_list.size(); i++) {
                        RadioButton radioButton=new RadioButton(mContext);
                        radioButton.setText(real_cause_list.get(i).getREAL_CAUSE_NAME());
                        dialog_al_rg.addView(radioButton);
                        causeLists.add(radioButton);
                    }
                    //显示实际处理方法选项
                    for (int j = 0; j < real_deal_list.size(); j++) {
                        RadioButton radioButton=new RadioButton(mContext);
                        radioButton.setText(real_deal_list.get(j).getDEAL_METHOD());
                        deal_al_rg.addView(radioButton);
                        dealLists.add(radioButton);
                    }
                    break;
            }
        }
    };

    @AfterViews
    protected void main() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        App.getInstance().addActivity(AlarmThirdAct.this);
        setSwipeEnable(false);
        initTitlebar();
        initUi();
        initDatas();
        AlKnlg();
    }

    private void initUi() {
        tv_alSecond_time.setText(getXml("AL_ALARM_TIME", "1"));
        tv_alSecond_reason.setText(getXml("AL_ALARM_CAUSE_NAME", "1"));
//        tv_alSecond_method.setText(getXml("AL_ALARM_DEAL_SUGGESTION", "1"));
        String plan_bgn_time = getXml("PLAN_BGN_TIME", "");
        if (!TextUtils.isEmpty(plan_bgn_time)) {
            ll_alSecond_planStart.setVisibility(View.VISIBLE);
            tv_alSecond_planStart.setText(plan_bgn_time);
        } else {
            ll_alSecond_planStart.setVisibility(View.GONE);
        }
        String plan_end_time = getXml("PLAN_END_TIME", "");
        if (!TextUtils.isEmpty(plan_end_time)) {
            ll_alSecond_planEnd.setVisibility(View.VISIBLE);
            tv_alSecond_planEnd.setText(plan_end_time);
        } else {
            ll_alSecond_planEnd.setVisibility(View.GONE);
        }
    }

    private void initDatas() {
        causeLists = new ArrayList<RadioButton>();
        dealLists = new ArrayList<RadioButton>();
//        causeLists.add(cb_alSecond_cause1);
//        causeLists.add(cb_alSecond_cause2);
//        causeLists.add(cb_alSecond_cause3);
//        dealLists.add(cb_alSecond_deal1);
//        dealLists.add(cb_alSecond_deal2);
//        dealLists.add(cb_alSecond_deal3);
    }

    //反馈
    @Click
    void btn_althird_fankui() {
        showLoading("反馈中...");
        String REAL_CAUSE = "";
        String DEAL_METHOD_CODE = "";
        for (int i = 0; i < real_cause_list.size(); i++) {
            if (causeLists.get(i).isChecked()) {
                REAL_CAUSE = real_cause_list.get(i).getREAL_CAUSE();
            }
        }
        for (int j = 0; j < real_deal_list.size(); j++) {
            if (dealLists.get(j).isChecked()) {
                DEAL_METHOD_CODE = real_deal_list.get(j).getDEAL_METHOD_CODE();
            }
        }
        String finalREAL_CAUSE ="";
        String finalDEAL_METHOD_CODE = "";
        String et_alSecond_yy_str = this.et_alSecond_yy.getText().toString();
        String et_alSecond_cl_str = et_alSecond_cl.getText().toString();
        if (TextUtils.isEmpty(et_alSecond_yy_str)){
            finalREAL_CAUSE=REAL_CAUSE;
        }else {
            finalREAL_CAUSE="IOM"+et_alSecond_yy_str;
        }

        if (TextUtils.isEmpty(et_alSecond_cl_str)){
            finalDEAL_METHOD_CODE=DEAL_METHOD_CODE;
        }else {
            finalDEAL_METHOD_CODE="IOM"+et_alSecond_cl_str;
        }
        WebModel.getInstance().maintFeedback(new MResponseListener() {
                                                 @Override
                                                 public void onSuccess(MResponse response) {
                                                     String callWebService = String.valueOf(response.data);
                                                     List<LoginOutClass> loginOutClasses = JSON.parseArray(callWebService,
                                                             LoginOutClass.class);
                                                     LoginOutClass maintFeedback = loginOutClasses.get(0);
                                                     String rt_f = maintFeedback.getRT_F();
                                                     if (rt_f.equals("1")) {
                                                         showToast("反馈成功！");
                                                         AlarmThirdAct.this.finish();
                                                     } else {
                                                         showToast(maintFeedback.getRT_D());
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
                                             },getXml("OPER_NO", "1"), getXml("AL_ORDER_NO", "1"), finalREAL_CAUSE, finalDEAL_METHOD_CODE,
                et_althird_repair.getText().toString(),
                et_alSecond_beizhu.getText().toString());
    }

    //延期申请
    @Click
    void btn_althird_postpone() {
        openAct(DelayApplyAct.class,true);
        finish();
    }

    @Click
    void btn_althird_toTrouble() {
        openAct(DiftApplyAct.class, true);
        finish();
    }



    //反馈状态工单明细
    private void AlKnlg() {
        showLoading("查询中...");
        WebModel.getInstance().getAlarmKnlg(new MResponseListener() {
            @Override
            public void onSuccess(MResponse response) {
                String callWebService = String.valueOf(response.data);
//                callWebService="[{\"RT_F\":\"1\",\"RT_D\":\"\",\"REAL_CAUSE_LIST\"" +
//                        ":[{\"REAL_CAUSE\":\"20200101\",\"REAL_CAUSE_NAME\":\"上料机器人执行抓表动作超时\"}] " +
//                        ",\"REAL_DEAL_LIST\":[{\"DEAL_METHOD_CODE\":\"2020010102\",\"DEAL_METHOD\":" +
//                        "\"检修电机空开辅助触点接线\"}]}]";
                List<AlKnlgClass> alKnlgClasses = JSON.parseArray(callWebService,
                        AlKnlgClass.class);
                AlKnlgClass alKnlgClass = alKnlgClasses.get(0);
                String rt_f = alKnlgClass.getRT_F();
                if (rt_f.equals("1")) {
                    real_cause_list = alKnlgClass.getREAL_CAUSE_LIST();
                    real_deal_list = alKnlgClass.getREAL_DEAL_LIST();
                    Message message = new Message();
                    message.what = AL_KNLG_SUCCESS;
                    handler.sendMessage(message);
                } else {
                    showToast(alKnlgClass.getRT_D());
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
        },getXml("OPER_NO", "1"), getXml("AL_ORDER_NO",""));
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
        }, "待维修状态工单",
                getXml("OPER_NAME",""),getXml("DEPT_NAME",""),getXml("ROLE_NAME","运维"),null);
    }

    @Override
    public void onEventMainThread(ViewMessage viewMessage) {

    }
}
