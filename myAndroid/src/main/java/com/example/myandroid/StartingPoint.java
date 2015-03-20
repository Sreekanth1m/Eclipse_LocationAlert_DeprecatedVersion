package com.example.myandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class StartingPoint extends Activity {

	int counter;
	Button add, sub;
	TextView display;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting_point);
		counter=0;
		add=(Button) findViewById(R.id.bAdd);
		sub=(Button) findViewById(R.id.bSub);
		display=(TextView) findViewById(R.id.tDisplay);
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			counter++;	
			display.setText("Yout Total is: "+ counter);
			}
		});
		sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter--;
				display.setText("Your Total is: "+ counter);
			}
		});
			}


	}


