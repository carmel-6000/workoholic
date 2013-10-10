package com.example.workoholic;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class WorkingHoursHandler {
	View whView;
	Context context;
	WorkingSessionsIO wsIO;
	
	public WorkingHoursHandler(View whView,Context context){
		this.whView = whView;
		this.context = context;
		this.wsIO = new WorkingSessionsIO(context);
	}
	@SuppressLint("UseValueOf")
	public TableLayout createWHTable()
	{
		TableLayout sTable = (TableLayout) whView.findViewById(R.id.workingHoursTable);
	    TableRow rowChild;
	    TextView beginTime;
	    TextView endTime;
	    wsIO.open();
	    List<WorkingSession> wsList = wsIO.getAllWorkingSessions();
	    wsIO.close();
	    rowChild = new TableRow(context);
	    for (WorkingSession ws : wsList)
	    {
	    	beginTime = new TextView(context);
	    	beginTime.setText(ws.getBeginTime()+" : ");
	    	endTime = new TextView(context);
	    	Integer sInterval = new Integer(ws.getSessionInterval());
	    	endTime.setText(sInterval.toString());
	    	rowChild.addView(beginTime);
	    	rowChild.addView(endTime);
	    	sTable.addView(rowChild);	
	    	rowChild = new TableRow(context);
	    }
	    return sTable;
	}
}
