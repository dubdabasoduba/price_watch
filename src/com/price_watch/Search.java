/**
 * 
 */
package com.price_watch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * @author benjamin
 *
 */
public class Search extends Activity implements OnClickListener{
    Spinner county,store;
    Button search;
    EditText item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchitem);
		
		search =(Button)findViewById(R.id.search);
		item=(EditText)findViewById(R.id.itemsearch);
		
		store = (Spinner) findViewById(R.id.stores);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		R.array.Storetype, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		store.setAdapter(adapter);
		
		county = (Spinner) findViewById(R.id.countyspinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> countyadapter = ArrayAdapter.createFromResource(this,
		R.array.county, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		countyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		county.setAdapter(countyadapter);
	
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
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
