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

public class Home extends Activity {

	private ListView maListViewPerso;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity6);
		this.setTitle("Home");
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
		map.put("titre", "Search");
		// on ins�re un �l�ment description que l'on r�cup�rera dans le textView
		// description cr�� dans le fichier affichageitem.xml
		map.put("description", "for the city");
		// on ins�re la r�f�rence � l'image (convertit en String car normalement
		// c'est un int) que l'on r�cup�rera dans l'imageView cr�� dans le
		// fichier affichageitem.xml
		map.put("img", String.valueOf(R.drawable.search));
		// enfin on ajoute cette hashMap dans la arrayList
		listItem.add(map);

		// On refait la manip plusieurs fois avec des donn�es diff�rentes pour
		// former les items de notre ListView

		map = new HashMap<String, String>();
		map.put("titre", "Explore");
		map.put("description", "by states");
		map.put("img", String.valueOf(R.drawable.state));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("titre", "Whats around");
		map.put("description", "nearby important places");
		map.put("img", String.valueOf(R.drawable.nearby));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("titre", "About App");
		map.put("description", "all that can be done");
		map.put("img", String.valueOf(R.drawable.about));
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
					startActivity(new Intent(Home.this, Search.class));
				} else if (position == 1) {
					startActivity(new Intent(Home.this, States.class));
				} else if (position == 2) {
					startActivity(new Intent(Home.this, Nearby.class));
				} else if (position == 3) {
					startActivity(new Intent(Home.this, About.class));
				}
			}
		});

	}
}