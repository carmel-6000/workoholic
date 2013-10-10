package com.example.workoholic;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Clock 
{
	public static final String GLOBAL_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";
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
	ScheduledFuture<?> secondsHandProcess;
	View act;
	String clockName;
	SessionHandler sessionHandler;
	SessionCanvas sessionCanvas;
	TextView clockHolder;
	Calendar cal1 = Calendar.getInstance();
	Date beginTime; 
	SimpleDateFormat format1;
	Activity context;
	ImageView secondsHandImg;
	
	public Clock(View act,TextView clockHolder,String clockName,SessionHandler sessionHandler,
			Activity context){
		this.clockName = clockName;
		this.act = act;
		this.context = context;
		this.sessionHandler = sessionHandler;
		this.sessionCanvas = new SessionCanvas(act,sessionHandler,context);
		this.clockHolder = clockHolder;
		this.rotateSecondsHand();
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
	public void incClockBySecond(){
		
		format1 = new SimpleDateFormat("HH:mm:ss",Locale.ENGLISH);
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
	
	public void rotateSecondsHand(){
		this.secondsHandImg = (ImageView) this.act.findViewById(R.id.imgsecond);
		secondsHandProcess = service.scheduleWithFixedDelay(new Runnable(){
			@Override
			public void run()
			{
				context.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						 int seconds = (Calendar.getInstance()).get(Calendar.SECOND);
						 RotateAnimation rotateAnimation = new RotateAnimation(
						   (seconds - 1) * 6, seconds * 6,
						   Animation.RELATIVE_TO_SELF, 0.5f,
						   Animation.RELATIVE_TO_SELF, 0.5f);
						 rotateAnimation.setInterpolator(new LinearInterpolator());
						 rotateAnimation.setDuration(1000);
						 rotateAnimation.setFillAfter(true);
						 secondsHandImg.startAnimation(rotateAnimation);
					}});		
			}}, 
				0, 1, TimeUnit.SECONDS
		);
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