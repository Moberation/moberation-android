/**
 * 
 */
package com.moberation.android.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
import com.moberation.android.utils.Point;

/**
 * @author jaran
 * 
 */
public class SurgeonGameView extends View implements OnTouchListener {

	private static final String TAG = SurgeonGameView.class.getSimpleName();

	private List<Point> cutPathPoints = new ArrayList<Point>();
	private Paint paint = new Paint();

	private Drawable background;

	private Drawable scalpel;

	public SurgeonGameView(final Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);

		this.setOnTouchListener(this);

		background = this.getContext().getResources()
				.getDrawable(R.drawable.surgeonbg);
		scalpel = this.getContext().getResources()
				.getDrawable(R.drawable.scalpel);

		paint.setColor(Color.MAGENTA);
		paint.setAntiAlias(false);

		this.setBackgroundDrawable(background);
	}

	@Override
	public void onDraw(final Canvas canvas) {

		Point lastPoint = null;
		for (Point point : cutPathPoints) {
			canvas.drawCircle(point.getX(), point.getY(), 2, paint);
			lastPoint = point;
		}

		if (lastPoint != null) {
			Bitmap bitmap = ((BitmapDrawable) scalpel).getBitmap();
			canvas.drawBitmap(bitmap, lastPoint.getX(), lastPoint.getY(), paint);
		}
	}

	@Override
	public boolean onTouch(final View view, final MotionEvent event) {
		Point point = new Point(event.getX(), event.getY());
		cutPathPoints.add(point);
		invalidate();
		return true;
	}

}
