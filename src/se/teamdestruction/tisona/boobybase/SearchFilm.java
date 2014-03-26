/**
 * Author Kasper Wilkosz
 * Tisona
 */

package se.teamdestruction.tisona.boobybase;

import com.teamdestruction.chaos.boobbase.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SearchFilm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_film);
		
		// get button ids
		Button searchButton = (Button) findViewById(R.id.searchButton);	
		Button addButton = (Button) findViewById(R.id.addButton);	
		final EditText inputEdit = (EditText) findViewById(R.id.inputEditTextSearchFilm);
		final CheckBox inputNice = (CheckBox) findViewById(R.id.checkNice);
		final CheckBox inputNaughty = (CheckBox) findViewById(R.id.checkNaughty);
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent("android.intent.action.SHOWSEARCHRESULTS");
				i.putExtra("search_film_name",inputEdit.getText().toString());
				i.putExtra("search_nice",inputNice.isChecked());
				i.putExtra("search_naughty",inputNaughty.isChecked());
				
				// show next activity (screen)
				startActivity(i);
				
				
				//startActivity(new Intent("android.intent.action.SHOWSEARCHRESULTS"));
			}
		});
		
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// show next activity (screen)
				startActivity(new Intent("android.intent.action.ADDFILM"));
			}
		});
	}
}
