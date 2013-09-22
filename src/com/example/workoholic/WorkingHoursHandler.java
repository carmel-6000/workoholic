package com.example.workoholic;

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
		this.wsIO.open();
	}
	public TableLayout createWHTable()
	{
		TableLayout sTable = (TableLayout) whView.findViewById(R.id.workingHoursTable);
	    TableRow rowChild;
	    TextView beginTime;
	    TextView endTime;
	    Cursor sessCursor = wsIO.getAllSessions();
		rowChild = new TableRow(context);
	    while (!sessCursor.isAfterLast()) {
	    	Log.d("WorkingHoursHandler",
	    				sessCursor.getLong(0)+
	    				sessCursor.getString(1)+
	    				sessCursor.getString(2)
	    	);
	    
	    	beginTime = new TextView(context);
	    	beginTime.setText(sessCursor.getString(1));
	    	endTime = new TextView(context);
	    	endTime.setText(sessCursor.getString(2));
	    	rowChild.addView(beginTime);
	    	rowChild.addView(endTime);
	    	sTable.addView(rowChild);	
	    	rowChild = new TableRow(context);
	    	sessCursor.moveToNext();
	    }
	    return sTable;
	}
}
