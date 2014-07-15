package communicate;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import client.ui.MainActivity;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;

public class PushReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Log.d("GetuiSdk", "onReceive() action=" + bundle.getInt("action"));
		switch (bundle.getInt(PushConsts.CMD_ACTION)) {
		
		case PushConsts.GET_MSG_DATA:
			// ��ȡ͸������
			// String appid = bundle.getString("appid");
			byte[] payload = bundle.getByteArray("payload");
			
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");
			
			// smartPush��������ִ���ýӿڣ�action��ΧΪ90000-90999���ɸ���ҵ�񳡾�ִ��
			boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
			System.out.println("��������ִ�ӿڵ���" + (result ? "�ɹ�" : "ʧ��"));
			
			if (payload != null) {
				String data = new String(payload);
				
				Log.d("GetuiSdk", "Got Payload:" + data);
				//TODO:����͸����Ϣ
				showNotification("Title", data, MainActivity.class);
			}
			break;
		case PushConsts.GET_CLIENTID:
			//��ȡClientID(CID)
			//������Ӧ����Ҫ��CID�ϴ��������������������ҽ���ǰ�û��˺ź�CID���й������Ա��պ�ͨ���û��˺Ų���CID������Ϣ����
			PushConfig.clientId = bundle.getString("clientid");
			syncClientId();
			break;
		default:
			break;
		}
	}
	
	/**
	 * �ϴ�clientId
	 */
	public static void syncClientId() {
	}
	
	/**
	 * ��ʾ֪ͨ
	 * @param title
	 * @param text
	 */
	public static void showNotification(String title, String text, Class<?> cls) {
		NotificationManager notifier = (NotificationManager) PushConfig.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notify = new Notification(R.drawable.btn_default, title, System.currentTimeMillis());
		notify.flags = Notification.FLAG_AUTO_CANCEL;
		Intent main = new Intent(PushConfig.applicationContext, cls);
		PendingIntent intent = PendingIntent.getActivity(PushConfig.applicationContext, 0, main, 0);
		notify.setLatestEventInfo(PushConfig.applicationContext, title, text, intent);
		notifier.notify(1, notify);
	}
}