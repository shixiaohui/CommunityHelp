package communicate;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import client.ui.R;

import com.igexin.sdk.PushManager;

public class PushConfig {
	public static final String SERVICEURL = "http://114.215.133.61:8080/api/"; // ��������ַ
	public static final int CONNECTION_TIMEOUT_INT = 8000; // Ĭ�����ӷ�������ʱʱ��
	public static final int READ_TIMEOUT_INT = 5000; // Ĭ�϶�ȡ���������ݳ�ʱʱ��
	
	// �û�����δ��¼ʱָ��Ϊ���ַ���
	public static String username = "";
	
	public static final int NOTIFICATION_EVENT = 0; // �¼�ϵ��֪ͨ
	public static final int NOTIFICATION_FRIEND = 1; // ����ϵ��֪ͨ
	public static final int NOTIFICATION_END_EVENT = 2; // �����¼�֪ͨ
	
	public static List<String> endevents = new ArrayList<String>();
	
	public static int helpmessage = 0; // δ��������Ϣ��������������֪ͨ����ʾ
	public static int aidmessage = 0; // δ��Ԯ����Ϣ��������������֪ͨ����ʾ
	public static boolean notifyevent = true; // �յ�������Ԯ����Ϣ���Ƿ���֪ͨ����ʾ
	public static int eventid = -1; // ��ǰ�¼�����ҳ���¼�id�����ڸ�ҳ����Ϊ-1
	public static int toevent = -1; // Ҫ��ת��event��id
	
	// ���±����Զ�ά��
	public static Context applicationContext = null;
	public static String clientId = "";
	
	/**
	 * ��ʼ�����ͷ���
	 * @param mContext
	 */
	public static void init(Activity mContext) {
		PushConfig.applicationContext = mContext.getApplicationContext();
        PushManager.getInstance().initialize(mContext.getApplicationContext());
	}
	
	/**
	 * ֹͣ���ͷ���
	 * @param mContext
	 */
	public static void stop(Activity mContext) {
		PushManager.getInstance().stopService(mContext.getApplicationContext());
	}
	
	/**
	 * ��ʾ֪ͨ
	 * @param context
	 * @param tickerText
	 * @param title
	 * @param content
	 * @param intent
	 */
	public static void sendNotification(Context context, String tickerText, String title, String content, Intent intent, int NOTIFY_ID) {
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Builder builder = new Notification.Builder(context);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(contentIntent);
		builder.setSmallIcon(R.drawable.notification_small)
		.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.notification))
		.setTicker(tickerText)
		.setContentTitle(title)
		.setContentText(content)
		.setWhen(System.currentTimeMillis())
		.setAutoCancel(true);
		
		Notification notice = builder.getNotification();
		notice.defaults |= Notification.DEFAULT_SOUND;
		nm.notify(NOTIFY_ID, notice);
	}
	
	/**
	 * ���֪ͨ
	 * @param context
	 */
	public static void clearNotification(Context context, int NOTIFY_ID) {
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(NOTIFY_ID);
	}
}