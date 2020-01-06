package com.ofc.ofc.utils.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.ofc.ofc.activity.PredictionDetailActivity;
import com.ofc.ofc.activity.WebContentActivity;
import com.ofc.ofc.utils.MyLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "收到的消息推送";

    static String url = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            MyLogger.i(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                MyLogger.i(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                MyLogger.i(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//				processCustomMessage(context, bundle);

                /*NotificationManager notificationManager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(context)//此处会有中间一道线，并不影响运行，这是android系统版本的问题
                        .setContentTitle(bundle.getString(JPushInterface.EXTRA_TITLE))  //显示通知的标题
                        .setContentText(bundle.getString(JPushInterface.EXTRA_MESSAGE))//显示消息通知的内容
                        .setWhen(System.currentTimeMillis())//显示通知的具体时间
                        .setSmallIcon(R.mipmap.ic_launcher)//这里设置显示的是手机顶部系统通知的应用图标
//                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))//这里设置显示的是下拉通知栏后显示的系统图标
//                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)//可以在此使用此方法，点击通知后，通知内容自动取消,也可以在NotificationActivity.java中设置方法取消显示通知内容
//                        .setVibrate(new long[] {0,1000,1000,1000})//设置发出通知后震动一秒，停止一秒后再震动一秒，需要在manifest.xml中设置权限
                        .build();
                notification.flags = Notification.FLAG_ONLY_ALERT_ONCE; //发起Notification后，铃声和震动均只执行一次
                notificationManager.notify(1,notification);//通过通知管理器发送通知*/

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                MyLogger.i(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                MyLogger.i(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                MyLogger.i(TAG, "[MyReceiver] 用户点击打开了通知");
                //打开自定义的Activity
                //解析数据
                JSONObject jObj = new JSONObject(url);
                String type = jObj.getString("type");
                MyLogger.i(">>>>>>>>>type:" + type);
                switch (type) {
                    case "1":
                        //网页
                        String url1 = jObj.getString("url");
                        MyLogger.i(">>>>>>>>>url:" + url1);
                        /*Bundle bundle2 = new Bundle();
                        bundle2.putString("url", url1);
                        CommonUtil.gotoActivityWithData(context, WebContentActivity.class, bundle2);*/
                        Intent i = new Intent(context, WebContentActivity.class);
                        bundle.putString("url", url1);
                        i.putExtras(bundle);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(i);
                        break;
                    case "2":
                        //订单详情
                        String symbol = jObj.getString("symbol");
                        MyLogger.i(">>>>>>>>>symbol:" + symbol);
//                        Intent i2 = new Intent(context, PredictionDetailActivity_MPChart.class);
                        Intent i2 = new Intent(context, PredictionDetailActivity.class);
                        bundle.putString("symbol", symbol);
                        i2.putExtras(bundle);
                        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(i2);
                        /*Bundle bundle1 = new Bundle();
                        bundle1.putString("symbol", symbol);
                        CommonUtil.gotoActivityWithData(context, PredictionDetailActivity.class, bundle1);
//                        CommonUtil.gotoActivityWithData(context, PredictionDetailActivity_MPChart.class, bundle1);*/

                        break;
                    default:

                        break;
                }


            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                MyLogger.i(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                MyLogger.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                MyLogger.i(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {

                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    MyLogger.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");

                        MyLogger.i(">>>>>>>>>" + json.optString(myKey));

                        url = json.optString(myKey) + "";

                    }
                } catch (JSONException e) {
                    MyLogger.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

	//send msg to MainActivity
	/*private void processCustomMessage(Context context, Bundle bundle) {
		if (MainActivity.isForeground) {
			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
			if (!ExampleUtil.isEmpty(extras)) {
				try {
					JSONObject extraJson = new JSONObject(extras);
					if (extraJson.length() > 0) {
						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
					}
				} catch (JSONException e) {

				}

			}
			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
		}
	}*/
}
