/**
 * 
 */
package com.price_watch;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @author benjamin
 *
 */
public class Help extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compare, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.compare:
	        	Intent i = new Intent(getApplicationContext(), Compare.class);
				startActivity(i);
	            return true;
	        case R.id.help:
	        	Intent a = new Intent(getApplicationContext(), Help.class);
				startActivity(a);
	            return true;
	        case R.id.admin:
	        	Intent b = new Intent(getApplicationContext(),Admin.class);
				startActivity(b);
	            return true;
	        case R.id.aboutus:
	        	Intent c = new Intent(getApplicationContext(), Info.class);
				startActivity(c);
	            return true;
	        case R.id.search:
	        	Intent d = new Intent(getApplicationContext(), Search.class);
				startActivity(d);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

}
