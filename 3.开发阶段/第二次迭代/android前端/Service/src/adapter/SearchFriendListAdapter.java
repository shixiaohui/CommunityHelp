package adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import client.ui.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

//�½�һ���࣬������дBaseExpandableListAdapter�Լ��ṩ������Դ
public class SearchFriendListAdapter extends BaseAdapter {

	
	private LayoutInflater mInflater;  
	private ArrayList<Map<String,Object>> listItem;
	Context con;
	

	public SearchFriendListAdapter(Context context, ArrayList<Map<String,Object>> list) {  

         this.mInflater = LayoutInflater.from(context);  
         listItem = list;
         con = context;
    }  

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        ViewHolder holder = null;  
            if (convertView == null) {  
                  
                holder=new ViewHolder();    
                  
                //�������Ϊ��vlist��ȡview  ֮���view���ظ�ListView   
                  
                convertView = mInflater.inflate(R.layout.search_result_item, null);
                holder.img = (ImageView)convertView.findViewById(R.id.add_image);
                holder.name = (TextView)convertView.findViewById(R.id.item_name);  
                holder.info = (TextView)convertView.findViewById(R.id.item_detail);  
                holder.add_Btn = (ImageButton)convertView.findViewById(R.id.addfriend);  
                convertView.setTag(holder);               
            }else {               
                holder = (ViewHolder)convertView.getTag();  
            }         
            
            holder.img.setTag(position);
            holder.name.setText((String)listItem.get(position).get("name"));  
            holder.info.setText((String)listItem.get(position).get("detail"));  
            holder.add_Btn.setTag(position);  
            //��Button��ӵ����¼�  ���Button֮��ListView��ʧȥ����  ��Ҫ��ֱ�Ӱ�Button�Ľ���ȥ��   
            holder.add_Btn.setOnClickListener(new View.OnClickListener() {  
                  
                @Override  
                public void onClick(View v) {  
                    showInfo(position);                   
                }  
            }); 
            
            holder.img.setOnClickListener(new View.OnClickListener() {  
                  
                @Override  
                public void onClick(View v) {  
                                   
                }  
            }); 
             
            //holder.viewBtn.setOnClickListener(MyListener(position));   
                      
            return convertView; 
	}
	
	
	//��ȡ���������   
	    public final class ViewHolder {  
	    	public ImageView img;
	        public TextView name;  
	        public TextView info;  
	        public ImageButton add_Btn;  
	    }  
	    
	    public void showInfo(int position){  
	    	          
			LayoutInflater inflater = mInflater;
			   View layout = inflater.inflate(R.layout.addfriendcheck, null);
			   AlertDialog a = new AlertDialog.Builder(con)
			   .setTitle("��Ӻ���").setView(layout)
			   .setPositiveButton("ȷ��", null)
			   .setNegativeButton("ȡ��", null).show();
	   } 
	
}
