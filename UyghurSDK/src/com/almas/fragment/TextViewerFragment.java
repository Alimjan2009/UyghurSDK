package com.almas.fragment;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.almas.tools.DensityUtil;
import com.almas.tools.NativeClass;
import com.almas.tools.SyllabelTextAlighnment;
import com.almas.tools.UyBaseAndEx;
import com.almas.uyghursdk.AppConfig;
import com.almas.uyghursdk.R;
import com.almas.view.UySyllabelTextView;

public class TextViewerFragment extends Fragment {

	private View mainView;
	private UySyllabelTextView textView;
	private Context context;
	private int stringId;
	public TextViewerFragment(Context context,int stringId,boolean isUyghur) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.stringId = stringId;
		this.isUyghur = isUyghur;
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mainView = inflater.inflate(R.layout.fragment_text_viewer, null);
		textView = (UySyllabelTextView)mainView.findViewById(R.id.syllabelText);
		updateUI();
		return mainView;
	}
	private boolean isUyghur = true;
	private void updateUI() {
		// TODO Auto-generated method stub
		if(isUyghur){
			textView.setText(context.getString(this.stringId));
			textView.SetSyllabelTextAlighnment(SyllabelTextAlighnment.Right);
		}else{
			textView.setText(NativeClass.toSelawenNative(context.getString(this.stringId)));
			textView.SetSyllabelTextAlighnment(SyllabelTextAlighnment.Left);
		}
		textView.setTextSize(DensityUtil.dip2px(context, AppConfig.textSize));
		textView.setPaintFontName(AppConfig.fontName[AppConfig.fontIndex]);
		textView.setLineSpaceMutliUy(AppConfig.lineSpace);
		textView.setEnableFirstLineIndent(AppConfig.enableFistLine);
		if(AppConfig.enableFistLine){
			textView.setFirstLineIndentWidth(DensityUtil.dip2px(getActivity(), AppConfig.firstLineIndentWidth));
		}
		textView.setTextColor(AppConfig.textColor);
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateUI();
	}
	
	public void switchLanguage() {
		// TODO Auto-generated method stub
		isUyghur = !isUyghur;
		updateUI();
	}
	
}
