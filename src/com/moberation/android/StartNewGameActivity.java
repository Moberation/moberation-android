package com.moberation.android;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.moberation.android.utils.AbstractTextWatcher;

public class StartNewGameActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_new_game);

		final Button startButton = (Button) findViewById(R.id.buttonStartNewGame);

		EditText gameName = (EditText) findViewById(R.id.editNewGameName);
		gameName.addTextChangedListener(new AbstractTextWatcher() {

			@Override
			public void afterTextChanged(final Editable s) {

				if (s.length() > 0) {

					startButton.setEnabled(true);
				} else {

					startButton.setEnabled(false);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_start_new_game, menu);
		return true;
	}

}
