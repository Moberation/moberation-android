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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.moberation.android.domain.AvailableGame;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class JoinGameActivity extends Activity {

	private static final String TAG = JoinGameActivity.class.getSimpleName();

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_join_game);

		final Spinner selectGame = (Spinner) findViewById(R.id.spinnerSelectGame);
		fetchAvailableGamesAndAddToSpinner(selectGame);

		final Button join = (Button) findViewById(R.id.buttonJoinGame);

		selectGame.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(final AdapterView<?> arg0,
					final View arg1, final int arg2, final long arg3) {

				join.setEnabled(true);

			}

			@Override
			public void onNothingSelected(final AdapterView<?> arg0) {

				join.setEnabled(false);
			}
		});
		final Activity thisActivity = this;

		join.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {

				v.setEnabled(false);

				HttpParams httpParameters = new BasicHttpParams();
				int timeoutConnection = 3000;
				HttpConnectionParams.setConnectionTimeout(httpParameters,
						timeoutConnection);
				int timeoutSocket = 5000;
				HttpConnectionParams
						.setSoTimeout(httpParameters, timeoutSocket);
				HttpClient client = new DefaultHttpClient(httpParameters);

				AvailableGame game = (AvailableGame) selectGame
						.getSelectedItem();

				try {
					String requestUrl = AppConfig.SERVER_BASE_URL
							+ "/JoinGame?id=" + game.getId() + "&role="
							+ game.getRole();

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

	}

	private void fetchAvailableGamesAndAddToSpinner(final Spinner selectGame) {

		AvailableGame[] games = new AvailableGame[] { new AvailableGame(
				"123456789abcdef", "SURGEON", "A Test Game") };
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, games);
		selectGame.setAdapter(adapter);
	}
}
