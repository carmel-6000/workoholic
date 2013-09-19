package com.example.workoholic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
/*
 * DEPRECATED 
 * */
public class Clocker 
{
	Activity act;
	TextView clockHolder;
	Calendar cal1 = Calendar.getInstance();
	Calendar beginTime; 
	SimpleDateFormat format1;
	
	public Clocker(Activity act,TextView clockHolder){
		this.act = act;
		this.clockHolder = clockHolder;
		resetCal();
	}
	
	public void resetCal(){
		
		cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
	}
	public void setBeginTime(){
		this.beginTime = Calendar.getInstance();
		Log.d("Clocker","Setting begin time to :"+beginTime.getTime().toString());
	}
	public Calendar getBeginTime()
	{
		return this.beginTime;
	}
	@SuppressLint("SimpleDateFormat") public void incClockBySecond(){
		format1 = new SimpleDateFormat("HH:mm:ss");
		act.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				clockHolder.setTextSize(40);
				cal1.add(Calendar.SECOND,1);
				clockHolder.setText(format1.format(cal1.getTime()));
			}
		});
	}
	
}
