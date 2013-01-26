/**
 * 
 */
package com.moberation.android.views;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.moberation.android.R;
import com.moberation.android.StartActivity;
import com.moberation.android.utils.Point;

/**
 * @author jaran
 * 
 */
public class AnaestheticGameView extends View implements OnTouchListener {

	private static final String TAG = AnaestheticGameView.class.getSimpleName();

	private List<Point> cutPathPoints = new ArrayList<Point>();
	private Paint paint = new Paint();

	private Drawable background;

	private Bitmap backgroundBitmap;

	public AnaestheticGameView(final Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);

		this.setOnTouchListener(this);

		background = this.getContext().getResources()
				.getDrawable(R.drawable.face);
		backgroundBitmap = ((BitmapDrawable) background).getBitmap();

		paint.setColor(Color.rgb(178, 0, 0));
		paint.setAntiAlias(false);

		this.setBackgroundDrawable(background);
	}

	@Override
	public void onDraw(final Canvas canvas) {

	}

	@Override
	public boolean onTouch(final View view, final MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			// TODO Increase levels
		} else if (event.getAction() == MotionEvent.ACTION_UP) {

			// TODO Reduce levels
		}

		boolean killed = false;
		if (killed) {

			AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
			builder.setMessage(R.string.gameover_message).setTitle(
					R.string.gameover);
			builder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(final DialogInterface dialog,
								final int which) {

							Intent switchActivityIntent = new Intent(
									getContext(), StartActivity.class);
							getContext().startActivity(switchActivityIntent);

						}
					});

			AlertDialog dialog = builder.create();
			dialog.show();

		}

		invalidate();

		return true;
	}
}
