/*Travel Guide for India
Copyright (C) 2014  Siddharth Chikorde & Vikrant Dhimate
    This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
    This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
    You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.*/
package com.example.final2;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TravelGuide extends Activity {
	// Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageButton ib = (ImageButton) findViewById(R.id.imageButton1);
		// b = (Button)findViewById(R.id.button1);
		ib.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(TravelGuide.this, Home.class);
				startActivity(i);
				finish();
				// Intent intent = new
				// Intent(android.content.Intent.ACTION_VIEW,Uri.parse(String.format("http://maps.google.com/maps?saddr=kedah&daddr=johor")));
				// startActivity(intent);

				// Intent intent = new
				// Intent(android.content.Intent.ACTION_VIEW,Uri.parse(String.format("http://maps.google.com/maps?saddr=kedah&daddr=johor")));
				// startActivity(intent);

				// Intent i = new Intent(android.content.Intent.ACTION_VIEW,
				// Uri.parse("google.navigation:q=" +destLat+ ","+destLon+""));
				// this.startActivity(i);

				/*
				 * Intent intent = new Intent(Intent.ACTION_VIEW,
				 * Uri.parse("http://maps.google.com/maps?" +
				 * "saddr=Pune&daddr=Mumbai"));
				 * 
				 * intent.setClassName("com.google.android.apps.maps",
				 * "com.google.android.maps.MapsActivity");
				 * startActivity(intent);
				 */
			}
		});
	}

	/*
	 * public void onClick(View v) { // TODO Auto-generated method stub
	 * if(v.getId()==b.getId()) { Intent i=new Intent(this,MainActivity1.class);
	 * startActivity(i); } }
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
