package communicate;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import client.ui.ControlActivity;
import client.ui.R;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;

public class PushReceiver extends BroadcastReceiver {
	
	@SuppressWarnings("deprecation")
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
				//����͸����Ϣ
				try {
					JSONObject json = new JSONObject(data);
					String type = json.getString("type");
					JSONObject message = json.getJSONObject("data");
					if (type.equals("help")) {
						// ������Ϣ
						Intent i = new Intent(context, ControlActivity.class);
						i.putExtra("type", "help");
						i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
						
						PendingIntent contentIntent = PendingIntent.getActivity(PushConfig.applicationContext, 0, i, 0);
						
						Notification notice = new Notification(R.drawable.ic_launcher, "�յ�һ��������Ϣ", System.currentTimeMillis());
						notice.setLatestEventInfo(context, message.getString("username") + "����������Ϣ", message.getString("content"), contentIntent);
						notice.flags = Notification.FLAG_AUTO_CANCEL;
						
						NotificationManager notifier = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
						notifier.notify(1, notice);
					} else if (type.equals("aid")) {
						// Ԯ����Ϣ
					} else if (type.equals("endhelp")) {
						// ���������¼�
					} else if (type.equals("invite")) {
						// ��������
					} else if (type.equals("remove")) {
						// �Ƴ�����
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			break;
		case PushConsts.GET_CLIENTID:
			//��ȡClientID(CID)
			//������Ӧ����Ҫ��CID�ϴ��������������������ҽ���ǰ�û��˺ź�CID���й������Ա��պ�ͨ���û��˺Ų���CID������Ϣ����
			PushConfig.clientId = bundle.getString("clientid");
			PushSender.sendClientId();
			break;
		default:
			break;
		}
	}
	
}