/*Travel Guide for India
Copyright (C) 2014  Siddharth Chikorde & Vikrant Dhimate
    This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
    This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
    You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.*/
package com.example.final2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

//import com.google.android.gms.maps.*;

public class States extends Activity {
	String[] codeLearnChapters =new String[0];
	String[] state;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity4);
		this.setTitle("States in India");
		ListView lv = (ListView)findViewById(R.id.listView1);
		//codeLearnChapters = new String[] { "shahid","gallardo","ic"};
		//ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, codeLearnChapters);
		
		String State = ReadFromfile("States.txt", getApplicationContext());
		StringTokenizer st = new StringTokenizer(State,"\n");
		int k = st.countTokens();
		int o;
		state = new String[k];
		for(o=0;o<k;o++)
			state[o]=st.nextToken();
		ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, state);
		lv.setAdapter(codeLearnArrayAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
			      //Intent appInfo = new Intent(MainActivity4.this, MainActivity3.class);
			      //appInfo.putExtra("message", codeLearnChapters[position]);
			      //startActivity(appInfo);
				 Intent appInfo = new Intent(States.this, Cities.class);
				 appInfo.putExtra("message", state[position]);
				 startActivity(appInfo);
			   } 
			});
		
	}
	public String ReadFromfile(String fileName, Context context) {
	    String returnString = new String();
	    InputStream fIn = null;
	    InputStreamReader isr = null;
	    BufferedReader input = null;
	    try {
	        fIn = context.getResources().getAssets().open(fileName, Context.MODE_WORLD_READABLE);
	        isr = new InputStreamReader(fIn);
	        input = new BufferedReader(isr);
	        String line = "";
	        while ((line = input.readLine()) != null) {
	            returnString=returnString+"\n"+line;
	        }
	    } catch (Exception e) {
	        e.getMessage();
	    } finally {
	        try {
	            if (isr != null)
	                isr.close();
	            if (fIn != null)
	                fIn.close();
	            if (input != null)
	                input.close();
	        } catch (Exception e2) {
	            e2.getMessage();
	        }
	    }
	    return returnString;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity1, menu);
		return true;
	}

}
