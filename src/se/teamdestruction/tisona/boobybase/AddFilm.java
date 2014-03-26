/**
 * Author Kasper Wilkosz
 * Tisona
 */

package se.teamdestruction.tisona.boobybase;

// import r
import com.teamdestruction.chaos.boobbase.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFilm extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView;
	EditText sqlTitle, sqlYear;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_film);
		
		
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		//sqlView = (Button) findViewById(R.id.bSQLOpenView);
		
		sqlTitle = (EditText) findViewById(R.id.etSQLTitle);
		sqlYear = (EditText) findViewById(R.id.etYear);
		
	//	sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSQLUpdate:
			
			Toast.makeText(getApplicationContext(), "Film added",
					   Toast.LENGTH_LONG).show();
			
			startActivity(new Intent("android.intent.action.SHOWSEARCHRESULTS"));
			break;	
		}
		
	}

}
