/**
 * Author Kasper Wilkosz
 * Tisona
 */

package se.teamdestruction.tisona.boobybase;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowSearchResults extends ListActivity {

	String filmIds[] = {"0","1","2","3","4","5","6","7"};
	String filmGenres[] = {"Comedy","Comedy","Comedy","Family","Action","Action","Action","Thriller"};
	String filmRatings[] = {"3.8","3.2","2.9","4.2","4.1","4.5","2.2","3.7"};
	String filmNames[] = {"American Pie","American Pie 2","American Pie 3","Gone with the wind","Saving Private Ryan","The Godfather","The Godfather Part II","Titanic"};
	String filmYears[] = {"1999","2001","2003","1939","1998","1972","1974","1997"};
	String filmDescriptions[] = {"Jim, Oz, Finch and Kevin are four friends who make a pact that before they graduate they will all lose their virginity. The hard job now is how to reach that goal by prom night. ",
								"The whole gang are back and as close as ever. They decide to get even closer by spending the summer together at a beach house.",
								"Jim Levenstein has finally found the courage to ask his girlfriend, Michelle Flaherty to marry him. She agrees to get married, but the problems don't stop there for Jim.",
								"Scarlett is a woman who can deal with a nation at war, Atlanta burning, the Union Army carrying off everything from her beloved Tara, the carpetbaggers who arrive after the war.",
								"Opening with the Allied invasion of Normandy on 6 June 1944, members of the 2nd Ranger Battalion under Cpt. Miller fight ashore to secure a beachhead. ",
								"The story begins as \"Don\" Vito Corleone, the head of a New York Mafia \"family\", oversees his daughter's wedding with his wife Wendy. His beloved son Michael has just come home from the war, but does not intend to become part of his father's business.",
								"Continuing saga of the Corleone family as they move to Nevada and make the casino business their major income source under the leadership of the increasingly paranoid and malevolent Michael",
								"84 years later, a 101-year-old woman named Rose DeWitt Bukater tells the story to her granddaughter Lizzy Calvert, Brock Lovett, Lewis Bodine, Bobby Buell and Anatoly Mikailavich on the Keldysh about her life set in April 10th 1912, on a ship called Titanic when young Rose boards the departing ship with the upper-class passengers and her mother, Ruth DeWitt Bukater, and her fiancé, Caledon Hockley."};
	boolean filmNudities[] = {true,true,true,false,false,false,false,true};
	String movieMatches[];
	int movieMatchIds[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.show_search_results);
		String searchFilmName = "";
		boolean isNice = true;
		boolean isNaughty = true;
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			searchFilmName = extras.getString("search_film_name");
			isNice = extras.getBoolean("search_nice");
			isNaughty = extras.getBoolean("search_naughty");
		}
		
		String regex = "(?i).*" + searchFilmName + ".*(?i)";
		
		int size = 0;
		for(int i = 0; i < filmNames.length; i++) {
			if(filmNames[i].matches(regex)) {
				if((isNice == true && filmNudities[i] == false) || (isNaughty == true && filmNudities[i] == true)) {
					size ++;
				}
			}
		}
		
		if(size == 0) {
			movieMatches = new String[2];
			movieMatches[0] = "NO MATCHES";
			movieMatches[1] = "ADD FILM";
		} else {
			int count = 0;
			movieMatches = new String[size + 1];
			movieMatchIds = new int[size + 1];
			for(int i = 0; i < filmNames.length; i++) {
				if(filmNames[i].matches(regex)) {
					if((isNice == true && filmNudities[i] == false) || (isNaughty == true && filmNudities[i] == true)) {
						movieMatches[count] = filmNames[i] + " (" + filmYears[i] + ")";
						movieMatchIds[count] = i;
						count++;
					}
				}
			}
			movieMatches[count] = "ADD FILM";
		}
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieMatches));
	}

	protected void onListItemClick(ListView lv, View v, int position, long id) {
		super.onListItemClick(lv, v, position, id);
		
		if(position == (movieMatches.length - 1)) {
			startActivity(new Intent("android.intent.action.ADDFILM"));
		} else {
		
			Intent i = new Intent("android.intent.action.SHOWSINGLEMOVIE");
				i.putExtra("film_name", filmNames[movieMatchIds[position]]);
				i.putExtra("film_year", filmYears[movieMatchIds[position]]);
				i.putExtra("film_nudity", filmNudities[movieMatchIds[position]]);
				i.putExtra("film_description", filmDescriptions[movieMatchIds[position]]);
				i.putExtra("film_rating", filmRatings[movieMatchIds[position]]);
				i.putExtra("film_genre", filmGenres[movieMatchIds[position]]);
			startActivity(i);
		
		}
	}
	
}
