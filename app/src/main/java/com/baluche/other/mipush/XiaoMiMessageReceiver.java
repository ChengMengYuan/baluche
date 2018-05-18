package com.baluche.other.mipush;

import android.content.Context;
import android.util.Log;

import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

/**
 * 继承自PushMessageReceiver（抽象类，继承自BroadcastReceiver），其作用主要是：
 * 接收推送消息
 * 对推送消息进行处理
 */
public class XiaoMiMessageReceiver extends PushMessageReceiver {
    /**
     * 小米推送---->当前设备上当前app的唯一标示
     */
    private String mRegId;

    /**
     * 透传消息到达客户端时调用
     * 作用：可通过参数message从而获得透传消息，具体请看官方SDK文档
     *
     * @param context       context
     * @param miPushMessage miPushMessage
     */
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        super.onReceivePassThroughMessage(context, miPushMessage);
        Log.d("miPushMessage", "透传消息到达了");
        Log.d("miPushMessage", "" + miPushMessage.toString());
    }

    /**
     * 通知消息到达客户端时调用
     * 注：应用在前台时不弹出通知的通知消息到达客户端时也会回调函数
     * 作用：通过参数message从而获得通知消息，具体请看官方SDK文档
     *
     * @param context       context
     * @param miPushMessage miPushMessage
     */
    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageArrived(context, miPushMessage);
        Log.d("miPushMessage", "通知消息到达了");
        Log.d("miPushMessage", "" + miPushMessage.toString());
    }

    /**
     * 用户手动点击通知栏消息时调用
     * 注：应用在前台时不弹出通知的通知消息到达客户端时也会回调函数
     * 作用：
     * 1. 通过参数message从而获得通知消息，具体请看官方SDK文档
     * 2. 设置用户点击消息后打开应用 or 网页 or 其他页面
     *
     * @param context       context
     * @param miPushMessage miPushMessage
     */
    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageClicked(context, miPushMessage);
        Log.d("miPushMessage", "用户点击了通知消息。");
        Log.d("miPushMessage", "通知消息是" + miPushMessage.toString());
        Log.d("miPushMessage", "点击后,会进入应用");
    }

    /**
     * 用来接收客户端向服务器发送命令后的响应结果。
     *
     * @param context              context
     * @param miPushCommandMessage miPushCommandMessage
     */
    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        super.onCommandResult(context, miPushCommandMessage);
        String command = miPushCommandMessage.getCommand();
        System.out.println(command);
        List<String> arguments = miPushCommandMessage.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);

        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                //打印信息便于测试注册成功与否
                Log.d("miPushMessage", "注册成功");
                mRegId = cmdArg1;
                Log.d("miPushMessage", mRegId);
            } else {
                Log.d("miPushMessage", "注册失败");
            }
        }
    }

    /**
     * 用于接收客户端向服务器发送注册命令后的响应结果。
     *
     * @param context              context
     * @param miPushCommandMessage miPushCommandMessage
     */
    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        super.onReceiveRegisterResult(context, miPushCommandMessage);
        String command = miPushCommandMessage.getCommand();
        System.out.println(command);
        List<String> arguments = miPushCommandMessage.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1;
                //打印日志：注册成功
                Log.d("miPushMessage", "注册成功");
            } else {
                //打印日志：注册失败
                Log.d("miPushMessage", "注册失败");
            }
        } else {
            Log.d("miPushMessage", "其他情况" + miPushCommandMessage.getReason());
        }
    }

}

