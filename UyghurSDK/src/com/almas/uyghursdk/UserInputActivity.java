package com.almas.uyghursdk;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.almas.keyboard.KeyboardUtil;
import com.almas.keyboard.UyghurKeyboardView;
import com.almas.tools.DensityUtil;
import com.almas.tools.OnKeyboardClickedListener;
import com.almas.view.UyEditText;
import com.almas.view.UySyllabelTextView;


public class UserInputActivity extends Activity implements OnKeyboardClickedListener {

	private UyEditText editText;
	private UyghurKeyboardView keyboardView;
	private KeyboardUtil keyboardUtil;
	private boolean isForSpace;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_input);
		 editText = (UyEditText) findViewById(R.id.uyEditText1);
		 keyboardView = (UyghurKeyboardView) findViewById(R.id.uyghurKeyboardView1);
		 keyboardUtil = new KeyboardUtil(this, editText, keyboardView);
		 editText.setKeyboardUtil(keyboardUtil);
		 String action = getIntent().getStringExtra("for");
		 if(action!=null)
			 isForSpace = action.equals("space");
		 
		 if(isForSpace){
			 if(TextUtils.isEmpty(AppConfig.stringSpaceText)==false)
				 editText.setText(AppConfig.stringSpaceText);
		 }else{
			 if(TextUtils.isEmpty(AppConfig.stringEnterText)==false)
				 editText.setText(AppConfig.stringEnterText);
		 }
		 editText.setOnKeyboardClickedListener(this);
		 if(AppConfig.stringEnterText!=null&&AppConfig.stringEnterText.length()>0){
				keyboardUtil.setEnterText(AppConfig.stringEnterText);
		}else{
			keyboardUtil.setEnterText(null);
		}
		if(AppConfig.stringSpaceText!=null&&AppConfig.stringSpaceText.length()>0){
			keyboardUtil.setSpaceIconText(AppConfig.stringSpaceText);
		}else{
			keyboardUtil.setSpaceIconText(null);
		}
	}
	public void onRetrunClicked(View v){
		this.finish();
	}
	@Override
	public void onTextChanged() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onEnterClicked() {
		// TODO Auto-generated method stub

		this.keyboardUtil.hideKeyboard();
		if(isForSpace){
			AppConfig.stringSpaceText = editText.getText();
		}else{
			AppConfig.stringEnterText = editText.getText();
		}
		showToast();
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				UserInputActivity.this.finish();
			}
		};
		handler.sendEmptyMessageDelayed(1, 1000);
	}
	
	private void showToast() {
		// TODO Auto-generated method stub
        Toast toast = new Toast(this);  
        toast.setGravity(Gravity.BOTTOM, 0, DensityUtil.dip2px(this, 20));  
        toast.setDuration(Toast.LENGTH_LONG);
        UySyllabelTextView textView = new UySyllabelTextView(this);
        
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.BLACK);
        textView.setTextSize(DensityUtil.dip2px(this, 16));
        
        textView.setText("ئۇتۇقلۇق بولدى");
        toast.setView(textView);  
        toast.show();
	}
}
