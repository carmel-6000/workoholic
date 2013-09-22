package com.example.workoholic;

public class WorkingSession {

  private long id;
  private String beginTime;
  private String endTime;
  private int sessionInterval;
  
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
  }
  public String getEndTime() {
	    return endTime;
  }
  public void setEndTime(String endTime) {
	    this.endTime = endTime;
	    this.calcSessionInterval();
  }
  
  public int getSessionInterval()
  {
	  return 8;
  }
  // Will be used by the ArrayAdapter in the ListView
  @Override
  public String toString() 
  {
    return "beginTime: "+getBeginTime()+" ,endTime: "+getEndTime()+", sessionInterval: "+getSessionInterval();
  }
  private void calcSessionInterval()
  {
	  //set objects with beginTime and endTime
	  //use method diff , format in hours
	  //set the result
  }
} 