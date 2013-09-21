package com.example.workoholic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Clock 
{
	@SuppressWarnings("serial")
	HashMap<String, Map<String,Boolean>> clocksConfig = new HashMap<String, Map<String,Boolean>>()
	{{
	     put("Working",new HashMap<String,Boolean>(){{  
		      put("in_loop",false);
		      put("began",false);
		 }});
		 put("Coffee",new HashMap<String,Boolean>(){{  
		      put("in_loop",false);
		      put("began",false);
		 }});
	}}; 
	
	final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	ScheduledFuture<?> process;
	View act;
	String clockName;
	SessionHandler sessionHandler;
	SessionCanvas sessionCanvas;
	TextView clockHolder;
	Calendar cal1 = Calendar.getInstance();
	Date beginTime; 
	SimpleDateFormat format1;
	Activity context;
	
	public Clock(View act,TextView clockHolder,String clockName,SessionHandler sessionHandler,
			Activity context){
		this.clockName = clockName;
		this.act = act;
		this.context = context;
		this.sessionHandler = sessionHandler;
		this.sessionCanvas = new SessionCanvas(act,sessionHandler,context);
		this.clockHolder = clockHolder;
		Log.d("Counter","Counter is Constructed");
		
	}
	public void toggleCounter()
	{
		Log.d ("Clock","toggleCounter, clock "+this.clockName+" in_loop ? "
				+clocksConfig.get(this.clockName).get("in_loop"));
		HashMap<String,Boolean> thisClock = (HashMap<String, Boolean>) clocksConfig.get(this.clockName);
		
		if (!thisClock.get("began")) this.resetCal();
		
		if ( this.clockName == "Working"){
			if (!thisClock.get("began"))
			{
				Log.d("Counter","first time");
				this.setBeginTime();
				sessionHandler.beginSession(getBeginTime());
			}	
				else
			{
				Log.d("Clock","not the first time");
			}
		}
		if (thisClock.get("in_loop"))
		{
			stopCounter();
			thisClock.put("in_loop",false);
		} 
		else {
			thisClock.put("in_loop",true);
			thisClock.put("began",true);
			beginCounter();
		}
		
	}
	public void beginCounter(){
		process = service.scheduleWithFixedDelay(new Runnable(){
			@Override
			public void run()
			{
		    	  incClockBySecond();
		    	  //Log.d("counter","repeating");
			}}, 
				0, 1, TimeUnit.SECONDS
		);
	}
	public void stopCounter(){
		if (process != null){
			process.cancel(true);
		}
	}
	@SuppressLint("SimpleDateFormat") public void incClockBySecond(){
		format1 = new SimpleDateFormat("HH:mm:ss");
		context.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				clockHolder.setTextSize(40);
				cal1.add(Calendar.SECOND,1);
				clockHolder.setText(format1.format(cal1.getTime()));
			}
		});
	}
	public void resetCal()
	{
		cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
	}
	public void setBeginTime(){
		this.beginTime = Calendar.getInstance().getTime();
		Log.d("Clock","Setting begin time to :"+beginTime.toString());
	}
	public Date getBeginTime()
	{
		return this.beginTime;
	}
}