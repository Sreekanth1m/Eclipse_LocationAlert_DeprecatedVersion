package com.example.myandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Send extends Activity implements View.OnClickListener, OnCheckedChangeListener{

	TextView etQuestion;
	RadioGroup rgAns;
	TextView tvDisplay;
	Button returnData;
	String returnt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		//Bundle receivedData = getIntent().getExtras();
		//String receivedString=receivedData.getString("key");
		//etQuestion.setText(receivedString);
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String et=getData.getString("name", "Preferences are");
		String list = getData.getString("list", "4");
		if(list.contentEquals("1"))
			etQuestion.setText(et);
	}

	private void initialize() {
		// TODO Auto-generated method stub
	etQuestion = (TextView) findViewById(R.id.etQuestion);
	rgAns = (RadioGroup) findViewById(R.id.rgAnswers);
	tvDisplay = (TextView) findViewById(R.id.textView1);
	rgAns.setOnCheckedChangeListener(this);
	returnData = (Button) findViewById(R.id.bReturnData);
	returnData.setOnClickListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch(arg1){
		case R.id.rUserFriendly:
			returnt="User Friendly";
			break;
		case R.id.rOperSource:
			returnt="Open Source";
			break;
		case R.id.rBoth:
			returnt = "Both User Friendly and Open Source";
			break;
		}
		tvDisplay.setText(returnt);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Bundle person = new Bundle();
		person.putString("ans", returnt);
		Intent aa = new Intent (this,Receive.class);
		aa.putExtras(person);
		setResult(RESULT_OK, aa);
		finish();
		
	}

}
