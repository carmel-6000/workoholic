package com.example.workoholic;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SampleAdapter extends FragmentPagerAdapter {
  Context ctxt=null;
  public SampleAdapter(Context ctxt, FragmentManager mgr) {
    super(mgr);
    this.ctxt=ctxt;
  }
  @Override
  public int getCount() {
    return(3);
  }
  @Override
  public Fragment getItem(int position) {
	  //Log.d("SampleAdapter","position -->"+position);
    switch (position)
    {
    case 0:
    	return(SessionFragment.newInstance(position));
    case 1:
    	return CalendarFragment.newInstance(position);
    case 2:
    	return GraphFragment.newInstance(position);
    }
    return(SessionFragment.newInstance(position)); 
  }
  
  @Override
  public String getPageTitle(int position) {
	    switch (position)
	    {
	    case 0:
	    	return(SessionFragment.getTitle(ctxt, position));
	    case 1:
	    	return(CalendarFragment.getTitle(ctxt, position));
	    case 2:
	    	return(GraphFragment.getTitle(ctxt, position));
	    }
	    return(SessionFragment.getTitle(ctxt, position)); 
  }	
}
