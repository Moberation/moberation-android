package com.moberation.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.moberation.android.views.AnaestheticGameView;

public class AnaestheticActivity extends Activity {

	private static final String TAG = AnaestheticActivity.class.getSimpleName();

	private AnaestheticGameView drawView;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		drawView = new AnaestheticGameView(this);
		setContentView(drawView);
		drawView.requestFocus();
	}

}
