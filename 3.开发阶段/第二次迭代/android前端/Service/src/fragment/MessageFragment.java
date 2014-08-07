package fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.MessageAdapter;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import client.ui.R;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import communicate.PushConfig;
import communicate.PushSender;

public class MessageFragment extends Fragment{
	private PullToRefreshListView mListView;
	private MessageAdapter myadapter;
	private View view;
	private final Handler handler = new Handler();
	private GetMessager getmessager;
	private List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
	private Map<String, Object> data = new HashMap<String, Object>();
	private Integer[] imgeIDs = {R.drawable.cake,    
            R.drawable.gift, R.drawable.letter,   
            R.drawable.love, R.drawable.mouse,   
            R.drawable.music};  
	private String[] name = {"����", "����",    
            "��Ʊ", "����", "���", "����CD"}; 
	
	private String[] time = {
			 "2011.02.12",
			 "2041.11.21",
			 "2012.03.24",
			 "2014.05.12",
			 "2012.07.15",
			 "2013.06.22"};
	 
	 private String[] content = {   
	            "����úóԡ�����úóԡ�����úóԡ�����úóԡ�����úóԡ�����úóԡ�",    
	            "�������ء��������ء��������ء��������ء��������ء��������ء��������ء��������ء�",    
	            "�������硣�������硣�������硣�������硣",    
	            "���綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а���",   
	            "��Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ�",   
	            "�������֡��������֡��������֡��������֡��������֡��������֡��������֡��������֡��������֡��������֡�"}; 
	
	public MessageFragment(){
		
	}
	@Override
    public View onCreateView(LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) { 
		
		ViewGroup p = (ViewGroup) view.getParent(); 
        if (p != null) { 
            p.removeAllViewsInLayout(); 
        } 	      
		return view;
   }
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		 
		super.onCreate(savedInstanceState);
		view=View.inflate(getActivity(),R.layout.pull_to_refreshlist,null); 
	    mListView=(PullToRefreshListView)view.findViewById(R.id.pull_to_refresh_list);   
	    myadapter=new MessageAdapter(getActivity());    
	    myadapter.setData(datalist);
	    mListView.setAdapter(myadapter);
		// ����PullToRefresh	    
		mListView.setMode(Mode.BOTH);
		mListView.setOnRefreshListener(new OnRefreshListener2<ListView>(){
		 
		    // ����Pulling Down
		    @Override
		    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		    	// ������ʱ����������
		    	getmessager=new GetMessager();
		    	getmessager.execute();
		    	handler.postDelayed(new Runnable() {
					public void run() {
						mListView.onRefreshComplete();
					}
				}, 1000);
		    }
		            
		    // ����Pulling Up
		    @Override
		    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		    		 // ������ʱ�����ѡ��
		    	handler.postDelayed(new Runnable() {
					public void run() {
						mListView.onRefreshComplete();
					}
				}, 1000);
		    }
		
		});	
	}
	
	private class GetMessager extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) { 
        	datalist.clear();
        	data.put("username", PushConfig.username);
        	Log.i("test","test");
            return PushSender.sendMessage("getAround",data);
        }
        @Override
        protected void onPreExecute() {   
        	
        }
        @Override
        protected void onPostExecute(String result) {  
        	Log.i("test",result);
        	if(result.equals("network error")){
        		Toast.makeText(getActivity(),"����û������", Toast.LENGTH_SHORT).show();
            	//pro.setVisibility(View.INVISIBLE);
        	}
        	if(result.equals("error")){
        		Toast.makeText(getActivity(),"���ӷ�����ʧ��", Toast.LENGTH_SHORT).show();
            	//pro.setVisibility(View.INVISIBLE);
        	}
            try {
            	JSONObject json = new JSONObject(result);
            	Log.i("test","test1");
            	switch (json.getInt("state")) {
            	
            	case 1:
            		JSONArray array = json.getJSONArray("aids");
            		Log.i("test",array.toString());
            		for (int i = 0; i < array.length(); i++) {
            			Map<String, Object> map = new HashMap<String, Object>();    
            	        map.put("image", imgeIDs[0]);                 
            	        map.put("name", array.getJSONObject(i).getString("name"));              
            	        map.put("time",array.getJSONObject(i).getString("starttime"));             
            	        map.put("content", array.getJSONObject(i).getString("content"));
            	        map.put("eid", array.getJSONObject(i).getString("id"));
            	        datalist.add(map);    
            		}
            		myadapter.notifyDataSetChanged();
            	default:
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