package com.baluche.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baluche.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.baluche.app.Constant.SELECT_PHOTO;
import static com.baluche.app.Constant.TAKE_PHTOT;


/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class PersonalmsgActivity extends Activity implements View.OnClickListener {
    private String sdPath;//SD卡的路径
    private String picPath;//图片存储路径

    private View inflate;
    private EditText personal_name_msg;
    private TextView choosePhoto;
    private TextView takePhoto;
    private Dialog dialog;
    private SimpleDraweeView personal_head_img;
    private ImageView return_left;

    private RelativeLayout personal_head;//头像框

    Uri mImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalmsg);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        sdPath = Environment.getExternalStorageDirectory().getPath();//设置拍照照片的保存路径
        picPath = sdPath + "/" + "image.jpg";

        initView();
    }

    private void initView() {
        personal_head = findViewById(R.id.personal_head);
        personal_head_img = findViewById(R.id.personal_head_img);
        return_left = findViewById(R.id.return_left);
        personal_name_msg = findViewById(R.id.personal_name_msg);

        personal_head.setOnClickListener(this);
        return_left.setOnClickListener(this);
        personal_name_msg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    personal_name_msg.clearFocus();//失去焦点
                    // 此处为得到焦点时的处理内容
                } else {
                    personal_name_msg.clearFocus();
                    // 此处为失去焦点时的处理内容
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personal_head:
                show_headchange();
                break;
            case R.id.return_left:
                PersonalmsgActivity.this.finish();
                break;
            case R.id.personal_name_msg:
                Toast.makeText(getApplicationContext(), "自定义显示位置的Toast", Toast.LENGTH_SHORT).show();
                personal_name_msg.setFocusable(true);
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
            default:
                break;
        }
    }

    public void show_headchange() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate
                (R.layout.dialog_layout, null);
        //初始化控件
        choosePhoto = (TextView) inflate.findViewById(R.id.choosePhoto);
        takePhoto = (TextView) inflate.findViewById(R.id.takePhoto);
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
