package communicate;

import com.igexin.sdk.PushManager;

import android.app.Activity;
import android.content.Context;

public class PushConfig {
	//��������ַ
	public static final String SERVICEURL = "http://114.215.133.61:8080/api/";
	
	
	//���±����Զ�ά��
	public static Context applicationContext = null;
	public static String clientId = "";
	
	public static void init(Activity mContext) {
		PushConfig.applicationContext = mContext.getApplicationContext();
        PushManager.getInstance().initialize(mContext.getApplicationContext());
	}
}