package com.example.workoholic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.content.Context;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SessionCanvas {
	View sessAct;
	Activity context;
	SessionHandler sessionHandler;
	RelativeLayout thisView;
    public static final int BEGIN_TIME_TEXT=88;
	public SessionCanvas(View sessAct,SessionHandler sessionHandler,Activity context)
	{
		this.sessAct = sessAct;
		this.context = context;
		this.sessionHandler = sessionHandler;
	
	}
	public void setCurrentView(RelativeLayout thisView){
		this.thisView = thisView;
	}
	public void printSessionBeginTime(final Date date){
		context.runOnUiThread(new Runnable(){
			@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint({ "InlinedApi", "SimpleDateFormat" }) @Override
			public void run() 
			{
				 RelativeLayout thisView = (RelativeLayout) sessAct.findViewById(R.id.CanvasLayout);
				 setCurrentView(thisView);
				 TextView beginTime=new TextView(context);
				 beginTime.setTextSize(18);
				 SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
				 String curTime = format1.format(date);
				 beginTime.setText("Began at: "+curTime);
				 beginTime.setId(BEGIN_TIME_TEXT);
				 RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				 params.addRule(RelativeLayout.CENTER_HORIZONTAL);
				 params.addRule(RelativeLayout.BELOW,R.id.workoholic);
				 
				 //thisView.setId(88);
				 thisView.addView(beginTime,params);
				 
				 RelativeLayout.LayoutParams btnParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				 btnParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
				 btnParams.bottomMargin = sessAct.findViewById(R.id.clocksLayout).getHeight() + 10; 	 
				 btnParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
				 Button endBtn = new Button(context);
				 endBtn.setText("End Session");
				 thisView.addView(endBtn,btnParams);
				 endBtn.setOnClickListener(new OnClickListener(){	
						@Override
						public void onClick(View arg0) {
							Log.d("Counter","Ending Session");
							sessionHandler.endSession();
						}
			    	});
			}
		});
	}
	public void printSessionEndTime()
	{
		
		context.runOnUiThread(new Runnable(){
			@SuppressLint({ "SimpleDateFormat", "InlinedApi" }) public void run() 
			{
				String endTime = (new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime());
				//RelativeLayout tView = (RelativeLayout) sessAct.findViewById(R.id.CanvasLayout);
				TextView endTimeText=new TextView(context);
				endTimeText.setTextSize(18);
				endTimeText.setText("Ends at: "+endTime);
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);
				params.addRule(RelativeLayout.CENTER_HORIZONTAL);
				params.addRule(RelativeLayout.BELOW,BEGIN_TIME_TEXT);
				thisView.addView(endTimeText,params);
			}
		});
	}
}
