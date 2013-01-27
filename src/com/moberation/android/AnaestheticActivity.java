package com.moberation.android;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AnaestheticActivity extends Activity {

	private int currentLevel = 50;

	private boolean patientDead = false;

	private LevelModThread levelThread;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_anestesi);

		Button up = (Button) findViewById(R.id.buttonIncrease);
		up.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {

				if (patientDead) {

					return;
				}
				updateLevel(5);

			}
		});

		Button down = (Button) findViewById(R.id.buttonReduce);
		down.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {

				if (patientDead) {

					return;
				}

				updateLevel(-5);
			}
		});

		levelThread = new LevelModThread(this);
		new Thread(levelThread).start();
	}

	public void updateLevel(final int amount) {

		currentLevel += amount;

		final TextView level = (TextView) findViewById(R.id.textLevel);
		level.post(new Runnable() {
			@Override
			public void run() {
				level.setText(currentLevel + "%");
			}
		});

		checkDead();

	}

	public void checkDead() {

		if (patientDead) {

			return;
		}

		if (currentLevel < 25 || currentLevel > 75) {

			patientDead = true;

			final View view = findViewById(R.id.anaesthetic_ptslayouty);
			view.post(new Runnable() {

				@Override
				public void run() {
					view.setBackgroundResource(R.drawable.face2);
					view.invalidate();

				}

			});

			levelThread.interrupt();
		}
	}

	class LevelModThread implements Runnable {

		public boolean interrupted = false;

		private AnaestheticActivity activity;

		public LevelModThread(final AnaestheticActivity activity) {

			this.activity = activity;
		}

		@Override
		public void run() {

			while (!interrupted) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}

				Random r = new Random(System.currentTimeMillis());
				int updateLevelWith = r.nextInt(5);
				int direction = r.nextInt(100) < 50 ? -1 : 1;
				updateLevel(updateLevelWith * direction);
			}

		}

		public void interrupt() {

			interrupted = true;
			MediaPlayer mediaPlayer = MediaPlayer
					.create(activity, R.raw.scream);
			mediaPlayer.start();

			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
			}

			Intent switchActivityIntent = new Intent(activity,
					GameOverActivity.class);
			startActivity(switchActivityIntent);
			finish();
		}
	}

}
