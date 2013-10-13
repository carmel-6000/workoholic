package com.example.workoholic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

public class GraphFragment extends MySherlockFragment {
  private static final String KEY_POSITION="position";
  
  static GraphFragment newInstance(int position) {
    GraphFragment frag=new GraphFragment();
    Bundle args=new Bundle();
    args.putInt(KEY_POSITION, position);
    frag.setArguments(args);
    return(frag);
  }
  
  static String getTitle(Context ctxt, int position) {
	  return "Graph View";
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) 
  	{
	LinearLayout wTable = (LinearLayout) inflater.inflate(R.layout.graph_view, container,false);
	WorkingHoursHandler whHandler = new WorkingHoursHandler(wTable,container.getContext());
	whHandler.createWHTable();
	return(wTable);
  }
  
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.graph_menu, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }
}