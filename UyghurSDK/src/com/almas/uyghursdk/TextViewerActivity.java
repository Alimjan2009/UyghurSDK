package com.almas.uyghursdk;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.almas.fragment.TextViewerControllerFragment;
import com.almas.fragment.TextViewerControllerFragment.ConfirmClickedListener;
import com.almas.fragment.TextViewerFragment;
import com.almas.tools.DensityUtil;
import com.almas.view.UySyllabelTextView;

public class TextViewerActivity extends FragmentActivity implements ConfirmClickedListener{

	private TextViewerFragment textViewerFragment;
	private TextViewerControllerFragment controllerFragment;
	private boolean isUyghurLetters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_text_viewer);
		if(getIntent().getStringExtra("isUyghur")!=null){
			isUyghurLetters = getIntent().getStringExtra("isUyghur").equals("YES");
		}else{
			isUyghurLetters = true;
		}
		
		if(isUyghurLetters){
			textViewerFragment = new TextViewerFragment(this,R.string.test5,true);
			findViewById(R.id.selaven).setVisibility(View.VISIBLE);
		}else{
			textViewerFragment = new TextViewerFragment(this,R.string.test3,false);
			findViewById(R.id.selaven).setVisibility(View.GONE);
		}
		controllerFragment = new  TextViewerControllerFragment(this);
		openTextFragment();
	}

	private void openTextFragment() {
		// TODO Auto-generated method stub
		if(isSettingMode==true){
			FragmentManager fm =  getSupportFragmentManager();  
	        FragmentTransaction transaction = fm.beginTransaction();  
	        transaction.replace(R.id.frameLayout, textViewerFragment);  
	        transaction.commit();
	        isSettingMode = false;
		}
	}
	
	public void onSettingClicked(View v){
		if(isSettingMode)openTextFragment();
		else openSettingFragment();
	}
	private boolean isSettingMode = true;
	private ColorPickerDialog dialog;
	private void openSettingFragment() {
		// TODO Auto-generated method stub
		if(isSettingMode==false){
			FragmentManager fm =  getSupportFragmentManager();  
	        FragmentTransaction transaction = fm.beginTransaction();  
	        transaction.replace(R.id.frameLayout, controllerFragment);  
	        transaction.commit();
	        isSettingMode = true;
		}
	}
	@Override
	public void onConfirmClicked() {
		// TODO Auto-generated method stub
		openTextFragment();
	}
	public void onSelavenClicked(View v){
		if(textViewerFragment!=null&&isSettingMode==false){
			textViewerFragment.switchLanguage();
			showToast();
		}
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
	public void onRetrunClicked(View v){
		if(isSettingMode==true){
			this.openTextFragment();
		}else{
			this.finish();
		}
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		textViewerFragment = null;
		controllerFragment = null;
	}
}
