package com.actionbarsherlock.app;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class WorkaholicService extends IntentService {
	public WorkaholicService() {
		super("WorkaholicService");
		Log.d(TAG,"WorkaholicService is running on background");
		// TODO Auto-generated constructor stub
	}
	public static final String TAG = "WorkaholicService";
	
	@Override
	protected void onHandleIntent(Intent intent) {
		//String dataString = intent.getDataString();
		Log.d(TAG,"WorkaholicService is running on background");
		Boolean isFalse = false;
		int x = 0;
		while (isFalse){
			try {
			    Thread.sleep(1000);
			    Log.d(TAG,Integer.toString(x)+",");
			    x++;
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
}
