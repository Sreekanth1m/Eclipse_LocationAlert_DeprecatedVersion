package com.example.myandroid;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener{
	EditText eResults;
	Button bCommand;
	ToggleButton tbCommand;
	TextView tResults;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		initialize();
		tbCommand.setOnClickListener(this);
		bCommand.setOnClickListener(this);
		
/*		tbCommand.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (tbCommand.isChecked()) {
					eResults.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				} else {
					eResults.setInputType(InputType.TYPE_CLASS_TEXT);
				}
			}
		});
		bCommand.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String editContent = eResults.getText().toString();
				if (editContent.contentEquals("left")) {
					tResults.setGravity(Gravity.LEFT);
				} else if (editContent.contentEquals("right")) {
					tResults.setGravity(Gravity.RIGHT);
				} else if (editContent.contentEquals("center")) {
					tResults.setGravity(Gravity.CENTER);

				} else if (editContent.contentEquals("blue")) {
					tResults.setTextColor(Color.BLUE);
				} else if (editContent.contains("WTF")) {
					Random ranVar = new Random();
					tResults.setText("WTF!!!!");
					tResults.setTextSize(ranVar.nextInt(275));
					// tResults.setTextColor(ranVar.nextInt(255));
					tResults.setTextColor(Color.rgb(ranVar.nextInt(255),
							ranVar.nextInt(255), ranVar.nextInt(255)));
					switch (ranVar.nextInt(3)) {
					case 0:
						tResults.setGravity(Gravity.LEFT);
						break;
					case 1:
						tResults.setGravity(Gravity.CENTER);
						break;
					case 2:
						tResults.setGravity(Gravity.RIGHT);
						break;
					}
				} else {
					tResults.setText("invalid");
					tResults.setGravity(Gravity.CENTER);
					tResults.setTextColor(Color.BLACK);
				}
			}
		});*/
	}

	private void initialize() {
		// TODO Auto-generated method stub
		eResults = (EditText) findViewById(R.id.etResults);
		bCommand = (Button) findViewById(R.id.bResults);
		tbCommand = (ToggleButton) findViewById(R.id.tbPassword);
		tResults = (TextView) findViewById(R.id.tvResults);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tbPassword: 
			if (tbCommand.isChecked()) {
				eResults.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				eResults.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		case R.id.bResults:
			String editContent = eResults.getText().toString();
			if (editContent.contentEquals("left")) {
				tResults.setGravity(Gravity.LEFT);
			} else if (editContent.contentEquals("right")) {
				tResults.setGravity(Gravity.RIGHT);
			} else if (editContent.contentEquals("center")) {
				tResults.setGravity(Gravity.CENTER);

			} else if (editContent.contentEquals("blue")) {
				tResults.setTextColor(Color.BLUE);
			} else if (editContent.contains("WTF")) {
				Random ranVar = new Random();
				tResults.setText("WTF!!!!");
				tResults.setTextSize(ranVar.nextInt(275));
				// tResults.setTextColor(ranVar.nextInt(255));
				tResults.setTextColor(Color.rgb(ranVar.nextInt(255),
						ranVar.nextInt(255), ranVar.nextInt(255)));
				switch (ranVar.nextInt(3)) {
				case 0:
					tResults.setGravity(Gravity.LEFT);
					break;
				case 1:
					tResults.setGravity(Gravity.CENTER);
					break;
				case 2:
					tResults.setGravity(Gravity.RIGHT);
					break;
				}
			} else {
				tResults.setText("invalid");
				tResults.setGravity(Gravity.CENTER);
				tResults.setTextColor(Color.BLACK);
			}
			break;
		}
	}

}
