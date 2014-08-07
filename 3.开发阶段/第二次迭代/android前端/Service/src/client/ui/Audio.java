package client.ui;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class Audio extends Activity {
	private Button btn_audio_start, btn_audio_stop;
	private MediaRecorder	mMediaRecorder;
	private String			strTempFile	= "audio_";
	private String SD_CARD_TEMP_DIR = getSDPath() + File.separator + "myaudio" + File.separator;
	/* ¼�Ƶ���Ƶ�ļ� */
	private File			mRecAudioFile;
	private File			mRecAudioPath;
	private File			dir;
	private Chronometer timer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audio);
		//�޸�
		//button id
		btn_audio_start = (Button) findViewById(R.id.audio_start);
		btn_audio_stop = (Button) findViewById(R.id.audio_stop);
		dir = new File(SD_CARD_TEMP_DIR);
		if (!dir.exists()) {
			dir.mkdir();
		}
		/*�ҵ���ʱ��*/
		timer = (Chronometer) findViewById(R.id.chronometer);
		timer.setFormat("��ʱ��%s"); 
			
		btn_audio_start.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				recorder();		
			}
		} );
		
		btn_audio_stop.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				try{
				if(mRecAudioFile != null){
					/* ֹͣ¼�� */
					mMediaRecorder.stop();
					/* �ͷ�MediaRecorder */
					mMediaRecorder.release();
					mMediaRecorder = null;
					btn_audio_start.setText("����¼��");
					/*ֹͣ��ʱ*/
					timer.stop();
				}
				
				Intent data=new Intent();  
	            data.putExtra("audio_file", mRecAudioFile.getAbsolutePath());
	            //�����������Լ����ã���������audio=20  
	            setResult(20, data);  
	            //�رյ����Activity  
	            finish();  
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
	}

	protected void recorder(){
		try{
			/* ����Ƿ����SD�� */
			if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
			{
				/* �õ�SD����·�� */
				mRecAudioPath = Environment.getExternalStorageDirectory();
				//Toast.makeText(getApplicationContext(), "SD������", Toast.LENGTH_LONG).show();
				
			}
			else
			{
				Toast.makeText(getApplicationContext(), "û��SD��", Toast.LENGTH_LONG).show();
			}
			
			Toast.makeText(getApplicationContext(), "SDcard:" + mRecAudioPath, Toast.LENGTH_SHORT).show();
			/*createTempFile(String prefix, String suffix, File directory)��
			��ָ��Ŀ¼�д���һ���µĿ��ļ���ʹ�ø�����ǰ׺�ͺ�׺�ַ������������ơ�*/
			mRecAudioFile = File.createTempFile(strTempFile, ".amr", dir);
			//Toast.makeText(getApplicationContext(), "��ʼ¼��......", Toast.LENGTH_SHORT).show();
			/*ʵ����*/
			mMediaRecorder = new MediaRecorder();
			mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
			mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			mMediaRecorder.setOutputFile(mRecAudioFile.getAbsolutePath());
			mMediaRecorder.prepare();
			mMediaRecorder.start();
			btn_audio_start.setText("����¼��");
			/*��ʱ���ȸ�λ��������*/
			timer.setBase(SystemClock.elapsedRealtime());
			timer.start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // �ж�sd���Ƿ����
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// ��ȡ��Ŀ¼
		} else {
			return null;
		}
		//System.out.println("sd·���ǣ�" + sdDir.toString());
		return sdDir.toString();

	}

}
