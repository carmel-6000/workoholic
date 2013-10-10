
package com.example.workoholic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
//import com.exina.android.calendar.Cell;

public class CalendarFragment extends SherlockFragment implements CalendarView.OnCellTouchListener{
  private static final String KEY_POSITION="position";
  protected static final String TAG = "CalendarFragment";
  public static final String MIME_TYPE = "vnd.android.cursor.dir/vnd.exina.android.calendar.date";
  java.util.Date currDateObj;
  CalendarNotebox calNotebox;
  CalendarView mView = null;
  Cell lastTouchedCell;
  TextView mHit;
  int thisMonth;
  Handler mHandler = new Handler();
  LinearLayout calView;
	
  static CalendarFragment newInstance(int position) {
    CalendarFragment frag=new CalendarFragment();
    Bundle args=new Bundle();
    args.putInt(KEY_POSITION, position);
    frag.setArguments(args);
    return(frag);
  }
  
  static String getTitle(Context ctxt, int position) {
    //return(String.format(ctxt.getString(R.string.hint), position + 1));
	  return "Calendar View";
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) 
  	{
	calView = (LinearLayout) inflater.inflate(R.layout.calender_view, container,false);
	mView = (CalendarView)calView.findViewById(R.id.calendar);
	calNotebox = (CalendarNotebox)calView.findViewById(R.id.working_hours_container);
	mView.setOnCellTouchListener(this);	
	thisMonth = mView.getMonth();
	this.setHasOptionsMenu(true);
	return(calView);
  }
  /*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      MenuInflater inflater = getSupportMenuInflater();
      inflater.inflate(R.menu.main_menu, menu);
      return true;
  }
  */
  
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.calendar_menu, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }
  

@Override
public void onTouch(Cell cell,MotionEvent event) 
{
	Log.d("CalendarFragment","touched!");
	final int day = cell.getDayOfMonth();
	switch (event.getAction()){
		case MotionEvent.ACTION_DOWN:Log.d(TAG,"ACTION_DOWN");break;case MotionEvent.ACTION_MOVE:Log.d(TAG,"ACTION_MOVE");break;case MotionEvent.ACTION_UP:Log.d(TAG,"ACTION_UP");break;
	}	
	if(mView.firstDay(day)) mView.previousMonth();
	else if(mView.lastDay(day)) 
		mView.nextMonth();
	
	String currDateSt = cell.getDayOfMonth() + "." + (mView.getMonth()+1)+"."+mView.getYear();
	  try{
		  currDateObj = new SimpleDateFormat("dd.MM.yyyy",Locale.ENGLISH).parse(currDateSt); 
	  }
	  		catch(ParseException p)
	  {
		  	Log.e(TAG,"error parsing string currDateSt ("+currDateSt+")"+" \nError message:"+p.getMessage());
	  }
	  
	
	if (lastTouchedCell instanceof Cell){	lastTouchedCell.redoTouched();}
	cell.markAsTouched();
	
	calNotebox
		.setDate(currDateObj)
		.setWorkingSessions(cell.getWorkingSessions())
		.setWorkingHours(cell.getWorkingHours())
		.setComment("additional comment")
		.refreshNotebox();
	
	lastTouchedCell = cell;
	mView.invalidate();
	/*mHandler.post(new Runnable() {@SuppressWarnings("deprecation")public void run() {Toast.makeText(CalendarFragment.this.getActivity(), day+" "+DateUtils.getMonthString(mView.getMonth(), DateUtils.LENGTH_LONG) + " "+mView.getYear(), Toast.LENGTH_SHORT).show();}});*/
}
}
