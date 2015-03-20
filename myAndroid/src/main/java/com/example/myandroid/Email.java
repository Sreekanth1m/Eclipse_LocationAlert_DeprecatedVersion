package com.example.myandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends Activity implements View.OnClickListener {

	String emailAdd, perMsg, hateMsg, intro, outro, endMsg, stpMsg;
	EditText etEmailAdd, etPerMsg, etHateMsg, etIntro, etOutro, etEndMsg,
			etStpMsg;
	Button sendEmail;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initializeVar();
        sendEmail.setOnClickListener(this); 	
	}

	private void initializeVar() {
		// TODO Auto-generated method stub
		etEmailAdd = (EditText) findViewById(R.id.etEmailAdd);
		etPerMsg = (EditText) findViewById(R.id.etPerMsg);
		etHateMsg = (EditText) findViewById(R.id.etHateMsg);
		etIntro = (EditText) findViewById(R.id.etIntro);
		etOutro = (EditText) findViewById(R.id.etOutro);
		etEndMsg = (EditText) findViewById(R.id.etEndMsg);
		etStpMsg = (EditText) findViewById(R.id.etStpAction);

		sendEmail = (Button) findViewById(R.id.bSendEmail);

		emailAdd = etEmailAdd.getText().toString();
		perMsg = etPerMsg.getText().toString();
		hateMsg = etHateMsg.getText().toString();
		intro = etIntro.getText().toString();
		outro = etOutro.getText().toString();
		endMsg = etEndMsg.getText().toString();
		stpMsg = etStpMsg.getText().toString();

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String emailAddress[] = {emailAdd};
		String message = "Hello"
				        + perMsg
				        + intro
				        + hateMsg
				        + stpMsg
				        + outro
				        + endMsg
				        +'\n' + "end of email";
		Intent intSendEmail = new Intent (android.content.Intent.ACTION_SEND);
		intSendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddress);
		intSendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, "Test Mail");
		intSendEmail.setType("plain/text");
		intSendEmail.putExtra(android.content.Intent.EXTRA_TEXT,message);
		startActivity(intSendEmail);
	}

    protected void onPause(){
    	super.onPause();
    	finish();
    }
}