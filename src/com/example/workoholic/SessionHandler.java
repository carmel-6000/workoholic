package com.example.workoholic;

import java.util.Date;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SessionHandler 
{
	Activity sessAct;
	Clock workClock;
	Clock coffeeClock;
	SessionCanvas sessionCanvas;
	Long sessionID;
	WorkingSessionsIO wsIO;
	
	public SessionHandler(Activity act)
	{
		this.sessAct = act;
		this.wsIO = new WorkingSessionsIO(sessAct);
		this.wsIO.open();
		sessionCanvas = new SessionCanvas(act,this);
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
		wsIO.EndASession(this.sessionID);
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
	    sessionID = wsIO.AppendANewSession(beginTime);
	    Log.d("SessionHandler","SessionID --> "+sessionID);

	}
	public void initClocks(){
    	TextView workClockHolder = (TextView) sessAct.findViewById(R.id.workTimeText);
    	TextView coffeeClockHolder = (TextView) sessAct.findViewById(R.id.coffeeTimeText);
    	workClock = new Clock(sessAct,workClockHolder,"Working",this);
    	coffeeClock = new Clock(sessAct, coffeeClockHolder,"Coffee",this);
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
