package communicate;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;

public class PushReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Log.d("GetuiSdk", "onReceive() action=" + bundle.getInt("action"));
		switch (bundle.getInt(PushConsts.CMD_ACTION)) {
		
		case PushConsts.GET_MSG_DATA:
			// 获取透传数据
			// String appid = bundle.getString("appid");
			byte[] payload = bundle.getByteArray("payload");
			
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");
			
			// smartPush第三方回执调用接口，action范围为90000-90999，可根据业务场景执行
			boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
			System.out.println("第三方回执接口调用" + (result ? "成功" : "失败"));
			
			if (payload != null) {
				String data = new String(payload);
				
				Log.d("GetuiSdk", "Got Payload:" + data);
				//处理透传消息
				try {
					JSONObject json = new JSONObject(data);
					String type = json.getString("type");
					JSONObject message = json.getJSONObject("data");
					Intent i = new Intent();
					if (type.equals("help")) {
						// 求助信息
						i.setAction("helpmessage");
						i.putExtra("username", message.getString("username"));
						i.putExtra("content", message.getString("content"));
						i.putExtra("time", message.getString("time"));
						i.putExtra("kind", message.getInt("kind"));
						i.putExtra("audio", message.getInt("audio"));
						i.putExtra("video", message.getInt("video"));
						i.putExtra("eventid", message.getInt("eventid"));
						i.putExtra("userid", message.getInt("userid"));
					} else if (type.equals("aid")) {
						// 援助信息
						i.setAction("aidmessage");
						i.putExtra("username", message.getString("username"));
						i.putExtra("content", message.getString("content"));
						i.putExtra("time", message.getString("time"));
						i.putExtra("audio", message.getString("audio"));
						i.putExtra("video", message.getString("video"));
						i.putExtra("eventid", message.getInt("eventid"));
						i.putExtra("userid", message.getInt("userid"));
					} else if (type.equals("endhelp")) {
						// 结束求助事件
						i.setAction("finishevent");
						i.putExtra("eventid", message.getInt("eventid"));
						i.putExtra("time", message.getString("time"));
					} else if (type.equals("invite")) {
						// 添加好友请求
						i.setAction("addfriend");
						i.putExtra("username", message.getString("username"));
						i.putExtra("info", message.getString("info"));
						i.putExtra("userid", message.getInt("userid"));
					} else if (type.equals("remove")) {
						// 移除好友（被拉入黑名单）
						i.setAction("removefriend");
						i.putExtra("userid", message.getInt("userid"));
					}
					context.sendBroadcast(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			break;
		case PushConsts.GET_CLIENTID:
			//获取ClientID(CID)
			//第三方应用需要将CID上传到第三方服务器，并且将当前用户账号和CID进行关联，以便日后通过用户账号查找CID进行消息推送
			PushConfig.clientId = bundle.getString("clientid");
			PushSender.sendClientId();
			break;
		default:
			break;
		}
	}
	
}