package com.example.workoholic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHandler extends SQLiteOpenHelper{
	  public static final String TABLE_NAME = "working_sessions";
	  public static final String COL_ID = "_id";
	  public static final String COL_BEGIN_TIME = "begin_time";
	  public static final String COL_END_TIME = "end_time";
	  public static final String COL_DATE = "sess_date";
	  private static final String DATABASE_NAME = "working_sessions.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_NAME + "(" 
		  + COL_ID + " integer primary key autoincrement, " 
	      + COL_BEGIN_TIME+ " datetime null, "
	      + COL_END_TIME+ " datetime null, "
	      + COL_DATE+ " datetime null"
	      +");";

	  public SQLiteHandler(Context context) 
	  {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
		  Log.d("SQLiteHandler","Creating database with query : "+DATABASE_CREATE);
		  database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(SQLiteHandler.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	    onCreate(db);
	  }
}
