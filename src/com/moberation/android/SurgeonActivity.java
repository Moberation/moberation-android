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

public class SurgeonActivity extends Activity {

	private static final String TAG = SurgeonActivity.class.getSimpleName();

	private View drawView;

	private Canvas canvas;

	private Bitmap bitmap;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_surgeon);

		Log.d(TAG, "Starting surgeon role...");

		drawView = findViewById(R.id.viewDraw);

		// reading screen size (for device Independence)
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

		bitmap = Bitmap.createBitmap(displaymetrics.widthPixels,
				displaymetrics.heightPixels, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);

		// Paint paint = new Paint();
		// paint.setColor(Color.BLACK);
		// paint.setStrokeWidth(3.0f);
		// canvas.drawCircle(200f, 200f, 50f, paint);

		drawView.setBackgroundDrawable(new BitmapDrawable(bitmap));
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_surgeon, menu);

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
			drawView.setBackgroundResource(R.drawable.surgeonbg);
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
