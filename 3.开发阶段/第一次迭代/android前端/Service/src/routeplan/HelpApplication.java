package routeplan;

import android.app.Application;
import android.os.Vibrator;
import android.widget.TextView;

import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;

public class HelpApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(this);
	}
	
}