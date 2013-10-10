package com.example.workoholic;
import java.util.HashMap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Cell 
{
	//private static final String TAG = "Cell";
	private static final String TOUCHED_BGCOLOR = "#FFFFD6";
	protected int workingHours; 
	protected int workingSessions;
	protected boolean isTouched = false;
	protected Rect mBound = null;
	protected int mDayOfMonth = 1;	// from 1 to 31
	protected Paint mPaint = new Paint(Paint.SUBPIXEL_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
	int dx, dy;
	HashMap<String,Integer> cellData;
	
	public Cell(int dayOfMon, Rect rect, float textSize, boolean bold) {
		mDayOfMonth = dayOfMon;
		mBound = rect;
		if (!isTouched) 
		{
			mPaint.setTextSize(textSize/*26f*/);
			mPaint.setColor(Color.BLACK);
			if(bold) mPaint.setFakeBoldText(true);
		}
		dx = (int) mPaint.measureText(String.valueOf(mDayOfMonth)) / 2;
		dy = (int) (-mPaint.ascent() + mPaint.descent()) / 2;
	}
	public Cell(int dayOfMon, Rect rect, float textSize) {
		this(dayOfMon, rect, textSize, false);
	}
	public Cell(int dayOfMon,Rect rect,float textSize,boolean bold,HashMap<String,Integer> cellData)
	{
		this(dayOfMon, rect, textSize, bold);
		this.workingHours = cellData == null ? 0 : cellData.get("workingHours"); 
		this.workingSessions = cellData == null ? 0 : cellData.get("sessions");
		this.cellData = cellData;
	}
	
	protected void draw(Canvas canvas) {
		if (this.isTouched)
		{
			Rect r = new Rect(mBound.left + 2, mBound.top + 3,mBound.right + 1 ,mBound.bottom + 2 );
			Paint p = new Paint();
			p.setStyle(Paint.Style.FILL);
			p.setColor(Color.parseColor(TOUCHED_BGCOLOR));
			canvas.drawRect(r,p);
		}
		canvas.drawText(String.valueOf(mDayOfMonth), mBound.centerX() - dx, mBound.centerY() + dy, mPaint);
	}
	public int getWorkingHours(){
		return this.workingHours;
	}
	public int getWorkingSessions(){
		return this.workingSessions;
	}
	
	public void redoTouched(){
		this.isTouched = false;
	}
	public void markAsTouched(){
		this.isTouched = true;
	}
	public String getCellData(){
		return this.cellData == null ? "" : this.cellData.toString();
	}
	public int getDayOfMonth() {
		return mDayOfMonth;
	}
	public boolean hitTest(int x, int y) {
		return mBound.contains(x, y); 
	}
	public Rect getBound() {
		return mBound;
	}
	public String toString() {
		return String.valueOf(mDayOfMonth)+"("+mBound.toString()+")";
	}
}