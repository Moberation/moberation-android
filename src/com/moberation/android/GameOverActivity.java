package com.moberation.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);

		final Activity thisActivity = this;

		Button back = (Button) findViewById(R.id.buttonBackToStart);
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				Intent switchActivityIntent = new Intent(thisActivity,
						StartActivity.class);
				thisActivity.startActivity(switchActivityIntent);
				thisActivity.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game_over, menu);
		return true;
	}

}
