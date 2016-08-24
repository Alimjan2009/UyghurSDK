package com.almas.uyghursdk;

import com.almas.fragment.EditTextFragment;
import com.almas.fragment.EditTextFragmentController;
import com.almas.fragment.EditTextFragmentController.ConfirmClickedListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;

public class EditTextActivity extends FragmentActivity implements ConfirmClickedListener   {

	private EditTextFragmentController editTextFragmentController;
	private EditTextFragment editTextFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_edit_text);
		
		editTextFragmentController = new EditTextFragmentController(this);
		editTextFragment = new EditTextFragment();
		openEditTextFragment();
	}


	private void openEditTextFragment() {
		// TODO Auto-generated method stub
		if(isSettingMode==true){
			FragmentManager fm =  getSupportFragmentManager();  
	        FragmentTransaction transaction = fm.beginTransaction();  
	        transaction.replace(R.id.frameLayout, editTextFragment);  
	        transaction.commit();
	        isSettingMode = false;
		}
	}
	
	public void onSettingClicked(View v){
		if(isSettingMode)openEditTextFragment();
		else openSettingFragment();
	}
	private boolean isSettingMode = true;
	private void openSettingFragment() {
		// TODO Auto-generated method stub
		if(isSettingMode==false){
			FragmentManager fm =  getSupportFragmentManager();  
	        FragmentTransaction transaction = fm.beginTransaction();  
	        transaction.replace(R.id.frameLayout, editTextFragmentController);  
	        transaction.commit();
	        isSettingMode = true;
		}
	}
	
	public void onRetrunClicked(View v){
		if(isSettingMode==true){
			this.openEditTextFragment();
		}else{
			this.finish();
		}
		
	}


	@Override
	public void onConfirmClicked() {
		// TODO Auto-generated method stub
		openEditTextFragment();
	}
	
	
}
