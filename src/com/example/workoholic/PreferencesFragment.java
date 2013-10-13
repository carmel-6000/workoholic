
package com.example.workoholic;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import com.actionbarsherlock.app.SherlockPreferenceActivity;

public class PreferencesFragment extends SherlockPreferenceActivity  implements OnSharedPreferenceChangeListener{
    private static final String KEY_EDIT_WORKING_PLACE = "pref_working_place_name";
	@SuppressWarnings("deprecation")
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addPreferencesFromResource(R.xml.preferences);
	    }
	static PreferencesFragment newInstance(int position) {
	    PreferencesFragment frag=new PreferencesFragment();
	    return(frag);
	 }
	static String getTitle(Context ctxt, int position) {
		  return "Calendar View";
	}
	    @SuppressWarnings("deprecation")
		@Override
	    protected void onResume(){
	        super.onResume();
	        // Set up a listener whenever a key changes
	        getPreferenceScreen().getSharedPreferences()
	            .registerOnSharedPreferenceChangeListener(this);
	        updatePreference(KEY_EDIT_WORKING_PLACE);
	    }
	 
	    @Override
	    protected void onPause() {
	        super.onPause();
	        // Unregister the listener whenever a key changes
	        getPreferenceScreen().getSharedPreferences()
	            .unregisterOnSharedPreferenceChangeListener(this);
	    }
	 
	    @Override
	    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
	            String key) {
	        updatePreference(key);
	    }
	 
	    private void updatePreference(String key){
	        if (key.equals(KEY_EDIT_WORKING_PLACE)){
	            Preference preference = findPreference(key);
	            if (preference instanceof EditTextPreference){
	                EditTextPreference editTextPreference =  (EditTextPreference)preference;
	                if (editTextPreference.getText().trim().length() > 0){
	                    editTextPreference.setSummary(editTextPreference.getText());
	                }else{
	                    editTextPreference.setSummary("Your company's name");
	                }
	            }
	        }
	    }

	
	
}