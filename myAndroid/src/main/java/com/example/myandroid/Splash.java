package com.example.myandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class Splash extends Activity {
    
	TextView tActivityDisplay;
	MediaPlayer playSong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		tActivityDisplay = (TextView) findViewById(R.id.tView1);
		//playSong = MediaPlayer.create(Splash.this,R.raw.aakasam);
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	boolean musicPref = getPrefs.getBoolean("checkbox",true);

	if (musicPref == true)
	//playSong.start();
		;
	   Thread timer = new Thread (){
		   public void run(){
			   try {
				   sleep(5000);
			   } catch (InterruptedException e){
				   e.printStackTrace();
			   } finally {
				   Intent startAnotherActivity = new Intent("android.intent.action.MENU");
			       startActivity(startAnotherActivity);
			   }
		   }
		   
	   };
	   
	   timer.start();
		
	
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		tActivityDisplay.setText("On Destroy called");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		tActivityDisplay.setText("On Pause Called");
		finish();
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		tActivityDisplay.setText("On Restart Called");
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		tActivityDisplay.setText("on Start called");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		tActivityDisplay.setText("on Stop called");
	}
	



}
