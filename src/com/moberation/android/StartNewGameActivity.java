package com.moberation.android;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.moberation.android.utils.AbstractTextWatcher;

public class StartNewGameActivity extends Activity {

	private static final String TAG = StartNewGameActivity.class
			.getSimpleName();

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_new_game);

		final Button startButton = (Button) findViewById(R.id.buttonStartNewGame);
		final EditText gameName = (EditText) findViewById(R.id.editNewGameName);
		final Spinner roleSpinner = (Spinner) findViewById(R.id.spinnerRole);
		final Activity thisActivity = this;
		startButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {

				// startGameWithServer(gameName, roleSpinner, thisActivity, v);
				startGameForTesting();
			}

			private void startGameForTesting() {

				Class<?> activity = roleSpinner.getSelectedItem().toString()
						.equalsIgnoreCase("surgeon") ? SurgeonActivity.class
						: AnaestheticActivity.class;
				Intent switchActivityIntent = new Intent(
						StartNewGameActivity.this, activity);
				StartNewGameActivity.this.startActivity(switchActivityIntent);
			}

			private void startGameWithServer(final EditText gameName,
					final Spinner roleSpinner, final Activity thisActivity,
					final View v) {
				v.setEnabled(false);

				HttpParams httpParameters = new BasicHttpParams();
				int timeoutConnection = 3000;
				HttpConnectionParams.setConnectionTimeout(httpParameters,
						timeoutConnection);
				int timeoutSocket = 5000;
				HttpConnectionParams
						.setSoTimeout(httpParameters, timeoutSocket);
				HttpClient client = new DefaultHttpClient(httpParameters);

				try {
					String requestUrl = AppConfig.SERVER_BASE_URL
							+ "/NewGame?name=" + gameName.getText() + "&role="
							+ roleSpinner.getSelectedItem();

					Log.d(TAG, "Requesting URL: " + requestUrl);

					HttpGet get = new HttpGet(requestUrl);
					HttpResponse response = client.execute(get);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

						// New game started!

					} else {

						throw new IllegalStateException(
								"Server returned status: "
										+ response.getStatusLine().toString());
					}

				} catch (Exception e) {

					Log.e(TAG, "An error occured while requesting new game:"
							+ e.getMessage());

					AlertDialog.Builder builder = new AlertDialog.Builder(
							thisActivity);
					builder.setMessage(R.string.newgame_servererror_text)
							.setTitle(R.string.newgame_servererror_title);
					builder.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										final DialogInterface dialog,
										final int which) {

									// OK was clicked

								}
							});

					AlertDialog dialog = builder.create();
					dialog.show();
				}
				v.setEnabled(true);
			}
		});

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
