/***
  Copyright (c) 2012 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  From _The Busy Coder's Guide to Android Development_
    http://commonsware.com/Android
 */

package com.example.workoholic;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import com.actionbarsherlock.app.SherlockFragmentActivity;


public class ViewPagerIndicatorActivity extends SherlockFragmentActivity 
{
  public static String PACKAGE_NAME;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    PACKAGE_NAME = getApplicationContext().getPackageName();
    setContentView(R.layout.main);
    ViewPager pager=(ViewPager)findViewById(R.id.pager);
    pager.setAdapter(buildAdapter());
  }

  private PagerAdapter buildAdapter() {
    return(new SampleAdapter(this, getSupportFragmentManager()));
  }
/*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
	  super.onCreateOptionsMenu(menu);
    //  MenuInflater inflater = getSupportMenuInflater();
//      inflater.inflate(R.menu.main_menu, menu);
      return true;
  }
  */

  
  
}