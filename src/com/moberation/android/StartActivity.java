package com.moberation.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class StartActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_game);

		Button buttonCreateGame = (Button) findViewById(R.id.buttonGoToNewGameActivity);
		final Activity thisActivity = this;
		buttonCreateGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				Intent switchActivityIntent = new Intent(thisActivity,
						StartNewGameActivity.class);
				StartActivity.this.startActivity(switchActivityIntent);
			}
		});

		Button buttonJoinGame = (Button) findViewById(R.id.buttonGoToJoinGame);
		buttonJoinGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				Intent switchActivityIntent = new Intent(thisActivity,
						JoinGameActivity.class);
				StartActivity.this.startActivity(switchActivityIntent);
			}
		});
	}

}
