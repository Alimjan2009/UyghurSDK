 ئۇيغۇرچە يۇمشاق دىتال ئىچىش قورالى Android
------------------------------------------


----------
## قىسقىچە چۈشەندۈرىلىشى:‫ ##
بۇ ‫‫SDK بولسا كەڭ ئۇيغۇرچە Android ئەپ ئاچقۇچىلار ئۈچۈن تەييارلانغان بولۇپ،ئىشلىتىش قولايلىق،كۆرۈنمە يۈزى كۆركەم,TextView,EditTextView ‫قۇرۇلمىلار ئىشقا ئاشۇرۇلغان .ئىچىدە سىزىش قاتارلىق فونكىسىيلەر تەمىنلەنگەن بولۇپ،ئەپ ئاچقۇچىلار خائىشى بويىچە باشقا قۇرۇلمىلارنىمۇ ئۆزگەرتىپ چىقسا بولىدۇ.

ئاساسلىق رولى تۆۋەندىكىچە:‫‫

‫1. ئۇيغۇرچىنى نورمال كۆرسىتىش
‫2. قۇر ئاخىرىدىكى سۆزلەرنى بوغۇمغا بۆلۈش
‫3. ھەر قايسى قۇرلارنى رەتلىك چىقىرىش
‫4. كونۇپكا تاختىسى ۋە كىرگۈزگۈچى بىلەن تەمىنلەش


----------
## كەسمە رەسىملىرى ##
![](http://dev.almas.biz/images/c1.png)![](http://dev.almas.biz/images/c2.png)
----------
## سىناش ئۇسۇلى:‫ ##
‫1.	Activity نىڭ xml ھۆججىتىگە

    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:background="#fff"
    xmlns:UyghurTextView="http://schemas.android.com/apk/res/com.example.uyghurtest" >

    <com.almas.keyboard.UyghurKeyboardView
        android:paddingTop="5dp"
        
        android:id="@+id/uyghurKeyboardView1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:keyBackground="@drawable/uy_keyboard_key" 
        android:background="@drawable/uy_keyboard_background" />
    <ScrollView 	
        android:background="@drawable/uy_bg_edittext"
		android:layout_alignParentTop="true"
        android:layout_margin="15dp"
        android:layout_above="@+id/uyghurKeyboardView1"
        android:layout_alignParentLeft="true"
        android:layout_alignParentRight="true"
        android:id="@+id/scrollView1"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical" >
                <com.almas.view.UyEditText
                    android:textColor="#000"
			        android:id="@+id/uyEditText1"
			        android:layout_width="match_parent"
			        android:layout_height="match_parent"
			        UyghurTextView:lineSpacingMultiplier="1.0"
			        android:padding="5dp"
			        android:text=""
			        android:textSize="20dp"
			        android:hint="مەزمۇننى كىرگۈزۈڭ..."/>
        </LinearLayout>
    </ScrollView>
</RelativeLayout>

‫2.	Activity نىڭ java ھۆججىتىگە

> private UyghurKeyboardView keyboardView;
	private UyEditText uyEditText;
	private KeyboardUtil keyboardUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		keyboardView = (UyghurKeyboardView) findViewById(R.id.uyghurKeyboardView1);
		uyEditText = (UyEditText)findViewById(R.id.uyEditText1);
		 keyboardUtil = new KeyboardUtil(this,uyEditText,keyboardView);
		 uyEditText.setKeyboardUtil(keyboardUtil);
		 uyEditText.setOnKeyboardClickedListener(new OnKeyboardClickedListener() {
			
			@Override
			public void onTextChanged() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onEnterClicked() {
				// TODO Auto-generated method stub
				keyboardUtil.hideKeyboard();
			}
		});
	}


----------
## توربىتىمىز:‫ ##
[توربىتىمىز](http://dev.almas.biz/index.html)
http://dev.almas.biz/
Email:822161777@qq.com
