package fragment;

import routeplan.RoutePlanDemo;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import client.ui.CloseActivity;
import client.ui.DetailMessageActivity;
import client.ui.R;
import client.ui.SendAssistMsgActivity;

public class BottomButtonFragment extends Fragment {
	
	private View messageLayout;
	private TextView navigation; //导航按钮
	private TextView assist;    //援助按钮
	private TextView conclude;  //结束按钮
	
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
       
    }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState){
		messageLayout = inflater.inflate(R.layout.messagedetail_bottom, container, false);  
		
		navigation=(TextView)messageLayout.findViewById(R.id.button_navigate);
		assist = (TextView)messageLayout.findViewById(R.id.button_assist);
		conclude = (TextView)messageLayout.findViewById(R.id.button_conclude);
		
		
		//监听事件
	    navigation.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v){
	        	Intent intent = new Intent();
        		Bundle bundle = new Bundle();
        		bundle.putDouble("longitude",DetailMessageActivity.Getlongitude());
        		bundle.putDouble("latitude",DetailMessageActivity.Getlatitude());
        		intent.putExtras(bundle);
        		intent.setClass(getActivity(),RoutePlanDemo.class);
	        	startActivity(intent);
	        }
	    });
	    
	    assist.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v){
	        	startActivity(new Intent(getActivity(),SendAssistMsgActivity.class));
	        }
	    });
	    
	    conclude.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v){
	        	startActivity(new Intent(getActivity(),CloseActivity.class));
	        }
	    });
	    
        return messageLayout;
	}
	
    public void onPause()
    {
        // TODO Auto-generated method stub
    	
        super.onPause();
    }

}
