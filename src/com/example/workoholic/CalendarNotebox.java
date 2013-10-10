package com.example.workoholic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarNotebox extends LinearLayout{
	private Context context;
	private Date thisDate;
	private int workingHours;
	private int workingSessions;
	String comment;
	public CalendarNotebox(Context context) {
		super(context);
		this.context = context;
	}
	public CalendarNotebox(Context context,AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
	}
	public CalendarNotebox setDate(java.util.Date thisDate){
		this.thisDate = thisDate;
		return this;
	}
	public CalendarNotebox setWorkingHours(int wh){
		this.workingHours = wh;
		return this;
	}
	public CalendarNotebox setWorkingSessions(int ws){
		this.workingSessions = ws;
		return this;
	}
	
	@SuppressLint("InlinedApi")
	private CalendarNotebox refreshTitleView(Date thisDate){
		if (this.findViewById(R.integer.calendar_notebox_date_title_id) == null)
		{
			String thisDateString = (new SimpleDateFormat("EEE, d MMM yyyy",Locale.ENGLISH)).format(thisDate);
			TextView dateStringTv=new TextView(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.CENTER;
			params.topMargin = 10;
			params.bottomMargin = 10;
			dateStringTv.setTextSize(20);
			dateStringTv.setId(R.integer.calendar_notebox_date_title_id);
			dateStringTv.setTextColor(Color.WHITE);
			dateStringTv.setText(thisDateString);
			this.addView(dateStringTv,params);	
		}
		return this;
	}
	private CalendarNotebox refreshView(String elementName,String noteboxLabel,String val){
		int resource_id = getResources().getIdentifier(elementName, "integer",ViewPagerIndicatorActivity.PACKAGE_NAME);
		if (this.findViewById(resource_id) == null){
			TextView tvNote=new TextView(context);
			tvNote.setTextSize(14);
			tvNote.setId(resource_id);
			tvNote.setTextColor(Color.WHITE);
			tvNote.setText(noteboxLabel+" : "+val);
			this.addView(tvNote);	
		}
		return this;
		//this.findViewById("R.id."+elementId);
	}
	public void refreshNotebox(){
		if(this.getChildCount() > 0) 
		    this.removeAllViews(); 
		this
			.refreshTitleView(this.thisDate)
			.refreshView("working_sessions","Working Sessions",Integer.toString(this.workingSessions))
			.refreshView("working_hours","Working Hours",Integer.toString(this.workingHours))
			.refreshView("comment","Comment",this.comment);
		
	}
	
	public CalendarNotebox setComment(String comment)
	{
		this.comment = comment;
		return this;
	}
/*<TextView
		android:id="@+id/working_hours_info"
        android:textSize="20sp"
        android:textColor="#FFF"
        android:fontFamily="sans-serif"
        android:gravity="center"
        android:typeface="monospace"
        android:layout_height="wrap_content"
        android:layout_width="fill_parent"
        android:text="@string/workoholic">
    </TextView>
 * */
}
