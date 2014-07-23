package client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.AssistListViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import communicate.PushConfig;
import communicate.PushSender;

public class DetailMessageActivity extends Activity {
	
	//求助视图
	public final class FirstItemView{
		public ImageView image;
		public TextView name;
		public TextView time;
		public TextView content;
		public Button concern;
		public Button assist;
	}
	
	FirstItemView firstItemView;
	
	private Map<String,Object> data=new HashMap<String, Object>();
	private Addassist addassist;
	private ListView listView;  
	private AssistListViewAdapter assistListViewAdapter;
	private List<Map<String, Object>> datalist=new ArrayList<Map<String, Object>>(); 
	private Bundle bundle;
	private GetAssist getassist;
	private static String eid;
	private static double longitude=0,latitude=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messagedetail);

		View firstView = View.inflate(DetailMessageActivity.this, R.layout.help_item, null);
		bundle=this.getIntent().getExtras();
		eid=bundle.getString("eid");
		
		firstItemView = new FirstItemView();
		
		firstItemView.image = (ImageView)firstView.findViewById(R.id.imageItem1);
		firstItemView.name = (TextView)firstView.findViewById(R.id.nameItem1);   
		firstItemView.time = (TextView)firstView.findViewById(R.id.timeItem1);   
		firstItemView.content= (TextView)firstView.findViewById(R.id.contentItem1);   
		firstItemView.concern = (Button)firstView.findViewById(R.id.concernBut1);
		firstItemView.assist = (Button)firstView.findViewById(R.id.assistBut1);
		
		firstItemView.image.setBackgroundResource(R.drawable.shopping);
		firstItemView.name.setText(bundle.getString("needhelp").toString());
		firstItemView.time.setText(bundle.getString("time").toString());
		firstItemView.content.setText(bundle.getString("content").toString());
		firstItemView.concern.setText("关注(0)");
		firstItemView.assist.setText("帮助 (0)");
		
		
		firstItemView.concern.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int numConcern = Integer.parseInt(getNumStr(firstItemView.concern.getText().toString()));
				numConcern ++;
				firstItemView.concern.setText("关注(" + Integer.toString(numConcern) + ")");
			}	
		});	
		
		firstItemView.assist.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int numConcern = Integer.parseInt(getNumStr(firstItemView.assist.getText().toString()));
				numConcern ++;
				firstItemView.assist.setText("帮助(" + Integer.toString(numConcern) + ")");
				addassist=new Addassist();
				addassist.execute();
			}	
		});	
		
		listView = (ListView)findViewById(R.id.assist_list);
		assistListViewAdapter = new AssistListViewAdapter(this, datalist);
		
		
		listView.addHeaderView(firstView);
		listView.setAdapter(assistListViewAdapter);
		getassist=new GetAssist();
		getassist.execute();
		
	}
	

	private String getNumStr(String string) {
		// TODO Auto-generated method stub
		
		StringBuffer str = new StringBuffer();
		boolean flag = false;
		for (int i=0; i<string.length(); i++)
		{
			if (string.charAt(i) == '('){
				flag = true;
				continue;
			}
			
			if (flag && string.charAt(i) != ')'){
				str.append(string.charAt(i));
				System.out.println(string.charAt(i));
			}
		}
		
		return str.toString();
	}
	
	private class GetAssist extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) { 
        	data.clear();
        	data.put("eventid",bundle.get("eid").toString()); 	
        	datalist.clear();
            String result=PushSender.sendMessage("event",data);
            try {
				JSONArray support = new JSONObject(result).getJSONArray("support");
				for (int i=0; i<support.length(); i++) {
					Map<String, Object> map = new HashMap<String, Object> ();
					map.put("image",R.drawable.img01);
					map.put("name", support.getJSONObject(i).getString("username"));
					map.put("time", support.getJSONObject(i).getString("time"));
					map.put("content", support.getJSONObject(i).getString("content"));
					datalist.add(map);
				}
				longitude=new JSONObject(result).getJSONObject("event").getDouble("longitude");
				latitude=new JSONObject(result).getJSONObject("event").getDouble("latitude");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return "true";
        }
        @Override
        protected void onPreExecute() {   
        	
        }
        @Override
        protected void onPostExecute(String result) {   	
        	assistListViewAdapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }
    }
	public static String GetEid(){
		return eid;
	}
	public static Double Getlongitude(){
		return longitude;
	}
	public static Double Getlatitude(){
		return latitude;
	}
	
	private class Addassist extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) { 
        	data.clear();
        	data.put("username",PushConfig.username); 
            data.put("eventid",bundle.getString("eid"));
            return PushSender.sendMessage("addaid",data);
        }
        @Override
        protected void onPreExecute() {   
        	
        }
        @Override
        protected void onPostExecute(String result) {   	
        	if(result.equals("network error")){
        		Toast.makeText(DetailMessageActivity.this,"您还没有联网", Toast.LENGTH_SHORT).show();
            	//pro.setVisibility(View.INVISIBLE);
        	}
        	if(result.equals("error")){
        		Toast.makeText(DetailMessageActivity.this,"连接服务器失败", Toast.LENGTH_SHORT).show();
            	//pro.setVisibility(View.INVISIBLE);
        	}
            try {
            	switch (new JSONObject(result).getInt("state")) {
            	case 1:
            		Toast.makeText(DetailMessageActivity.this, "成功加入帮助", Toast.LENGTH_SHORT).show();
            		break;
            	case 2:
            		Toast.makeText(DetailMessageActivity.this, "已经加入", Toast.LENGTH_SHORT).show();
            		break;
            	case 3:
            		Toast.makeText(DetailMessageActivity.this, "事件已经结束", Toast.LENGTH_SHORT).show();
            		break;
            	default:
            		Toast.makeText(DetailMessageActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
            	}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            super.onPostExecute(result);
        }
    }
}
