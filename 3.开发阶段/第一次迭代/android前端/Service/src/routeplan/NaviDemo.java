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

	
	
	// 天安门坐标
	public double mLat1;
	public double mLon1;
	// 广州火车站坐标
	public double mLat2 = 23.154868;
	public double mLon2 = 113.264213;
	
	
	
	// 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener;
	boolean isFirstLoc = true;// 是否首次定位

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		// 定位初始化
		myListener = new MyLocationListenner();
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
			
		setContentView(R.layout.activity_navi_demo);			
		
		
		
	}
	
	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			
			//if (isFirstLoc) {
			  //isFirstLoc = false;
			  mLat1=location.getLatitude();
			  mLon1=location.getLongitude();
			  
			  TextView text = (TextView) findViewById(R.id.navi_info);
			  text.setText(String.format("起点:(%f,%f)\n终点:(%f,%f)", mLat1, mLon1,
						mLat2, mLon2));
			  
			//}
			
			
			// map view 销毁后不在处理新接收的位置
			//if (location == null || mMapView == null)
				//return;
			//MyLocationData locData = new MyLocationData.Builder()
					//.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
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
	 * 开始导航
	 * 
	 * @param view
	 */
	public void startNavi(View view) {
		LatLng pt1 = new LatLng(mLat1, mLon1);
		LatLng pt2 = new LatLng(mLat2, mLon2);
		
		// 构建 导航参数
		NaviPara para = new NaviPara();
		para.startPoint = pt1;
		para.startName = "从这里开始";
		para.endPoint = pt2;
		para.endName = "到这里结束";

		try {

			BaiduMapNavigation.openBaiduMapNavi(para, this);

		} catch (BaiduMapAppNotSupportNaviException e) {
			e.printStackTrace();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					BaiduMapNavigation.getLatestBaiduMapApp(NaviDemo.this);
				}
			});

			builder.setNegativeButton("取消", new OnClickListener() {
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
		// 构建 导航参数
		NaviPara para = new NaviPara();
		para.startPoint = pt1;
		para.endPoint = pt2;
		BaiduMapNavigation.openWebBaiduMapNavi(para, this);
	}
	
	
}
