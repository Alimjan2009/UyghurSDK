package com.almas.fragment;



import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.almas.tools.ML;
import com.almas.uyghursdk.AppConfig;
import com.almas.uyghursdk.ColorPickerDialog;
import com.almas.uyghursdk.R;

public class TextViewerControllerFragment extends Fragment {
	private ConfirmClickedListener confirmClickedListener;
	private SeekBar seekBarTextSize;
	private TextView textViewTextSize;
	private View viewMain;
	private Spinner spinnerFont;
	private SeekBar seekBarLineSpace;
	private TextView textViewLineSpace;
	private SeekBar seekBarFistLine;
	private TextView textViewFirstLine;
	private CheckBox checkBoxFirstLine;
	protected ColorPickerDialog dialog;
	private View imageViewTextViewerColor;
	public TextViewerControllerFragment(ConfirmClickedListener listener) {
		// TODO Auto-generated constructor stub
		this.confirmClickedListener = listener;
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		 viewMain = inflater.inflate(R.layout.fragment_text_viewer_controller, null);
		 viewMain.findViewById(R.id.buttonConfirm).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(confirmClickedListener!=null) confirmClickedListener.onConfirmClicked();
			}
		});
		 
		 //字体的大小
		seekBarTextSize = (SeekBar) viewMain.findViewById(R.id.seekBar1);
		seekBarTextSize.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				AppConfig.textSize = seekBar.getProgress();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewTextSize.setText(""+progress);
			}
		});
		textViewTextSize = (TextView)viewMain.findViewById(R.id.textView1);
		
		//字体的选择
		spinnerFont = (Spinner)viewMain.findViewById(R.id.spinner);
		spinnerFont.setAdapter(new FontAdapter((Context)getActivity(),AppConfig.fontName));
		spinnerFont.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				AppConfig.fontIndex = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		//行间距
		seekBarLineSpace = (SeekBar) viewMain.findViewById(R.id.seekBarLineSpace);
		seekBarLineSpace.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				AppConfig.lineSpace = (float)(seekBar.getProgress()+10)/10.0f;
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewLineSpace.setText(""+(float)(seekBar.getProgress()+10)/10.0f);
			}
		});
		textViewLineSpace = (TextView)viewMain.findViewById(R.id.textViewLineSpace);
		
		
		//首行缩进
		seekBarFistLine = (SeekBar) viewMain.findViewById(R.id.seekBarFistLine);
		seekBarFistLine.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				AppConfig.firstLineIndentWidth = seekBar.getProgress();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				textViewFirstLine.setText(""+seekBar.getProgress());
			}
		});
		textViewFirstLine = (TextView)viewMain.findViewById(R.id.textViewFistLine);
		checkBoxFirstLine = (CheckBox)viewMain.findViewById(R.id.checkBoxFistLine);
		checkBoxFirstLine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AppConfig.enableFistLine = checkBoxFirstLine.isChecked();
				updateUI();
			}
		});
		Button btnColor = (Button) viewMain.findViewById(R.id.buttonTextColor);
		btnColor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    dialog = new ColorPickerDialog(getActivity(), R.style.ColorDialogTheme,AppConfig.textColor,
			            "",
			            new ColorPickerDialog.OnColorChangedListener() {
			        @Override
			        public void colorChanged(int color) {
			        	AppConfig.textColor = color;
			        	updateUI();
			        }
			    });			   
			    dialog.show(); 
			}
		});
		imageViewTextViewerColor = viewMain.findViewById(R.id.imageViewTextViewerColor);
		
		updateUI();
		return viewMain;
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		updateUI();
	}
	private void updateUI() {
		// TODO Auto-generated method stub
		textViewTextSize.setText(""+AppConfig.textSize);
		seekBarTextSize.setProgress(AppConfig.textSize);
		spinnerFont.setSelection(AppConfig.fontIndex,true);
		
		textViewLineSpace.setText(""+AppConfig.lineSpace);
		seekBarLineSpace.setProgress((int) ((AppConfig.lineSpace*10)-10));
		
		textViewFirstLine.setText(""+AppConfig.firstLineIndentWidth);
		seekBarFistLine.setProgress(AppConfig.firstLineIndentWidth);
		
		checkBoxFirstLine.setChecked(AppConfig.enableFistLine);
		if(AppConfig.enableFistLine){
			seekBarFistLine.setEnabled(true);
		}else{
			seekBarFistLine.setEnabled(false);
		}
		imageViewTextViewerColor.setBackgroundColor(AppConfig.textColor);
	}
	public ConfirmClickedListener getConfirmClickedListener() {
		return confirmClickedListener;
	}
	public void setConfirmClickedListener(ConfirmClickedListener confirmClickedListener) {
		this.confirmClickedListener = confirmClickedListener;
	}
	public interface ConfirmClickedListener{
		public void onConfirmClicked();
	}
	
	


}
