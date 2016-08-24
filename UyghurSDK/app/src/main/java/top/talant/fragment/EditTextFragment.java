package com.almas.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.almas.keyboard.KeyboardUtil;
import com.almas.keyboard.UyghurKeyboardView;
import com.almas.tools.DensityUtil;
import com.almas.uyghursdk.AppConfig;
import com.almas.uyghursdk.R;
import com.almas.view.UyEditText;


public class EditTextFragment extends Fragment {

	private View viewMain;
	private UyEditText editText;
	private UyghurKeyboardView keyboardView;
	private KeyboardUtil keyboardUtil;
	private String text;
	public EditTextFragment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.viewMain = inflater.inflate(R.layout.fragment_edit_text, null);
		editText = (UyEditText) viewMain.findViewById(R.id.uyEditText);
		keyboardView = (UyghurKeyboardView)viewMain.findViewById(R.id.keyboard_view);
		keyboardUtil = new KeyboardUtil(getActivity(), editText,keyboardView);
		if(text!=null){
			editText.setText(text);
		}
		updateUI();
	
		return this.viewMain;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateUI();

	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
	}
	private void updateUI() {
		// TODO Auto-generated method stub
		editText.setTextSize(DensityUtil.dip2px(getActivity(), AppConfig.textSize));
		editText.setPaintFontName(AppConfig.fontName[AppConfig.fontIndex]);
		editText.setLineSpaceMutliUy(AppConfig.lineSpace);
		editText.setTextColor(AppConfig.textColor);
		editText.setTextHintColor(AppConfig.textHintColor);
		
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
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		if(editText!=null){
			this.text = editText.getText();
		}
	}
}
