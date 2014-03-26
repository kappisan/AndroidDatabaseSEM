/**
 * Author Kasper Wilkosz
 * Tisona
 */

package se.teamdestruction.tisona.boobybase;

import java.util.List;
import java.util.Random;

import com.teamdestruction.chaos.boobbase.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class TestDatabaseActivity extends ListActivity {
  private FilmDataSource datasource;

  
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_database);
    

    datasource = new FilmDataSource(this);
    datasource.open();

    List<Film> values = datasource.getAllComments();

    // use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<Film> adapter = new ArrayAdapter<Film>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
  }

  // Will be called via the onClick attribute
  // of the buttons in main.xml
  public void onClick(View view) {
	  
	EditText writing = (EditText) findViewById(R.id.inputEditTextSearchFilm);
	  
    @SuppressWarnings("unchecked")
    ArrayAdapter<Film> adapter = (ArrayAdapter<Film>) getListAdapter();
    Film comment = null;
    switch (view.getId()) {
    case R.id.add:
      String[] comments = new String[] { "Titanic", "Hotel Rawanda", "Die Hard", "James Bond", "Friday" };
      int nextInt = new Random().nextInt(5);
      // save the new comment to the database
      comment = datasource.createComment(writing.getText().toString());
      adapter.add(comment);
      break;
    case R.id.delete:
      if (getListAdapter().getCount() > 0) {
        comment = (Film) getListAdapter().getItem(0);
        datasource.deleteComment(comment);
        adapter.remove(comment);
      }
      break;
    }
    adapter.notifyDataSetChanged();
  }

  @Override
  protected void onResume() {
    datasource.open();
    super.onResume();
  }

  @Override
  protected void onPause() {
    datasource.close();
    super.onPause();
  }

} 