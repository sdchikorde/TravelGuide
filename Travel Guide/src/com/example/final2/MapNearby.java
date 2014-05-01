/*Travel Guide for India
Copyright (C) 2014  Siddharth Chikorde & Vikrant Dhimate
    This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
    This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
    You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.*/
package com.example.final2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost.Settings;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapNearby extends Activity implements LocationListener {
	String city = new String();
	LocationManager locationManager;
	LocationManager locationManager1;
	LocationManager locationManager2;
	static final Double distance = 6372.8;
	LocationListener locationListener;
	LocationListener locationListener1;
	private WebView webView;
	Double lattitude = 0.0, longitude = 0.0, lattitude1 = 0.0,
			longitude1 = 0.0, lattitude2 = 0.0, longitude2 = 0.0;
	String mode;

	public boolean isConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);

		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null && netinfo.isConnectedOrConnecting()) {
			return true;
		} else
			return false;
	}

	private Context _context;

	public void ConnectionDetector(Context context) {
		this._context = context;

	}

	public boolean isConnectingToInternet() {
		ConnectivityManager connectivity = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		city = i.getStringExtra("message");
		this.setTitle("Nearby " + city);
		setContentView(R.layout.activity_main_activity12);
		webView = (WebView) findViewById(R.id.webView1);
		webView.setWebViewClient(new MyBrowser());
		webView.getSettings().setJavaScriptEnabled(true);
		mode = "none";

		// webView.loadUrl("http://www.google.com");

		
		// locationManager = (LocationManager)
		// getSystemService(Context.LOCATION_SERVICE);
		// locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
		// 0, 0, this);

		PackageManager pm = this.getPackageManager();
		if (pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_NETWORK)) {
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, this);
		}
		if (pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)) {
			locationManager1 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			locationManager1.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, this);
			if (!locationManager1
					.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("GPS");
				builder.setMessage("Would you like to enable GPS?");
				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								Intent j = new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(j);
							}
						});
				builder.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub

							}
						});
				builder.create().show();
			}
		}
	}

	public static double haversine(double lat1, double lon1, double lat2,
			double lon2) {
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2)
				* Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return distance * c;
	}

	@Override
	public void onLocationChanged(Location location) {
		// txtLat = (TextView) findViewById(R.id.textview1);
		// txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:"
		// + location.getLongitude());
		int change = 0;

		if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
			Double dis = haversine(lattitude, longitude,
					location.getLatitude(), location.getLongitude());
			if (location.getAccuracy() < 500)
				mode = "net";
			if (dis > 0.5) {
				lattitude = location.getLatitude();
				longitude = location.getLongitude();
				change = 1;
			}

		} else if (location.getProvider().equals(
				LocationManager.PASSIVE_PROVIDER)) {
			Double dis = haversine(lattitude2, longitude2,
					location.getLatitude(), location.getLongitude());
			if (location.getAccuracy() < 500)
				mode = "passive";
			if (dis > 0.5) {
				lattitude2 = location.getLatitude();
				longitude2 = location.getLongitude();
				change = 1;
			}

		} else if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
			Double dis = haversine(lattitude1, longitude1,
					location.getLatitude(), location.getLongitude());
			if (location.getAccuracy() < 300)
				mode = "gps";
			if (dis > 0.2) {
				lattitude1 = location.getLatitude();
				longitude1 = location.getLongitude();
				change = 1;
			}

		}
		if (change == 1) {
			if (mode.equals("none")) {
				String customHtml = "<html><body><iframe height=\"100%\" width =\"100%\" src=\"https://maps.google.com/maps?t=m&z=14&ll="+ location.getLatitude()+ ","+ location.getLongitude()+ "&markers="+ location.getLatitude()+ ","+ location.getLongitude()+ "&q="+ city+ "&output=embed\"><iframe/></body></html>";
				webView.loadData(customHtml, "text/html", "UTF-8");
			} else if (mode.equals("gps")) {
				String customHtml = "<html><body><iframe height=\"100%\" width =\"100%\" src=\"https://maps.google.com/maps?t=m&z=14&ll="+lattitude1+ ","+ longitude1+"&q="+ city+ "&output=embed\"><iframe/></body></html>";
				webView.loadData(customHtml, "text/html", "UTF-8");
			} else if (mode.equals("net")) {
				String customHtml = "<html><body><iframe height=\"100%\" width =\"100%\" src=\"https://maps.google.com/maps?t=m&z=14&ll="+ lattitude+ ","+ longitude+"&q="+ city+ "&output=embed\"><iframe/></body></html>";
				webView.loadData(customHtml, "text/html", "UTF-8");
			} else if (mode.equals("passive")) {
				String customHtml = "<html><body><iframe height=\"100%\" width =\"100%\" src=\"https://maps.google.com/maps?t=m&z=14&ll="+ lattitude2+ ","+ longitude2+"&q="+ city+ "&output=embed\"><iframe/></body></html>";
				webView.loadData(customHtml, "text/html", "UTF-8");
			}

		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.d("Latitude", "disable");
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.d("Latitude", "enable");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d("Latitude", "status");
	}

	private class MyBrowser extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

}