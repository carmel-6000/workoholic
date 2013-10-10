package com.example.workoholic;

import java.util.Date;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SessionHandler 
{
	Activity context;
	View sessAct;
	Clock workClock;
	Clock coffeeClock;
	SessionCanvas sessionCanvas;
	Long sessionID;
	WorkingSessionsIO wsIO;
	
	@SuppressLint("NewApi")
	public SessionHandler(View act,Activity context)
	{
		this.sessAct = act;
		this.context = context;
		this.wsIO = new WorkingSessionsIO(context);
		sessionCanvas = new SessionCanvas(sessAct,this,context);
	}
	public void drawCanvas()
	{
		initClocks();
		addListeners();
	}
	public void endSession()
	{
		Log.d("SessionHandler","End Session Launched");
		stopAllClocks();
		sessionCanvas.printSessionEndTime();
	    wsIO.open();
		wsIO.EndASession(this.sessionID);
		wsIO.close();
	}
	public void stopAllClocks()
	{
		workClock.stopCounter();
		coffeeClock.stopCounter();
	}
	public void beginSession(Date beginTime)
	{
		sessionCanvas.printSessionBeginTime(beginTime);
		saveBeginTime(beginTime);
	}
	public void saveBeginTime(Date beginTime)
	{
		//Calendar beginTime = workClock.getBeginTime();
	    wsIO.open();
		sessionID = wsIO.AppendANewSession(beginTime);
		wsIO.close();
	    Log.d("SessionHandler","SessionID --> "+sessionID);

	}
	public void initClocks(){
    	TextView workClockHolder = (TextView) sessAct.findViewById(R.id.workTimeText);
    	TextView coffeeClockHolder = (TextView) sessAct.findViewById(R.id.coffeeTimeText);
    	workClock = new Clock(sessAct,workClockHolder,"Working",this,context);
    	coffeeClock = new Clock(sessAct, coffeeClockHolder,"Coffee",this,context);
    }
	
	public void addListeners()
    {
    	Button workPlayBtn = (Button) sessAct.findViewById(R.id.workPlayBtn);
    	Button coffeePlayBtn = (Button) sessAct.findViewById(R.id.coffeePlayBtn);
    	coffeePlayBtn.setOnClickListener(new OnClickListener(){	
			@Override
			public void onClick(View arg0) {
				coffeeClock.toggleCounter();
			}
    	});
    	
    	workPlayBtn.setOnClickListener(new OnClickListener(){	
			@Override
			public void onClick(View arg0) {
				workClock.toggleCounter();
			}
    	});
    	
    }
}
