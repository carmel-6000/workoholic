package com.example.workoholic;

import android.content.Intent;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.MenuItem;

public class MySherlockFragment extends SherlockFragment 
{
	public void runSettings()
	{
		   Intent intent = new Intent( this.getActivity(), PreferencesFragment.class);
	       startActivity(intent);
	}
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	      // Handle item selection
	      switch (item.getItemId()) {
	          case R.id.settings:
	              runSettings();
	        	  return true;
	          case R.id.help:
	              return true;
	          default:
	              return super.onOptionsItemSelected(item);
	      }
	  }
	
}
