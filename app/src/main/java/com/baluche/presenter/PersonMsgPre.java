package com.baluche.presenter;

import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baluche.R;
import com.baluche.base.BasePresenter;
import com.baluche.model.http.entity.PersonMsg;
import com.baluche.model.http.http.HttpMethods;
import com.baluche.view.api.ILoginACT;
import com.baluche.view.api.IPersonMsgACT;
import android.support.v4.app.FragmentActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.baluche.view.activity.PersonMsgActivity.SELECT_PHOTO;

/**
 * 文 件 名: PersonMsgPre<p>
 * 创 建 人: ZEndOf<p>
 * 创建日期: 2018/5/3 03 18:05<p>
 * 邮   箱: 2766755768@qq.com<p>
 * 文件说明:<p>
 */
public class PersonMsgPre extends BasePresenter{

    private IPersonMsgACT iPersonMsgACT;
    private View inflate;
    private Dialog dialog;
    private TextView choosePhoto;
    private TextView takePhoto;

    public PersonMsgPre(IPersonMsgACT iPersonMsgACT) {
        this.iPersonMsgACT = iPersonMsgACT;
    }

    public void personal_head(){
        iPersonMsgACT.show_headchange();
    }

    public void write_personal_name_msg(){
        iPersonMsgACT.write_personal_name_msg();
    }

    public void write_timepicker_msg(){
        iPersonMsgACT.write_timepicker_msg();
    }

    public void updata_permsg(){
        iPersonMsgACT.during_send_personal_name_msg();
        HttpMethods.getInstance().updatePersonMsg(new Observer<PersonMsg>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PersonMsg personMsg) {
                iPersonMsgACT.clear_send_personal_name_msg();
                switch (personMsg.getCode()) {
                    case 200:
                     iPersonMsgACT.messageChangeSuccess();
                        break;
                    default:
                    iPersonMsgACT.messageChangeFail();
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
    }


    public void choosePhoto(){
        iPersonMsgACT.choosePhoto();
    }

    public void takePhoto(){
        iPersonMsgACT.takePhoto();
    }
}
