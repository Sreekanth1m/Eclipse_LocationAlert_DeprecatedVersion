package com.example.myandroid;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener{

ImageView iv;
ImageButton ib;
Button b;
Intent i;
final static int cameradata = 0;
Bitmap bmp;

@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
	    initialize();
	    ib.setOnClickListener(this);
	    b.setOnClickListener(this);
	    InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
	    bmp = BitmapFactory.decodeStream(is);
	    
}

private void initialize() {
	// TODO Auto-generated method stub
	iv = (ImageView) findViewById(R.id.ivPicture);
	ib = (ImageButton) findViewById(R.id.ibCaptureImage);
	b = (Button) findViewById(R.id.bSetWall);
	
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.ibCaptureImage:
		i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(i,cameradata);
		break;
	case R.id.bSetWall:
		WallpaperManager wm = WallpaperManager.getInstance(this);  
		try {
			wm.setBitmap(bmp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	}
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if (resultCode == Activity.RESULT_OK && requestCode == cameradata){
		Bundle extras = data.getExtras();
		bmp = (Bitmap) extras.get("data");
		iv.setImageBitmap(bmp);
		
		
	}

}

}
