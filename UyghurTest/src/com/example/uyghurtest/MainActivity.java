package com.example.uyghurtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.almas.keyboard.KeyboardUtil;
import com.almas.keyboard.UyghurKeyboardView;
import com.almas.tools.OnKeyboardClickedListener;
import com.almas.view.UyEditText;

public class MainActivity extends Activity {

	private UyghurKeyboardView keyboardView;
	private UyEditText uyEditText;
	private KeyboardUtil keyboardUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		keyboardView = (UyghurKeyboardView) findViewById(R.id.uyghurKeyboardView1);
		uyEditText = (UyEditText)findViewById(R.id.uyEditText1);
		 keyboardUtil = new KeyboardUtil(this,uyEditText,keyboardView);
		 uyEditText.setKeyboardUtil(keyboardUtil);
		 uyEditText.setOnKeyboardClickedListener(new OnKeyboardClickedListener() {
			
			@Override
			public void onTextChanged() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onEnterClicked() {
				// TODO Auto-generated method stub
				keyboardUtil.hideKeyboard();
			}
		});
	}
}
