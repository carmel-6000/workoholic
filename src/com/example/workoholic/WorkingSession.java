package com.example.workoholic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.util.Log;

public class WorkingSession {
	private static final String TAG = "WorkingSession";
  private long id;
  private String beginTime;
  private String endTime;
  private int sessionInterval;
  private int day;
  SimpleDateFormat df = new SimpleDateFormat(Clock.GLOBAL_TIME_FORMAT,Locale.ENGLISH);
  
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getBeginTime() {
    return beginTime;
  }
  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
    this.setDay();
  }
  public String getEndTime() {
	    return endTime;
  }
  public void setEndTime(String endTime) {
	  	Log.d("WorkingSession","setEndTime");
	    this.endTime = endTime;
	    this.calcSessionInterval();
  }
private void setDay(){
	  try{
		  	//Log.d(TAG,"beginTime --->"+this.beginTime);
			Date thisDate = df.parse(this.beginTime);
			this.day = Integer.parseInt( (new SimpleDateFormat("dd",Locale.ENGLISH)).format(thisDate));
			Log.d(TAG,"this.day --->"+(Integer.toString(this.day)));
	  }
	  	catch(ParseException p)
	  {
	  	Log.e("WorkingSession","error parsing beginTime for setDay()"+" \nError message:"+p.getMessage());
	  	this.sessionInterval = 0;
	  }
  }
  public int getDay(){
	  return this.day;
  }
  public int getSessionInterval()
  {
	  return this.sessionInterval;
  }
  // Will be used by the ArrayAdapter in the ListView
  @Override
  public String toString() 
  {
    return "beginTime: "+getBeginTime()+" ,endTime: "+getEndTime()+", sessionInterval: "+getSessionInterval();
  }
  @SuppressLint("SimpleDateFormat")
private void calcSessionInterval() 
  {
	  if (""==beginTime || beginTime == null || ""==endTime || endTime == null)
	  {
		  Log.d("WorkingSession","beginTime or endTime is empty (beginTime="+beginTime+")"
				+"endTime="+endTime+"\n , Returning a zero");
		  this.sessionInterval = 0;
		  return ;
	  }
	  
	  java.util.Date beginTimeObj;
	  java.util.Date endTimeObj;
	  try{
	   beginTimeObj = df.parse(beginTime);
	   endTimeObj =  df.parse(endTime);
  }catch(ParseException p){
  	Log.e("WorkingSession",
  		  "error parsing string beginTime ("+beginTime+") or endTime("+endTime+")"
  		 +" \nError message:"+p.getMessage());
  	this.sessionInterval = 0;
  	return;
  }
	  long diff = beginTimeObj.getTime() - endTimeObj.getTime(); //this is going to give you the difference in sec
	  Log.d("WorkingSession","interval between "+beginTime+" to "+endTime+" is : "+diff);
	  diff = diff / (60 * 60);
	  this.sessionInterval = (int)diff;
	  //Date result = new Date(diff);
	  //SimpleDateFormat frmt = new SimpleDateFormat("yy MM dd HH:mm:ss");
	  // frmt.format(result).toString();
	  //set objects with beginTime and endTime
	  //use method diff , format in hours
	  //set the result
  }
} 
