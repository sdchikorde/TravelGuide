/*Travel Guide for India
Copyright (C) 2014  Siddharth Chikorde & Vikrant Dhimate
    This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
    This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
    You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.*/
package com.example.final2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class Search extends Activity {

	// List view
	private ListView lv;

	// Listview Adapter
	ArrayAdapter<String> adapter;

	// Search EditText
	EditText inputSearch;

	// ArrayList for Listview
	ArrayList<HashMap<String, String>> productList;
	String[] state;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle("Search for the city");
		setContentView(R.layout.activity_main_activity10);
		String text = ReadFromfile("Places.txt", getApplicationContext());
		StringTokenizer st = new StringTokenizer(text, "\n");
		int k = st.countTokens();
		int o;
		state = new String[k];
		StringTokenizer st1;
		for (o = 0; o < k; o++) {
			st1 = new StringTokenizer(st.nextToken(), "_");
			state[o] = st1.nextToken();
		}
		// ArrayAdapter<String> codeLearnArrayAdapter = new
		// ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
		// state);

		// Listview Data
		/*
		 * String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S",
		 * "HTC Sense", "HTC Sensation XE", "iPhone 4S",
		 * "Samsung Galaxy Note 800", "Samsung Galaxy S3", "MacBook Air",
		 * "Mac Mini", "MacBook Pro"};
		 */

		lv = (ListView) findViewById(R.id.list_view);
		inputSearch = (EditText) findViewById(R.id.inputSearch);

		// Adding items to listview
		adapter = new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.product_name, state);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg) {
				Intent appInfo = new Intent(Search.this, City.class);
				String selected = ((TextView) findViewById(R.id.product_name)).getText().toString();
				//appInfo.putExtra("message", selected);
				int[] touch = new int[state.length]; 
				EditText et = (EditText)findViewById(R.id.inputSearch);
				int j = 0,k = 0;
				for(j=0;j<state.length;j++)
				{
					if(state[j].toLowerCase().startsWith(et.getText().toString().toLowerCase()))
					{
						touch[k]=j;
						k++;
					}
				}
				
				appInfo.putExtra("message", state[touch[position]]);
				// lv.getSelectedItem().toString()
				startActivity(appInfo);
			}
		});

		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				Search.this.adapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});

	}

	public String ReadFromfile(String fileName, Context context) {
		String returnString = new String();
		InputStream fIn = null;
		InputStreamReader isr = null;
		BufferedReader input = null;
		try {
			fIn = context.getResources().getAssets()
					.open(fileName, Context.MODE_WORLD_READABLE);
			isr = new InputStreamReader(fIn);
			input = new BufferedReader(isr);
			String line = null;
			while ((line = input.readLine()) != null) {
				returnString = returnString + "\n" + line;
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

}