package client.ui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.SearchFriendListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;



public class Findfriendresult extends Activity {
	
	private ListView list;
	Button add_btn;
	private ArrayList<Map<String,Object>> listItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		
		list = (ListView)findViewById(R.id.search_results);
		add_btn = (Button)findViewById(R.id.addfriend);
		
		listItem = new ArrayList<Map<String,Object>>();
		
		setData();
		
		SearchFriendListAdapter listAdapter = new SearchFriendListAdapter(this, listItem);
	
		list.setAdapter(listAdapter); 
		
	}
	
	private void setData()
	{
		Map<String, Object> map = new HashMap<String, Object>();   
	    map.put("img", R.drawable.img00);   
	    map.put("name", "张三");
	    map.put("detail", "Good job!");
        listItem.add(map); 
	    map = new HashMap<String, Object>();   
	    map.put("img", R.drawable.img00);   
	    map.put("name", "张三");
	    map.put("detail", "Good job!");
        listItem.add(map); 
	    map = new HashMap<String, Object>();   
	    map.put("img", R.drawable.img00);   
	    map.put("name", "张三");
	    map.put("detail", "Good job!");
        listItem.add(map); 
	    map = new HashMap<String, Object>();   
	    map.put("img", R.drawable.img00);   
	    map.put("name", "张三");
	    map.put("detail", "Good job!");
        listItem.add(map);    
	}


	
	

}
