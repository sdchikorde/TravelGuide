/*Travel Guide for India
Copyright (C) 2014  Siddharth Chikorde & Vikrant Dhimate
    This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
    This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
    You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.*/
package com.example.final2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class Image extends Activity implements
		AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {
	public Integer[] mThumbIds;// =
								// getAllResourceIDs(R.drawable.class,"gallardo");//{
								// R.drawable.ic_launcher,R.drawable.shahid};

	public Integer[] mImageIds;// =
								// getAllResourceIDs(R.drawable.class,"gallardo");//{
								// R.drawable.ic_launcher,R.drawable.shahid};

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		String name = i.getExtras().getString("message");
		// if(!name.equals(null))
		// {
		mThumbIds = getAllResourceIDs(R.drawable.class, name);// {
																// R.drawable.ic_launcher,R.drawable.shahid};

		mImageIds = getAllResourceIDs(R.drawable.class, name);// {
																// R.drawable.ic_launcher,R.drawable.shahid};
		// }
		/*
		 * else{ mThumbIds = getAllResourceIDs(R.drawable.class,"gallardo");//{
		 * R.drawable.ic_launcher,R.drawable.shahid};
		 * 
		 * mImageIds = getAllResourceIDs(R.drawable.class,"gallardo");//{
		 * R.drawable.ic_launcher,R.drawable.shahid}; }
		 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main_activity3);

		mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
		mSwitcher.setFactory(this);
		mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));

		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this));
		g.setOnItemSelectedListener(this);
	}

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		mSwitcher.setImageResource(mImageIds[position]);
	}

	public void onNothingSelected(AdapterView<?> parent) {
	}

	public View makeView() {
		ImageView i = new ImageView(this);
		i.setBackgroundColor(0xFF000000);
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return i;
	}

	private ImageSwitcher mSwitcher;

	public class ImageAdapter extends BaseAdapter {
		/*
		 * public int[] addInt(int [] series, int newInt){ //create a new array
		 * with extra index int[] newSeries = new int[series.length + 1];
		 * 
		 * //copy the integers from series to newSeries for (int i = 0; i <
		 * series.length; i++){ newSeries[i] = series[i]; } //add the new
		 * integer to the last index newSeries[newSeries.length - 1] = newInt;
		 * 
		 * 
		 * 
		 * return newSeries;
		 * 
		 * }
		 */
		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		@SuppressWarnings("deprecation")
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView i = new ImageView(mContext);

			i.setImageResource(mThumbIds[position]);
			i.setAdjustViewBounds(true);
			i.setLayoutParams(new Gallery.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			i.setBackgroundResource(R.drawable.ic_launcher);
			return i;
		}

		private Context mContext;

	}

	public Integer[] getAllResourceIDs(Class<?> aClass, String name)
			throws IllegalArgumentException {
		// Get all Fields from the class passed.
		Field[] IDFields = aClass.getFields();
		List<Integer> myList = new ArrayList<Integer>();
		// int-Array capable of storing all ids.
		Integer[] IDs = new Integer[IDFields.length];

		try {
			// Loop through all Fields and store id to array.
			for (int i = 0; i < IDFields.length; i++) {
				// All fields within the subclasses of R
				// are Integers, so we need no type-check here.

				// pass 'null' because class is static
				if (IDFields[i].getName().startsWith(name.toLowerCase())) {
					IDs[i] = IDFields[i].getInt(null);
					myList.add(IDFields[i].getInt(null));
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		Integer[] wrapperArr = myList.toArray(new Integer[myList.size()]);
		return wrapperArr;
		// return IDs;
	}

	// gets the previously created intent
	// nameimage = myIntent.getExtras().getString("message");

}