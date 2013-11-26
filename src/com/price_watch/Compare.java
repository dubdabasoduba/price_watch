package com.price_watch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
	
	
public class Compare extends Activity implements OnItemSelectedListener {
	private EditText location;
	private EditText item;
	private Spinner store;
	private Button compare;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compare);
	
		location = (EditText) findViewById(R.id.location);
		item = (EditText) findViewById(R.id.item);
		compare=(Button)findViewById(R.id.compare);
		
		store = (Spinner) findViewById(R.id.storetypespinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		R.array.Storetype, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		store.setAdapter(adapter);
		
	compare.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching All products Activity
				Intent i = new Intent(getApplicationContext(), Results.class);
				startActivity(i);
			
				
			}
		});
		
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
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
