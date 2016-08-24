package com.almas.uyghursdk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.almas.view.UySyllabelTextView;

public class HomeAdapter extends BaseAdapter implements ListAdapter {

	private String[] title={"ئۇيغۇرچە","Engilish","كىرگۈزگۈچ"};
	private LayoutInflater inflater;
	public HomeAdapter(Context context) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return title.length;
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
			convertView = inflater.inflate(R.layout.list_item_main, null);
		}
		UySyllabelTextView  textView = (UySyllabelTextView ) convertView.findViewById(R.id.text);
		textView.setText(title[position]);
		return convertView;
	}

}
