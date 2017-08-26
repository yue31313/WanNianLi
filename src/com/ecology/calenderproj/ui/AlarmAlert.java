package com.ecology.calenderproj.ui;

import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ecology.calenderproj.R;
  
public class AlarmAlert extends Activity { 
	private Dialog builder;
	private MediaPlayer mp;
	private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tixing);
        mp = new MediaPlayer();
        //����������ʱ��
        new Thread(){
  			public void run(){
  				try {
					Thread.sleep(53000);
					if(mp!=null){
			    		mp.stop();
			    		vibrator.cancel();  
			    	}else if(vibrator!=null){
			    		vibrator.cancel(); 
			    	}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  			}
  		}.start();
        try {
        	//���ŵ�ǰĬ������
			mp.setDataSource(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
			 mp.prepare();
			 mp.setLooping(true);
			 mp.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//������
		vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE); 
		long [] pattern = {700,1300,700,1300};   
        vibrator.vibrate(pattern,2);
        String content=getIntent().getExtras().getString("content");
        TextView dialog_content=(TextView) findViewById(R.id.dialog_content);
		Button dialog_button_cancel=(Button)findViewById(R.id.dialog_button_cancel);
		dialog_content.setText(content);
  		dialog_button_cancel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
    } 
    @Override
    protected void onStop() {
    	ScheduleViewAddActivity.setAlart(AlarmAlert.this);
    	if(mp!=null){
    		mp.stop();
    		vibrator.cancel();  
    	}else if(vibrator!=null){
    		vibrator.cancel(); 
    	}
    	super.onStop();
    }
    
}
