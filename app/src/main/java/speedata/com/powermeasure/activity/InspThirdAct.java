package speedata.com.powermeasure.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.alibaba.fastjson.JSON;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.base.App;
import common.base.act.FragActBase;
import common.event.ViewMessage;
import common.http.MResponse;
import common.http.MResponseListener;
import common.utils.FTPUtils;
import common.view.CustomTitlebar;
import speedata.com.powermeasure.R;
import speedata.com.powermeasure.bean.InspOfEquipQueryClass;
import speedata.com.powermeasure.bean.LoginOutClass;
import speedata.com.powermeasure.model.WebModel;

@EActivity(R.layout.activity_insp_third)
public class InspThirdAct extends FragActBase {
    @ViewById
    CustomTitlebar titlebar;
    @ViewById
    Button btn_inspThird_takephoto;
    @ViewById
    Button btn_inspThird_trouble;
    @ViewById
    EditText et_inspThird_trouble;
    @ViewById
    ImageView iv_inspThird_photo;
    @ViewById
    Spinner sp_insp_third;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static String path = null;
    private InspOfEquipQueryClass inspOfEquipQueryClass;
    private List<InspOfEquipQueryClass.RTLISTBean> rt_list=new ArrayList<>();

    @AfterViews
    protected void main() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        App.getInstance().addActivity(InspThirdAct.this);
        initTitlebar();
        setSwipeEnable(false);
        pictureId = Integer.parseInt(getXml("pictureId", "0"));
        getInspOfEquipQuery();
    }

    private void getInspOfEquipQuery() {
        showLoading("查询中...");
        WebModel.getInstance().inspOfEquipQuery(new MResponseListener() {
            @Override
            public void onSuccess(MResponse response) {
                String callWebService = String.valueOf(response.data);
                List<InspOfEquipQueryClass> inspOfEquipQueryClasses = JSON.parseArray(callWebService,
                        InspOfEquipQueryClass.class);
                inspOfEquipQueryClass = inspOfEquipQueryClasses.get(0);
                String rt_f = inspOfEquipQueryClass.getRT_F();
                if (rt_f.equals("1")) {
                    rt_list = inspOfEquipQueryClass.getRT_LIST();
                    Message message = new Message();
                    message.what = INSP_SCAN_SUCCESS;
                    handler.sendMessage(message);
                } else {
                    showToast(inspOfEquipQueryClass.getRT_D());
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
        },getXml("OPER_NO", "1"), getXml("INSP_NO", ""));
    }


    //上报故障
    @Click
    void btn_inspThird_trouble() {
        if (path != null) {
            CommitToFTP();
        }
        InspReport();
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case INSP_SCAN_SUCCESS:
                    suppers = new String[rt_list.size()];
                    for (int i = 0; i < rt_list.size(); i++) {
                        suppers[i] = rt_list.get(i).getEQUIP_NAME();
                    }
                    supperAdapter = new ArrayAdapter<String>(InspThirdAct.this, android.R.layout
                            .simple_spinner_item, suppers);
                    sp_insp_third.setAdapter(supperAdapter);
                    setXml("fb_equipNo",rt_list.get(0).getEQUIP_NO());
                    break;

            }
        }
    };
    private static final int INSP_SCAN_SUCCESS = 0;
    private ArrayAdapter<String> supperAdapter;
    private String[] suppers;


    //上报
    private void InspReport() {
        getInspFeedBack();
    }

    private void getInspReport() {
        showLoading("上报中...");
        final String ALARM_CAUSE_NAME = et_inspThird_trouble.getText().toString();
        WebModel.getInstance().inspReport(new MResponseListener() {
                                              @Override
                                              public void onSuccess(MResponse response) {
                                                  String callWebService = String.valueOf(response.data);
                                                  List<LoginOutClass> loginOutClasses = JSON.parseArray(callWebService,
                                                          LoginOutClass.class);
                                                  LoginOutClass inspReport = loginOutClasses.get(0);
                                                  String rt_f = inspReport.getRT_F();
                                                  if (rt_f.equals("1")) {
                                                      showToast("上报成功！");
                                                      setXml("fankuiSuccess", "1");
                                                      openAct(TestActivity.class,true);
                                                      finish();
                                                  } else {
                                                      showToast("上报失败，" + inspReport.getRT_D());
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
                                          },getXml("OPER_NO", "1"), getXml("INSP_NO", "1"),
                rt_list.get(sp_insp_third.getSelectedItemPosition()).getEQUIP_NO()
                , ALARM_CAUSE_NAME, getXml("DEPT_NO", ""), path,"");
    }

    private void getInspFeedBack() {
        showLoading("反馈中...");
        WebModel.getInstance().inspFeedback(new MResponseListener() {
            @Override
            public void onSuccess(MResponse response) {
                String callWebService = String.valueOf(response.data);
                List<LoginOutClass> loginOutClasses = JSON.parseArray(callWebService,
                        LoginOutClass.class);
                LoginOutClass inspFeedBack = loginOutClasses.get(0);
                String rt_f = inspFeedBack.getRT_F();
                if (rt_f.equals("1")) {
                    getInspReport();
                } else {
                    showToast("反馈失败，" + inspFeedBack.getRT_D());
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
        },getXml("OPER_NO", "1"), getXml("INSP_NO", "1"), getXml("fb_equipNo",""), getXml("inspFeedback_resultList",""));
    }

    //上传FTP
    private void CommitToFTP() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FTPUtils ftpUtils = FTPUtils.getInstance();
                boolean initFTPSetting = ftpUtils
                        .initFTPSetting("191.168.1.61", 21, "ftpiom", "ftpiom");
                if (initFTPSetting) {
                    String iom_path = getXml("IOM_PATH", "");
                    ftpUtils.uploadFile(Environment.getExternalStoragePublicDirectory(Environment
                            .DIRECTORY_PICTURES) + "/MyCameraApp/" + path, path, iom_path + "/IOM01");
                }

            }
        }).start();

    }


    @Click
    void btn_inspThird_takephoto() {
        // 利用系统自带的相机应用:拍照
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // create a file to save the image
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        // 此处这句intent的值设置关系到后面的onActivityResult中会进入那个分支，即关系到data是否为null，如果此处指定，则后来的data为null
        // set the image file name
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

    /**
     * Create a file Uri for saving an image or video
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = null;
        try {
            mediaStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(Environment
                            .DIRECTORY_PICTURES), "MyCameraApp");


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                // 在SD卡上创建文件夹需要权限：
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            path = "A_" + timeStamp + ".jpg";
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + path);
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 如果是拍照
        if (CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE == requestCode) {

            if (RESULT_OK == resultCode) {

                // Check if the result includes a thumbnail Bitmap
                if (data != null) {
                    // 没有指定特定存储路径的时候
                    // 指定了存储路径的时候（intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);）
                    if (data.hasExtra("data")) {
                        Bitmap thumbnail = data.getParcelableExtra("data");
                        iv_inspThird_photo.setImageBitmap(thumbnail);
                    }
                } else {

                    // If there is no thumbnail image data, the image
                    // will have been stored in the target output URI.
                    // Resize the full image to fit in out image view.
                    int width = iv_inspThird_photo.getWidth();
                    int height = iv_inspThird_photo.getHeight();

                    BitmapFactory.Options factoryOptions = new BitmapFactory.Options();

                    factoryOptions.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(fileUri.getPath(), factoryOptions);

                    int imageWidth = factoryOptions.outWidth;
                    int imageHeight = factoryOptions.outHeight;

                    // Determine how much to scale down the image
                    int scaleFactor = Math.min(imageWidth / width, imageHeight
                            / height);

                    // Decode the image file into a Bitmap sized to fill the
                    // View
                    factoryOptions.inJustDecodeBounds = false;
                    factoryOptions.inSampleSize = scaleFactor;
                    factoryOptions.inPurgeable = true;

                    Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                            factoryOptions);

                    iv_inspThird_photo.setImageBitmap(bitmap);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
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
                          }, "故障上报",
                getXml("OPER_NAME", ""), getXml("DEPT_NAME", ""), getXml("ROLE_NAME", "运维"), null);
    }

    @Override
    public void onEventMainThread(ViewMessage viewMessage) {

    }

}
