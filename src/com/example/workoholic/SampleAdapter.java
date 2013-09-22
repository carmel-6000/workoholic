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

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class SampleAdapter extends FragmentPagerAdapter {
  Context ctxt=null;

  public SampleAdapter(Context ctxt, FragmentManager mgr) {
    super(mgr);
    this.ctxt=ctxt;
  }

  @Override
  public int getCount() {
    return(2);
  }

  @Override
  public Fragment getItem(int position) {
	  //Log.d("SampleAdapter","position -->"+position);
    if (position ==  0)
    {
      return GraphFragment.newInstance(position);
    }else
      return(EditorFragment.newInstance(position));
       
  }

  @Override
  public String getPageTitle(int position) {
    if (position == 0){
    	return(GraphFragment.getTitle(ctxt, position));
    }
    else{
  	  return(EditorFragment.getTitle(ctxt, position));
    }
    	
    }
  }
