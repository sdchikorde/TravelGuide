/*Travel Guide for India
Copyright (C) 2014  Siddharth Chikorde & Vikrant Dhimate
    This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
    This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
    You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.*/
package com.example.final2;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.final2.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Nearby extends Activity {

	private ListView maListViewPerso;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity11);
		this.setTitle("Nearby places");
		// R�cup�ration de la listview cr��e dans le fichier main.xml
		maListViewPerso = (ListView) findViewById(R.id.listviewperso);

		// Cr�ation de la ArrayList qui nous permettra de remplire la listView
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

		// On d�clare la HashMap qui contiendra les informations pour un item
		HashMap<String, String> map;

		// Cr�ation d'une HashMap pour ins�rer les informations du premier item
		// de notre listView
		map = new HashMap<String, String>();
		// on ins�re un �l�ment titre que l'on r�cup�rera dans le textView titre
		// cr�� dans le fichier affichageitem.xml
		map.put("titre", "ATM");
		// on ins�re un �l�ment description que l'on r�cup�rera dans le textView
		// description cr�� dans le fichier affichageitem.xml
		map.put("description", "for money");
		// on ins�re la r�f�rence � l'image (convertit en String car normalement
		// c'est un int) que l'on r�cup�rera dans l'imageView cr�� dans le
		// fichier affichageitem.xml
		map.put("img", String.valueOf(R.drawable.money));
		// enfin on ajoute cette hashMap dans la arrayList
		listItem.add(map);

		// On refait la manip plusieurs fois avec des donn�es diff�rentes pour
		// former les items de notre ListView

		map = new HashMap<String, String>();
		map.put("titre", "Hotel");
		map.put("description", "food & lodging");
		map.put("img", String.valueOf(R.drawable.hotel));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("titre", "Petrol");
		map.put("description", "gas station");
		map.put("img", String.valueOf(R.drawable.petrol));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("titre", "Bus");
		map.put("description", "bus station");
		map.put("img", String.valueOf(R.drawable.bus));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("titre", "Railway");
		map.put("description", "railway station");
		map.put("img", String.valueOf(R.drawable.railway));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("titre", "Airport");
		map.put("description", "nearest airport");
		map.put("img", String.valueOf(R.drawable.airport));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("titre", "Police");
		map.put("description", "police station");
		map.put("img", String.valueOf(R.drawable.police));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("titre", "Hospital");
		map.put("description", "nearest health center");
		map.put("img", String.valueOf(R.drawable.hospital));
		listItem.add(map);

		// Cr�ation d'un SimpleAdapter qui se chargera de mettre les items
		// pr�sent dans notre list (listItem) dans la vue affichageitem
		SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(),
				listItem, R.layout.affichageitem, new String[] { "img",
						"titre", "description" }, new int[] { R.id.img,
						R.id.titre, R.id.description });

		// On attribut � notre listView l'adapter que l'on vient de cr�er
		maListViewPerso.setAdapter(mSchedule);

		// Enfin on met un �couteur d'�v�nement sur notre listView
		maListViewPerso.setOnItemClickListener(new OnItemClickListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				// on r�cup�re la HashMap contenant les infos de notre item
				// (titre, description, img)
				/*
				 * HashMap<String, String> map = (HashMap<String, String>)
				 * maListViewPerso.getItemAtPosition(position); //on cr�er une
				 * boite de dialogue AlertDialog.Builder adb = new
				 * AlertDialog.Builder(MainActivity6.this); //on attribut un
				 * titre � notre boite de dialogue
				 * adb.setTitle("S�lection Item"); //on ins�re un message �
				 * notre boite de dialogue, et ici on affiche le titre de l'item
				 * cliqu� adb.setMessage("Votre choix : "+map.get("titre"));
				 * //on indique que l'on veut le bouton ok � notre boite de
				 * dialogue adb.setPositiveButton("Ok", null); //on affiche la
				 * boite de dialogue adb.show();
				 */
				if (position == 0) {
					// startActivity(new
					// Intent(MainActivity11.this,MainActivity10.class));
					Intent appInfo = new Intent(Nearby.this, MapNearby.class);
					appInfo.putExtra("message", "ATM");
					startActivity(appInfo);
				} else if (position == 1) {
					Intent appInfo = new Intent(Nearby.this, MapNearby.class);
					appInfo.putExtra("message", "Hotel");
					startActivity(appInfo);
				} else if (position == 2) {
					Intent appInfo = new Intent(Nearby.this, MapNearby.class);
					appInfo.putExtra("message", "Petrol");
					startActivity(appInfo);
				} else if (position == 3) {
					// startActivity(new
					// Intent(MainActivity11.this,MainActivity10.class));
					Intent appInfo = new Intent(Nearby.this, MapNearby.class);
					appInfo.putExtra("message", "Bus");
					startActivity(appInfo);
				} else if (position == 4) {
					Intent appInfo = new Intent(Nearby.this, MapNearby.class);
					appInfo.putExtra("message", "Railway");
					startActivity(appInfo);
				} else if (position == 5) {
					Intent appInfo = new Intent(Nearby.this, MapNearby.class);
					appInfo.putExtra("message", "Airport");
					startActivity(appInfo);
				} else if (position == 6) {
					Intent appInfo = new Intent(Nearby.this, MapNearby.class);
					appInfo.putExtra("message", "Police");
					startActivity(appInfo);
				} else if (position == 7) {
					Intent appInfo = new Intent(Nearby.this, MapNearby.class);
					appInfo.putExtra("message", "Hospital");
					startActivity(appInfo);
				}

			}
		});

	}
}