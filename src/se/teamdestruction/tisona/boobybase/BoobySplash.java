/**
 * Author Kasper Wilkosz
 * Tisona
 */

package se.teamdestruction.tisona.boobybase;

import com.teamdestruction.chaos.boobbase.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BoobySplash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.booby_splash);
		Thread logoTimer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(1000);
					Intent menuIntent = new Intent("android.intent.action.SEARCHFILM");
					startActivity(menuIntent);
				} catch(InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
				}
			}
		};
		logoTimer.start();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
}
