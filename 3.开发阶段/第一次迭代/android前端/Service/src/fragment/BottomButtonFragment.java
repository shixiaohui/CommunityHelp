package fragment;

import routeplan.RoutePlan;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import client.ui.CloseActivity;
import client.ui.R;
import client.ui.SendHelpMsgActivity;

public class BottomButtonFragment extends Fragment {
	private View messageLayout;
	private Button navigation,close,help;
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
       
    }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState){
		messageLayout = inflater.inflate(R.layout.messagedetail_bottom, container, false);  
		navigation=(Button)messageLayout.findViewById(R.id.button_navigate);
	    navigation.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v){
	        	startActivity(new Intent(getActivity(),RoutePlan.class));
	        }
	    });
	    close=(Button)messageLayout.findViewById(R.id.button_conclude);
	    close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),CloseActivity.class));
			}
		});
	    
	    help=(Button)messageLayout.findViewById(R.id.button_assist);
	    help.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),SendHelpMsgActivity.class));
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
