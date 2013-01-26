/**
 * 
 */
package com.moberation.android.utils;

/**
 * @author jaran
 * 
 */
public class Point {

	private float x;

	private float y;

	public Point() {

	}

	public Point(final float x, final float y) {

		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(final float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(final float y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
}
