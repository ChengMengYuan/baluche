package com.baluche.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
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
import com.baluche.http.http.HttpMethods;
import com.baluche.http.entity.PersonMsg;
import com.baluche.util.SnackbarUtil;
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

public class PersonMsgActivity extends BaseActivity {
    public static final int SELECT_PHOTO = 1; //显示Android自带图库，用于选择用户自己的图片
    public static final int TAKE_PHTOT = 2;//选择照片
    private String sdPath;//SD卡的路径
    private String picPath;//图片存储路径

    private View inflate;
    private EditText personal_name_msg;
    private TextView choosePhoto;
    private TextView takePhoto;
    private TextView timepicker_msg; //时间选择器
    private Dialog dialog;
    private SimpleDraweeView personal_head_img;
    private ImageView return_left;
    private Button updata_permsg;//确定按钮

    private RelativeLayout personal_head;//头像框

    Uri mImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persion_msg);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        sdPath = Environment.getExternalStorageDirectory().getPath();//设置拍照照片的保存路径
        picPath = sdPath + "/" + "image.jpg";
    }

    @Override
    public void setActivityPre() {

    }

    @Override
    public void initView() {
        personal_head = findViewById(R.id.personal_head);
        personal_head_img = findViewById(R.id.personal_head_img);
        return_left = findViewById(R.id.return_left);
        personal_name_msg = findViewById(R.id.personal_name_msg);
        timepicker_msg = findViewById(R.id.timepicker_msg);
        updata_permsg = findViewById(R.id.updata_permsg);
        updata_permsg.setOnClickListener(this);
        timepicker_msg.setOnClickListener(this);

        personal_head.setOnClickListener(this);
        return_left.setOnClickListener(this);

    }


    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.personal_head:
                $Log("personal_head is on click");
                // FIXME: 2018/4/2 0002 测试代码  测试个人信息查询接口是否能用
                HttpMethods.getInstance().queryPersonMsg(new Observer<PersonMsg>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PersonMsg personMsg) {
                        Gson gson = new Gson();
                        Log.d("http+personMsg", "" + personMsg.getCode());
                        Log.d("http+personMsg", "" + personMsg.getMessage());
                        Log.d("http+personMsg", "" + gson.toJson(personMsg.getData()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                show_headchange();
                break;
            case R.id.return_left:
                finish();
                break;
            case R.id.personal_name_msg:
                Toast.makeText(getApplicationContext(), "自定义显示位置的Toast", Toast.LENGTH_SHORT).show();
                personal_name_msg.setFocusable(true);
                showInputMethod();
                break;
            case R.id.choosePhoto:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_PHOTO);//运行Intent事件
                dialog.dismiss();
                break;
            case R.id.takePhoto:
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                //为拍摄的图片指定一个存储的路径
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                startActivityForResult(intent2, TAKE_PHTOT);
                dialog.dismiss();
                break;
            case R.id.timepicker_msg:
                $Log(" timepicker_msg is on click");
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(PersonMsgActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        $Log("" + date.getTime());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");//这是转成后的时间的格式
                        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(date.getTime()))));   // 时间戳转换成时间
                        $Log("" + sd);
                        timepicker_msg.setText(sd);
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.updata_permsg:
                final MaterialDialog materialDialog;//等待的dialog
                materialDialog = new MaterialDialog.Builder(this)
                        .title("请稍候")
                        .content("正在上传数据")
                        .progress(true, 0)
                        .show();
                HttpMethods.getInstance().updatePersonMsg(new Observer<PersonMsg>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PersonMsg personMsg) {
                        // TODO: 2018/4/8 0008 判断上传成功或者失败做出不同的限制并且取消等待的dialog
                        materialDialog.dismiss();
                        switch (personMsg.getCode()) {
                            case 200:
                                SnackbarUtil.showLongSnackbar(updata_permsg,
                                        "上传成功",
                                        getResources().getColor(R.color.colorGreen),
                                        Color.WHITE);
                                break;
                            default:
                                SnackbarUtil.showLongSnackbar(updata_permsg,
                                        "上传成功",
                                        getResources().getColor(R.color.colorGreen),
                                        Color.WHITE);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECT_PHOTO://从相册选择照片
                if (data != null) {
                    if (requestCode == SELECT_PHOTO && resultCode == Activity.RESULT_OK)
                        personal_head_img.setImageURI(data.getData());
                }
                break;

            case TAKE_PHTOT://通过相机拍照生成头像
                FileInputStream fis = null;

                try {
                    //把图片转化为字节流
                    fis = new FileInputStream(picPath);
                    //把流转化图片
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    //将图片由Bitmap格式转换为URI
                    Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
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
                break;
        }
    }
}
