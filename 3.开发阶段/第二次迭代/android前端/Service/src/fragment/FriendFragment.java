package fragment;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import adapter.FriendAdapter;
import android.app.AlertDialog;
import android.content.ClipData.Item;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageButton;
import base.friend;
import client.ui.FriendInfoActivity;
import client.ui.R;

public class FriendFragment extends Fragment implements OnChildClickListener{
	
	private ExpandableListView mListView = null; 
    private FriendAdapter mAdapter = null; 
    private List<List<friend>> mData = new ArrayList<List<friend>>(); //表示用户信息的列表
    private EditText mSearchEditText = null;
    private ImageButton deleteBtn;
    private View view;
    
    //表示定义的四个分组
    private String[] mGroupArrays = new String[] {  
            "亲人", 
            "好友",  
            "陌生人",
            "黑名单"
    }; 
    
    //每个分组下的子项
    private String[][] mGroupArraysChild = new String[][] {
    	{"张三", "李四"}, 
    	{"王二", "麻子"}, 
    	{"小明", "小莫"},
    	{"周杰伦", "奥巴马"}
    };
 
    //针对每个子项的描述，类似于个性签名
    private String[][] mDetail = new String[][] {  
    		{"美丽的兴义", "漂亮的遵义"}, 
    		{"下雨的珠海", "知名的中山"}, 
        	{"热辣的长沙", "未知的娄底"},
        	{"我真的是周杰伦", "我是奥巴马"}
    }; 
 
    //设置每个子项的图片
    private int[][] mImageIds = new int[][] { 
            { R.drawable.img00,    
              R.drawable.img01 }, 
            { R.drawable.img10,   
              R.drawable.img11 }, 
            { R.drawable.img20, 
              R.drawable.img21 },
            { R.drawable.img30, 
              R.drawable.img31 }
    };
	public FriendFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {       
        //return inflater.inflate(R.layout.fragment_3, container, false);
		ViewGroup p = (ViewGroup) view.getParent(); 
        if (p != null) { 
            p.removeAllViewsInLayout(); 
        } 

		return view;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		view=View.inflate(getActivity(),R.layout.friend,null); 
		mListView = (ExpandableListView)view.findViewById(R.id.friendlist);
        //mListView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); 
        mAdapter = new FriendAdapter(getActivity(), mData, mGroupArrays);
        mListView.setAdapter(mAdapter); 
        //mListView.setDescendantFocusability(ExpandableListView.FOCUS_AFTER_DESCENDANTS); 
        //mListView.setOnChildClickListener(this);
        mListView.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int which, long id) {
				// TODO Auto-generated method stub
				final String[] friend_manage = new String[]{"查看亲友","删除亲友"};
				new AlertDialog.Builder(getActivity())
						.setItems(friend_manage, new DialogInterface.OnClickListener() {		
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								if(which==0)
									startActivity(new Intent(getActivity(),FriendInfoActivity.class));
								else if(which==1)
									;
							}
						}).show();
				return true;
			}
        });
        
		initData(); 
		mSearchEditText = (EditText)view.findViewById(R.id.search);
        mSearchEditText.addTextChangedListener(new TextWatcher() {
        	//当输入框的文字改变时，执行以下方法。
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                            int count) {
            	//当输入文字的时候才显示删除按钮
            	if(s.toString().isEmpty())
            	{
            		deleteBtn.setVisibility(View.GONE);
            	}
            	else 
            	{
            		deleteBtn.setVisibility(View.VISIBLE);
            	}
            	
                FriendAdapter adapter = null;
                List<List<friend>> data = new ArrayList<List<friend>>();
                for(int i = 0; i < mData.size(); i++)
                {
                	List<friend> list = new ArrayList<friend>(); 
                	for(int j = 0; j < mData.get(i).size(); j++)
                	{
                		//查询满足条件的联系人
                		String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(mData.get(i).get(j).getName().charAt(0));
                		if(mData.get(i).get(j).getName().toLowerCase().indexOf(s.toString()) != -1 ||
                				mData.get(i).get(j).getName().toUpperCase().indexOf(s.toString()) != -1 ||
                				(pinyin != null && pinyin[0].charAt(0) == s.toString().toLowerCase().charAt(0))
                				)
                		{
                			list.add(mData.get(i).get(j));
                		}
                	}
                	
                	data.add(list);
                }
                
                adapter = new FriendAdapter(getActivity(), data, mGroupArrays); 
                mListView.setAdapter(adapter);
                
                if(s.toString().isEmpty())
                	mListView.setAdapter(mAdapter);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                            int after) {
                    // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                    // TODO Auto-generated method stub

            }
		});
        
        //删除按钮，删除查询内容
        deleteBtn = (ImageButton)view.findViewById(R.id.delete_button);
        deleteBtn.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN)
				{				
					((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.delete_icon_down));
				}
				else if(event.getAction() == MotionEvent.ACTION_UP)
				{
					((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.delete_icon));
					mSearchEditText.setText(null);
				}
				return false;
			}
		});
    
	}
	@Override
	public boolean onChildClick(ExpandableListView parent, View v, 
            int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		friend item = mAdapter.getChild(groupPosition, childPosition); 
        new AlertDialog.Builder(getActivity()) 
                .setTitle(item.getName()) 
                .setMessage(item.getDetail()) 
                .setIcon(android.R.drawable.ic_menu_more) 
                .setNegativeButton("取消", 
                        new OnClickListener() { 
                            @Override 
                            public void onClick(DialogInterface dialog, 
                                    int which) { 
                                // TODO Auto-generated method stub 
 
                            } 
                        }).create().show(); 
        return true; 
	}
	
	//初始化mData的内容
	private void initData() {     
        for(int i = 0; i < mGroupArrays.length; i++)
        {
        	List<friend> list = new ArrayList<friend>(); 
        	for(int j = 0; j < 2; j++)
        	{
        		friend item = new friend(mImageIds[i][j], mGroupArraysChild[i][j], mDetail[i][j]);
        		list.add(item);
        	}
        	mData.add(list);
        }
    } 
	
}