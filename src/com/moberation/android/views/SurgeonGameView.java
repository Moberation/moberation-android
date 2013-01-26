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
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
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
public class SurgeonGameView extends View implements OnTouchListener {

	private static final String TAG = SurgeonGameView.class.getSimpleName();

	private List<Point> cutPathPoints = new ArrayList<Point>();

	private Point previousTouchPoint = null;

	private Paint paint = new Paint();

	private Paint steeringWheelPaint = new Paint();

	private Drawable background;

	private Bitmap backgroundBitmap;

	private Drawable scalpel;

	private boolean scalpelPlaced = false;

	private Rect bounds = new Rect();

	private int pathColor = Color.rgb(0, 0, 0);

	public SurgeonGameView(final Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);

		this.setOnTouchListener(this);

		background = this.getContext().getResources()
				.getDrawable(R.drawable.surgeonbg);
		backgroundBitmap = ((BitmapDrawable) background).getBitmap();
		scalpel = this.getContext().getResources()
				.getDrawable(R.drawable.scalpel);

		paint.setColor(Color.rgb(178, 0, 0));
		paint.setAntiAlias(false);

		steeringWheelPaint.setColor(Color.BLUE);

		this.setAlpha(1);
		this.setBackgroundDrawable(background);
	}

	@Override
	public void onDraw(final Canvas canvas) {

		canvas.getClipBounds(bounds);
		canvas.drawCircle(150f, bounds.bottom - 150f, 100f, steeringWheelPaint);

		Point lastPoint = null;
		for (Point point : cutPathPoints) {
			canvas.drawCircle(point.getX(), point.getY(), 2, paint);
			lastPoint = point;
		}

		if (lastPoint != null && scalpelPlaced) {
			Bitmap bitmap = ((BitmapDrawable) scalpel).getBitmap();
			canvas.drawBitmap(bitmap, lastPoint.getX(), lastPoint.getY(), paint);
		}
	}

	@Override
	public boolean onTouch(final View view, final MotionEvent event) {

		if (!scalpelPlaced) {

			previousTouchPoint = new Point(event.getX(), event.getY());

			pathColor = backgroundBitmap.getPixel(
					(int) previousTouchPoint.getX(),
					(int) previousTouchPoint.getY());

			cutPathPoints.add(previousTouchPoint);
			scalpelPlaced = true;
			view.invalidate();

			return true;
		}

		Log.d(TAG, "x=" + event.getX() + " y=" + event.getY());
		float centerY = bounds.bottom - 150f;
		float centerX = 150f;
		float radius = 100f;

		if ((event.getX() - centerX) * (event.getX() - centerX)
				+ (event.getY() - centerY) * (event.getY() - centerY) > radius
				* radius) {
			Log.d(TAG, "Outside joystick");
			return false;

		}

		float addX = 0;
		float addY = 0;

		if (event.getX() > centerX) {

			addX += 1.0f * ((event.getX() - centerX) / radius);
		} else {

			addX -= 1.0f * ((centerX - event.getX()) / radius);
		}

		if (event.getY() < centerY) {

			addY -= 1.0f * ((centerY - event.getY()) / radius);
		} else {

			addY += 1.0f * ((event.getY() - centerY) / radius);
		}

		previousTouchPoint = new Point(previousTouchPoint.getX() + addX,
				previousTouchPoint.getY() + addY);

		int touchedColor = backgroundBitmap.getPixel(
				(int) previousTouchPoint.getX(),
				(int) previousTouchPoint.getY());

		// Ensure player follows the path around the heart. Should he fail, end
		// the game.

		Log.d(TAG, "touched color=" + touchedColor + ", path color="
				+ pathColor);

		if (touchedColor != pathColor) {

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

		Point point = new Point(previousTouchPoint.getX(),
				previousTouchPoint.getY());
		cutPathPoints.add(point);
		invalidate();

		return true;
	}
}
