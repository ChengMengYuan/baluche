package com.baluche.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baluche.R;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.model.http.entity.PersonMsg;
import com.baluche.presenter.LoginPre;
import com.baluche.presenter.PersonMsgPre;
import com.baluche.util.SnackbarUtil;
import com.baluche.base.BaseActivity;
import com.baluche.view.api.ILoginACT;
import com.baluche.view.api.IPersonMsgACT;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Administrator on 2018/3/26 0026
 */

public class PersonMsgActivity extends BaseActivity implements IPersonMsgACT {
    public static final int SELECT_PHOTO = 1; //显示Android自带图库，用于选择用户自己的图片
    public static final int TAKE_PHTOT = 2;//选择照片
    private String sdPath;//SD卡的路径
    private String picPath;//图片存储路径
    private MaterialDialog materialDialog;//等待的dialog

    private PersonMsgPre personmsgPre;
    private View inflate;
    private EditText personal_name_msg;
    private TextView choosePhoto;
    private TextView takePhoto;
    private TextView timepicker_msg; //时间选择器
    private TextView personal_sex_msg;
    private Dialog dialog;
    private SimpleDraweeView personal_head_img;
    private RelativeLayout return_left;
    private Button updata_permsg;//确定按钮
    private String[] sexArry = new String[]{"男","女"};// 性别选择

//    public static Object nickname = "123456";
//    public static int sex = 1;
//    public static int birthday = 2;

    private RelativeLayout personal_head;//头像框
    private RelativeLayout personal_sex;//性别框

    Uri mImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persion_msg);
        personmsgPre = new PersonMsgPre(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        sdPath = Environment.getExternalStorageDirectory().getPath();//设置拍照照片的保存路径
        picPath = sdPath + "/" + "image.jpg";
    }

    @Override
    public void initView() {
        personal_head = findViewById(R.id.personal_head);
        personal_head_img = findViewById(R.id.personal_head_img);
        return_left = findViewById(R.id.return_left);
        personal_name_msg = findViewById(R.id.personal_name_msg);
        timepicker_msg = findViewById(R.id.timepicker_msg);
        updata_permsg = findViewById(R.id.updata_permsg);
        personal_sex = findViewById(R.id.personal_sex);
        personal_sex_msg = findViewById(R.id.personal_sex_msg);
        updata_permsg.setOnClickListener(this);
        personal_sex.setOnClickListener(this);
        timepicker_msg.setOnClickListener(this);
        personal_head.setOnClickListener(this);
        return_left.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.personal_head:
                personmsgPre.personal_head();
                break;
            case R.id.return_left:
                finish();
                break;
            case R.id.personal_name_msg:
                personmsgPre.write_personal_name_msg();
                break;
            case R.id.personal_sex:
                showSexChooseDialog();
                break;
            case R.id.choosePhoto:
                personmsgPre.choosePhoto();
                break;
            case R.id.takePhoto:
                personmsgPre.takePhoto();
                break;
            case R.id.timepicker_msg:
                personmsgPre.write_timepicker_msg();
                break;
            case R.id.updata_permsg:
//                personmsgPre.updata_permsg();
//                personmsgPre.updatePortrait();
                HttpMethods.getInstance().updatePersonMsg(new Object(), 1, "", new Observer<PersonMsg>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PersonMsg personMsg) {
                        if (personMsg.getCode() == 200) {
                            Log.d("PersonMsg", "PutMsg" + "success++++++++++++++++++++++++++++++++");
                        }
                        switch (personMsg.getCode()) {
                            case 200:
                                Log.d("PersonMsg", "PutMsg" + "success++++++++++++++++++++++++++++++++");
                                break;
                            default:
                                Log.d("PersonMsg", "PutMsg" + personMsg.getCode());
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void show_headchange() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate
                (R.layout.dialog_layout, null);
        //初始化控件
        choosePhoto = inflate.findViewById(R.id.choosePhoto);
        takePhoto = inflate.findViewById(R.id.takePhoto);
        choosePhoto.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    @Override
    public void write_personal_name_msg() {
        personal_name_msg.setFocusable(true);
        showInputMethod();
    }

    @Override
    public void write_timepicker_msg() {
        LogD(" timepicker_msg is on click");
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(PersonMsgActivity.this, (date, v) -> {
            LogD("" + date.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");//这是转成后的时间的格式
            String sd = sdf.format(new Date(Long.parseLong(String.valueOf(date.getTime()))));   // 时间戳转换成时间
            LogD("" + sd);
            timepicker_msg.setText(sd);
        }).build();
        pvTime.show();
    }

    @Override
    public void during_send_personal_name_msg() {
        materialDialog = new MaterialDialog.Builder(this)
                .title("请稍候")
                .content("正在上传数据")
                .progress(true, 0)
                .show();
    }

    @Override
    public void clear_send_personal_name_msg() {
        materialDialog.dismiss();
    }

    MaterialDialog successDialog;//提示的dialog

    @Override
    public void messageChangeSuccess() {
        successDialog = new MaterialDialog.Builder(this)
                .title("信息传输")
                .content("信息上传成功")
                .negativeText("确定")
                .show();
    }

    MaterialDialog failDialog;//提示的dialog

    @Override
    public void messageChangeFail() {
        failDialog = new MaterialDialog.Builder(this)
                .title("信息传输")
                .content("信息上传失败")
                .negativeText("确定")
                .show();
    }

    @Override
    public void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PHOTO);//运行Intent事件
        dialog.dismiss();
    }

    @Override
    public void takePhoto() {
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
        //为拍摄的图片指定一个存储的路径
        intent2.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        startActivityForResult(intent2, TAKE_PHTOT);
        dialog.dismiss();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_PHOTO://从相册选择照片
                if (requestCode == SELECT_PHOTO && resultCode == Activity.RESULT_OK) {
                    if (data != null)
                    {
                        personal_head_img.setImageURI(data.getData());
                    }
                }
                break;

            case TAKE_PHTOT://通过相机拍照生成头像
                FileInputStream fis = null;
                Log.d("PersonMsgActivity", "requestCode:===" + requestCode);
                Log.d("PersonMsgActivity", "resultCode:===" + resultCode);
                if (requestCode == TAKE_PHTOT && resultCode == Activity.RESULT_OK){
                    try {
                        //把图片转化为字节流
                        fis = new FileInputStream(picPath);
                        //把流转化图片
                        Bitmap bitmap = BitmapFactory.decodeStream(fis);
                        //将图片由Bitmap格式转换为URI
                        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
                        Log.d("PersonMsgActivity", "sdPath:===" + sdPath);
                        Log.d("PersonMsgActivity", "picPath:===" + picPath);
                        personal_head_img.setImageURI(uri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fis.close();//关闭流
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }

    private void showSexChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);// 自定义对话框
        builder3.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                personal_sex_msg.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }
}
