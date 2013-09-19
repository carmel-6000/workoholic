package com.example.workoholic;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class WorkingSessionsIO 
{
  // Database fields
  private SQLiteDatabase database;
  private SQLiteHandler dbHelper;
  @SuppressLint("SimpleDateFormat") SimpleDateFormat SDFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
  //private String[] allColumns = { SQLiteHandler.COL_ID,SQLiteHandler.COL_BEGIN_TIME,SQLiteHandler.COL_END_TIME,
  //SQLiteHandler.COL_DATE };
  
  public WorkingSessionsIO(Context context) {
    dbHelper = new SQLiteHandler(context);
  }
  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }
  public void close() {
    dbHelper.close();
  }
  public Long AppendANewSession(Date beginTime)
  {
	  ContentValues values = new ContentValues();
	  values.put(SQLiteHandler.COL_BEGIN_TIME,SDFormat.format(beginTime));
	  Long insertId = database.insert(SQLiteHandler.TABLE_NAME, null, values);
	  return insertId;
  }
  public Boolean EndASession(Long sessionId)
  {
	  ContentValues values = new ContentValues();
	  
	  values.put(
			    SQLiteHandler.COL_END_TIME,
			  	SDFormat.format(Calendar.getInstance().getTime())
	  );
	  //database.update(table, values, whereClause, whereArgs)
	  database.update(SQLiteHandler.TABLE_NAME,values,SQLiteHandler.COL_ID +"= "+sessionId , null);
	  return true;
	  //Long insertId = database.insert(SQLiteHandler.TABLE_NAME, null, values);
	  //return insertId;
  }
  public void updateSession(String comment) {
	  //database.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
	 
    ContentValues values = new ContentValues();
    values.put(SQLiteHandler.COL_BEGIN_TIME,"23-07-2013 24:00:00");
    values.put(SQLiteHandler.COL_END_TIME,"23-07-2013 24:20:00");
    values.put(SQLiteHandler.COL_DATE,"23-07-2013");
    database.insert(SQLiteHandler.TABLE_NAME, null, values);
    /*
    values.put(SQLiteHandler.COLUMN_COMMENT, comment);
    long insertId = database.insert(SQLiteHandler.TABLE_COMMENTS, null,values);
    Cursor cursor = database.query(
    	SQLiteHandler.TABLE_COMMENTS,
        allColumns, SQLiteHandler.COL_ID + " = " + insertId, null,
        null, null, null
    );
    cursor.moveToFirst();
    Comment newComment = cursorToComment(cursor);
    cursor.close();
    return newComment;
*/
  }
/*
  public List<Comment> getAllComments() 
  {
    List<Comment> comments = new ArrayList<Comment>();
    Cursor cursor = database.query(SQLiteHandler.TABLE_NAME,allColumns, null, null, null, null, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Comment comment = cursorToComment(cursor);
      comments.add(comment);
      cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();
    return comments;
  }
  
  private Comment cursorToComment(Cursor cursor) {
    Comment comment = new Comment();
    comment.setId(cursor.getLong(0));
    comment.setComment(cursor.getString(1));
    return comment;
  }
  */
}