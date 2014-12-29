package com.almas.uyghursdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ListView listView  = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new HomeAdapter(this));
		listView.setOnItemClickListener(this);
	}
	
	public void onTestActivityClicked(View v){
		this.finish();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(this, TextViewerActivity.class);
			intent.putExtra("isUyghur", "YES");
			break;
		case 1:
			intent = new Intent(this, TextViewerActivity.class);
			intent.putExtra("isUyghur", "NO");
			break;
		case 2:
			intent = new Intent(this, EditTextActivity.class);
			
			break;

		default:
			break;
		}
		if(intent!=null)  startActivity(intent);
	}
	
}
