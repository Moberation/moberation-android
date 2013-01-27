package com.moberation.android;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

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

		final Button buttonStartGame = (Button) findViewById(R.id.startNewGame);
		buttonStartGame.setEnabled(false);
		final Activity thisActivity = this;
		final Spinner gameType = (Spinner) findViewById(R.id.spinnerRole);

		buttonStartGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {

				if (gameType.getSelectedItem().equals("Surgeon")) {
					Intent switchActivityIntent = new Intent(thisActivity,
							SurgeonActivity.class);
					StartActivity.this.startActivity(switchActivityIntent);
				} else {

					Intent switchActivityIntent = new Intent(thisActivity,
							AnaestheticActivity.class);
					StartActivity.this.startActivity(switchActivityIntent);
				}
			}
		});

		Button buttonExit = (Button) findViewById(R.id.buttonExit);
		buttonExit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				thisActivity.finish();
			}
		});

		Button buttonAbout = (Button) findViewById(R.id.buttonAbout);
		buttonAbout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				Intent switchActivityIntent = new Intent(thisActivity,
						AboutActivity.class);
				StartActivity.this.startActivity(switchActivityIntent);
			}
		});

		gameType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(final AdapterView<?> arg0,
					final View arg1, final int arg2, final long arg3) {

				buttonStartGame.setEnabled(true);

			}

			@Override
			public void onNothingSelected(final AdapterView<?> arg0) {
				buttonStartGame.setEnabled(false);


				final Button buttonLeftSide = (Button) findViewById(R.id.button1);
				buttonLeftSide.setEnabled(true);
				
				
				
				
				
				final Button buttonRightSide = (Button) findViewById(R.id.button2);
				buttonRightSide.setEnabled(true);




			}
		});

	}
}
