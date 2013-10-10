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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class SessionFragment extends SherlockFragment {
  private static final String KEY_POSITION="position";
  private int position=-1;

  static SessionFragment newInstance(int position) {
    SessionFragment frag=new SessionFragment();
    Bundle args=new Bundle();
    args.putInt(KEY_POSITION, position);
    frag.setArguments(args);
    return(frag);
  }

  static String getTitle(Context ctxt, int position) {
    return("Daily Session");
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.session_layout, container,false);
    SessionHandler sessionHandler = new SessionHandler(rl , this.getActivity());
    sessionHandler.drawCanvas();
    position=getArguments().getInt(KEY_POSITION, -1);
    if ((position % 2)==0) {
      setHasOptionsMenu(true);
    }
    return(rl);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.actions, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }
}