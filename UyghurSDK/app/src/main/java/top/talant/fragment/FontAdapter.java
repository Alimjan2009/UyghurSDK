package com.almas.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;

import com.almas.tools.DensityUtil;
import com.almas.view.UySyllabelTextView;

public class FontAdapter extends BaseAdapter implements SpinnerAdapter {

	
	private String [] fontName;
	private Context context;
	public FontAdapter(Context activity,String[] font) {
		// TODO Auto-generated constructor stub
		this.context = activity;
		fontName = font;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fontName.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView = new UySyllabelTextView(context);
		}
		UySyllabelTextView text = (UySyllabelTextView) convertView;
		text.setBackgroundColor(Color.WHITE);
		text.setText(fontName[position]);
		text.setTextSize(DensityUtil.dip2px(context, 25));
		return convertView;
	}

}
