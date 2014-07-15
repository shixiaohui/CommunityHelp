package client.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.igexin.sdk.PushManager;
import communicate.PushConfig;

public class MainActivity extends Activity implements OnClickListener{

	Button loginBtn,registBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        loginBtn = (Button)findViewById(R.id.main_login_btn);
        registBtn = (Button)findViewById(R.id.main_regist_btn);
        loginBtn.setOnClickListener(this);
        registBtn.setOnClickListener(this);
        PushConfig.applicationContext = this.getApplicationContext();
        PushManager.getInstance().initialize(this.getApplicationContext());
    }
	@Override
	public void onClick(View v) {
		int btnId = v.getId();
		switch (btnId) {//�жϵ���İ�ť
		case 	R.id.main_login_btn://��¼��ť
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(intent);//������Ӧ��Activity  �˴�ΪӲ����  ��������ôд  д��action��ʽ ���
				Log.i("-------------", "------------------");
				break;

		case 	R.id.main_regist_btn://ע�ᰴť
				startActivity(new Intent(MainActivity.this, RegisterActivity.class));
				break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
