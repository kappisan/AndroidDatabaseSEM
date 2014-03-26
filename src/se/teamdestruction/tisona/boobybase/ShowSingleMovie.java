/**
 * Author Kasper Wilkosz
 * Tisona
 */

package se.teamdestruction.tisona.boobybase;

import com.teamdestruction.chaos.boobbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowSingleMovie extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_single_movie);
		
		String filmName = "";
		String filmYear = "";
		boolean filmNudity = false;
		String filmDescription = "";
		String filmRating = "";
		String filmGenre = "";
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			filmName = extras.getString("film_name");
			filmYear = extras.getString("film_year");
			filmNudity = extras.getBoolean("film_nudity");
			filmDescription = extras.getString("film_description");
			filmRating = extras.getString("film_rating");
			filmGenre = extras.getString("film_genre");
		}
		
		TextView titleTextView = (TextView) findViewById(R.id.showSingleMovieTitle);
		titleTextView.setText(filmName);
		
		TextView yearTextView = (TextView) findViewById(R.id.showSingleMovieYear);
		yearTextView.setText("YEAR: " + filmYear);

		TextView descriptionTextView = (TextView) findViewById(R.id.showSingleMovieDescription);
		descriptionTextView.setText(filmDescription);

		TextView ratingTextView = (TextView) findViewById(R.id.showSingleMovieRating);
		ratingTextView.setText("Rating: " + filmRating);

		TextView genreTextView = (TextView) findViewById(R.id.showSingleMovieGenre);
		genreTextView.setText("Genre: " + filmGenre);		
		
		TextView nudityTextView = (TextView) findViewById(R.id.showSingleMovieNudity);
		if(filmNudity) {
			nudityTextView.setText("NUDITY: YES");
		} else {
			nudityTextView.setText("NUDITY: NO");			
		}
	}
}
