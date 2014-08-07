package routeplan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import client.ui.R;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviPara;

public class NaviDemo extends Activity {

	
	
	// �찲������
	public double mLat1;
	public double mLon1;
	// ���ݻ�վ����
	public double mLat2 = 23.154868;
	public double mLon2 = 113.264213;
	
	
	
	// ��λ���
	LocationClient mLocClient;
	public MyLocationListenner myListener;
	boolean isFirstLoc = true;// �Ƿ��״ζ�λ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		// ��λ��ʼ��
		myListener = new MyLocationListenner();
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// ��gps
		option.setCoorType("bd09ll"); // ������������
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
			
		setContentView(R.layout.activity_navi_demo);			
		
		
		
	}
	
	/**
	 * ��λSDK��������
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			
			//if (isFirstLoc) {
			  //isFirstLoc = false;
			  mLat1=location.getLatitude();
			  mLon1=location.getLongitude();
			  
			  TextView text = (TextView) findViewById(R.id.navi_info);
			  text.setText(String.format("���:(%f,%f)\n�յ�:(%f,%f)", mLat1, mLon1,
						mLat2, mLon2));
			  
			//}
			
			
			// map view ���ٺ��ڴ����½��յ�λ��
			//if (location == null || mMapView == null)
				//return;
			//MyLocationData locData = new MyLocationData.Builder()
					//.accuracy(location.getRadius())
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
					//.direction(100).latitude(location.getLatitude())
					//.longitude(location.getLongitude()).build();
			//mBaiduMap.setMyLocationData(locData);
			//if (isFirstLoc) {
				//isFirstLoc = false;
				//LatLng ll = new LatLng(location.getLatitude(),
						//location.getLongitude());
				//MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				//mBaiduMap.animateMapStatus(u);
			//}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
		


	}

	/**
	 * ��ʼ����
	 * 
	 * @param view
	 */
	public void startNavi(View view) {
		LatLng pt1 = new LatLng(mLat1, mLon1);
		LatLng pt2 = new LatLng(mLat2, mLon2);
		
		// ���� ��������
		NaviPara para = new NaviPara();
		para.startPoint = pt1;
		para.startName = "�����￪ʼ";
		para.endPoint = pt2;
		para.endName = "���������";

		try {

			BaiduMapNavigation.openBaiduMapNavi(para, this);

		} catch (BaiduMapAppNotSupportNaviException e) {
			e.printStackTrace();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("����δ��װ�ٶȵ�ͼapp��app�汾���ͣ����ȷ�ϰ�װ��");
			builder.setTitle("��ʾ");
			builder.setPositiveButton("ȷ��", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					BaiduMapNavigation.getLatestBaiduMapApp(NaviDemo.this);
				}
			});

			builder.setNegativeButton("ȡ��", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});

			builder.create().show();
		}
	}

	public void startWebNavi(View view) {
		LatLng pt1 = new LatLng(mLat1, mLon1);
		LatLng pt2 = new LatLng(mLat2, mLon2);
		// ���� ��������
		NaviPara para = new NaviPara();
		para.startPoint = pt1;
		para.endPoint = pt2;
		BaiduMapNavigation.openWebBaiduMapNavi(para, this);
	}
	
	
}
