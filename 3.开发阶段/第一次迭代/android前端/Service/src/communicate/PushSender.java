package communicate;

import java.util.Map;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class PushSender {
	
	/**
	 * ����ģ��
	 */
	public static final String REGISTER = "register"; // ע��
	public static final String LOGIN = "login"; // ��¼
	public static final String USER_AUTHENTICATION = "userauthentication"; // �û���֤
	public static final String LOGOUT = "logout"; // �ǳ�
	public static final String CANCEL = "cancel"; // ע��
	public static final String CHECK_RELATIVES = "checkrelatives"; // �鿴�����б�
	public static final String DELETE_RELATIVES = "deleterelatives"; // ɾ������
	public static final String ADD_RELATIVES = "addrelatives"; // �������
	public static final String CHECK_HISTORY = "history"; // �鿴��Ϣ��ʷ��¼
	public static final String SEND_HELP_MESSAGE = "pophelpmessage"; // ����������Ϣ
	public static final String ADD_SUPPORT_MESSAGE = "supportmessage"; // ��Ӹ�����Ϣ
	public static final String END_HELP = "finish"; // ��������
	public static final String GIVE_CREDIT = "givecredit"; // ����
	public static final String ADD_AID = "addaid"; // ������� 
	public static final String SEND_SUPPORT_MESSAGE = "sendsupport"; // ����Ԯ����Ϣ
	public static final String QUIT_AID = "quitaid"; // �˳�����
	
	/**
	 * ���������������
	 * @param action
	 * @param map
	 * @return
	 */
	public static String sendMessage(String action, Map<String, Object> map) {
		if (isNetworkConnected()) {
			return GetuiSdkHttpPost.httpPost(action, map);
		} else {
			//��鱾������
			return "network error";
		}
	}
	
	/**
	 * �ж������Ƿ�����
	 * @return
	 */
	public static boolean isNetworkConnected() {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) PushConfig.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		
		if (mNetworkInfo != null) {
			return mNetworkInfo.isAvailable();
		}
		return false;
	}
}