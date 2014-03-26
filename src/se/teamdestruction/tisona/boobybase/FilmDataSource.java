/**
 * Author Kasper Wilkosz
 * Tisona
 */


package se.teamdestruction.tisona.boobybase;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FilmDataSource {

  // Database fields
  private SQLiteDatabase database;
  private SQLiteHelper dbHelper;
  private String[] allColumns = { SQLiteHelper.COLUMN_ID,
      SQLiteHelper.COLUMN_COMMENT };

  public FilmDataSource(Context context) {
    dbHelper = new SQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Film createComment(String comment) {
    ContentValues values = new ContentValues();
    values.put(SQLiteHelper.COLUMN_COMMENT, comment);
    long insertId = database.insert(SQLiteHelper.TABLE_COMMENTS, null,
        values);
    Cursor cursor = database.query(SQLiteHelper.TABLE_COMMENTS,
        allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Film newComment = cursorToComment(cursor);
    cursor.close();
    return newComment;
  }

  public void deleteComment(Film comment) {
    long id = comment.getId();
    System.out.println("Comment deleted with id: " + id);
    database.delete(SQLiteHelper.TABLE_COMMENTS, SQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  public List<Film> getAllComments() {
    List<Film> comments = new ArrayList<Film>();

    Cursor cursor = database.query(SQLiteHelper.TABLE_COMMENTS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Film comment = cursorToComment(cursor);
      comments.add(comment);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return comments;
  }

  private Film cursorToComment(Cursor cursor) {
    Film comment = new Film();
    comment.setId(cursor.getLong(0));
    comment.setComment(cursor.getString(1));
    return comment;
  }
} 