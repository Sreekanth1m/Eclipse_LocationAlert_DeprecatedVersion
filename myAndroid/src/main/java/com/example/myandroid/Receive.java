package com.example.myandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Receive extends Activity implements View.OnClickListener{

	EditText etSendText;
	Button bSA, bSAFR;
	TextView tvGot;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receive);
		initialize();
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		etSendText = (EditText) findViewById(R.id.etSend);
		bSA=(Button) findViewById(R.id.bSA);
		bSAFR = (Button) findViewById(R.id.bSAFR);
		tvGot = (TextView) findViewById(R.id.tvGot);
		bSA.setOnClickListener(this);
		bSAFR.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.bSA:
			String question = etSendText.getText().toString();
			Bundle basket = new Bundle();
			basket.putString("key", question);
			Intent i = new Intent(this, Send.class);
			i.putExtras(basket);
			startActivity(i);
			break;
		case R.id.bSAFR:
			
			Intent a = new Intent(this,Send.class);
			startActivityForResult(a,0);
			
			break;
		}
		}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle passback = new Bundle();
			passback = data.getExtras();
			String pback = passback.getString("ans");
			tvGot.setText(pback);
			
		}
	}

	
}
