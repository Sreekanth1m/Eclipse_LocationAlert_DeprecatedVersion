package com.example.myandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

public class MyBringBack extends View {
	Bitmap gball;
	float yAxis;
	Typeface font;
	public MyBringBack(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		gball = BitmapFactory.decodeResource(getResources(), R.drawable.gball);
	yAxis=0;
	font = Typeface.createFromAsset(context.getAssets(),"GoodDog.otf");
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		Paint textPaint = new Paint();
		textPaint.setARGB(100,250,40,100);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(font);
		canvas.drawText("Custom Font",canvas.getWidth()/2,300,textPaint);
		canvas.drawBitmap(gball, (canvas.getWidth()/2),yAxis,null);
		if (yAxis<canvas.getHeight()){
			yAxis += 10;
		}
		else
			{yAxis=0;}
	Rect drawRectangle = new Rect();
	drawRectangle.set(0, 400, canvas.getWidth(), 550);
	Paint ourBlue = new Paint();
	ourBlue.setColor(Color.BLUE);
	canvas.drawRect(drawRectangle, ourBlue);
	invalidate();
	}
	
	

	
}
