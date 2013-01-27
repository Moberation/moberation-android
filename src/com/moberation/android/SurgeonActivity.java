package com.moberation.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.moberation.android.views.SurgeonGameView;

public class SurgeonActivity extends Activity {

	private static final String TAG = SurgeonActivity.class.getSimpleName();

	private SurgeonGameView drawView;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		drawView = new SurgeonGameView(this);
		setContentView(drawView);
		drawView.requestFocus();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle("Info");
		builder.setMessage("Click anywhere on the black border to place the scalpel. Use the blue joystick to control it.");
		builder.setInverseBackgroundForced(true);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				dialog.dismiss();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_surgeon, menu);

		return true;

	}

}
