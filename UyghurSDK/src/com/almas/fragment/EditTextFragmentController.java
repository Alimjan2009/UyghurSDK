package com.almas.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.almas.fragment.TextViewerControllerFragment.ConfirmClickedListener;
import com.almas.tools.ML;
import com.almas.uyghursdk.AppConfig;
import com.almas.uyghursdk.ColorPickerDialog;
import com.almas.uyghursdk.R;
import com.almas.uyghursdk.UserInputActivity;
import com.almas.view.UySyllabelTextView;



public class EditTextFragmentController extends Fragment{

	private ConfirmClickedListener confirmClickedListener;
	private SeekBar seekBarTextSize;
	private TextView textViewTextSize;
	private View viewMain;
	private Spinner spinnerFont;
	private SeekBar seekBarLineSpace;
	private TextView textViewLineSpace;
	protected ColorPickerDialog dialog;
	private ImageView imageViewEditTextColor;
	private ImageView imageViewEditTextHintColor;
	private UySyllabelTextView textViewEnterText;
	private UySyllabelTextView textViewSpaceText;

	public EditTextFragmentController(ConfirmClickedListener listener) {
		// TODO Auto-generated constructor stub
		this.confirmClickedListener = listener;
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		 viewMain = inflater.inflate(R.layout.fragment_edit_text_controller, null);
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
		Button btnColor = (Button) viewMain.findViewById(R.id.buttonTextColor);
		btnColor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    dialog = new ColorPickerDialog(getActivity(), R.style.ColorDialogTheme,AppConfig.textColor,
			            "okokko",
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
		
		btnColor = (Button) viewMain.findViewById(R.id.buttonTextHintColor);
		btnColor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
			    dialog = new ColorPickerDialog(getActivity(), R.style.ColorDialogTheme,AppConfig.textColor,
			            "okokko",
			            new ColorPickerDialog.OnColorChangedListener() {
			        @Override
			        public void colorChanged(int color) {
			        	AppConfig.textHintColor = color;
			        	updateUI();
			        }
			    });			   
			    dialog.show(); 
			}
		});
		
		imageViewEditTextColor = (ImageView)viewMain.findViewById(R.id.imageViewEditTextColor);
		imageViewEditTextHintColor = (ImageView)viewMain.findViewById(R.id.imageViewEditTextHintColor);
		
		btnColor = (Button)viewMain.findViewById(R.id.buttonEnterText);
		btnColor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), UserInputActivity.class);
				intent.putExtra("for", "enter");
				startActivity(intent);
			}
		});
		textViewEnterText = (UySyllabelTextView)viewMain.findViewById(R.id.textViewEnterText);
		btnColor = (Button)viewMain.findViewById(R.id.buttonSpaceText);
		btnColor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), UserInputActivity.class);
				intent.putExtra("for", "space");
				startActivity(intent);
			}
		});
		textViewSpaceText = (UySyllabelTextView)viewMain.findViewById(R.id.textViewSpaceText);
		
		
		
		
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
		
		imageViewEditTextColor.setBackgroundColor(AppConfig.textColor);
		imageViewEditTextHintColor.setBackgroundColor(AppConfig.textHintColor);
		
		if(AppConfig.stringEnterText!=null&&AppConfig.stringEnterText.length()>0){
			textViewEnterText.setText(AppConfig.stringEnterText);
		}else{
			textViewEnterText.setText("رەسىملىك ھالەت");
		}
		if(AppConfig.stringSpaceText!=null&&AppConfig.stringSpaceText.length()>0){
			textViewSpaceText.setText(AppConfig.stringSpaceText);
		}else{
			textViewSpaceText.setText("");
		}
		
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
