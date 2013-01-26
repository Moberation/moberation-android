package com.moberation.android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;


public class AnaestheticActivity extends Activity {
	
	private static final String TAG = AnaestheticActivity.class.getSimpleName();
	
	private View drawView;
	private Canvas canvas;
	private Bitmap bitmap;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anestesi);
		
		Log.d(TAG, "Starting Anaesthetic role... ");
		
		drawView = findViewById(R.id.viewDraw);
		
		// reading screen size (for device Independence)
				DisplayMetrics displaymetrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

				bitmap = Bitmap.createBitmap(displaymetrics.widthPixels,
						displaymetrics.heightPixels, Bitmap.Config.ARGB_8888);
				canvas = new Canvas(bitmap);
				
				drawView.setBackgroundDrawable(new BitmapDrawable(bitmap));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_anestesi, menu);
		return true;
	}
	@Override
	public boolean onTouchEvent(final MotionEvent event) {

		switch (event.getAction()) {

		case MotionEvent.ACTION_MOVE: {

			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setAlpha(255);
			paint.setStrokeWidth(3.0f);
			drawView.setBackgroundResource(R.drawable.anaestheticbg);
			canvas.drawPoint(event.getX(), event.getY(), paint);
			drawView.invalidate();

			Log.d(TAG, "plotted path");

			break;
		}
		default:
			break;
		}
		return true;
	}

}
