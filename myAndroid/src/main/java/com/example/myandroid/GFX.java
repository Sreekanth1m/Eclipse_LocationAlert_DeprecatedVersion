package com.example.myandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class GFX extends Activity {
	
	MyBringBack overView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		overView = new MyBringBack(this);
		setContentView(overView);
		
	}

}
