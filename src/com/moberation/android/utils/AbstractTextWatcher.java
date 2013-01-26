/**
 * 
 */
package com.moberation.android.utils;

import android.text.Editable;
import android.text.TextWatcher;


/**
 * Easily create anonymous instances of this method, override only the methods you need.
 * @author jaran
 *
 */
public abstract class AbstractTextWatcher implements TextWatcher {

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

}
