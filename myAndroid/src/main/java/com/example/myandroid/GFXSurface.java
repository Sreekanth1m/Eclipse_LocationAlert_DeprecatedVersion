package com.example.myandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{
    MyBringBackSurface overViewSurface;
    float x, y;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		overViewSurface = new MyBringBackSurface(this);
		setContentView(overViewSurface);
		x=0;
		y=0;
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		overViewSurface.resume();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		overViewSurface.pause();
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		x=event.getX();
		y=event.getY();
		
		return true;
	}
	
	public class MyBringBackSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning;

		public MyBringBackSurface(Context context) {
			super(context);// TODO Auto-generated constructor stub
			ourHolder = getHolder();
			ourThread = new Thread(this);
			ourThread.start();
			isRunning = false;
		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ourThread = null;

			}

		}

		public void resume() {
			isRunning = true;
			Thread ourThread = new Thread(this);
			ourThread.start();

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunning) {
				if (!ourHolder.getSurface().isValid())
					continue;
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(20, 20, 100);
				if (x!=0 && y!=0){
					Bitmap test = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
					canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);
				}
				ourHolder.unlockCanvasAndPost(canvas);

			}
		}

	}


}
