/**
 * 
 */
package com.price_watch;



import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author Mulyungi
 *
 */
public class index extends Activity implements OnClickListener {
	
	ImageView compare,help,info,admin,search;
	//	private ProgressDialog pDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
//		the toast code 
		Context context = getApplicationContext();
		CharSequence text = "Please Click any of the Icons to continue..";		
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.BOTTOM, 0, duration);
		toast.show();
		
//		end of the toast code
		
/*//		Toast.makeText(getApplicationContext(),"Please Choose One Catergory To Continue",LENGTH_LONG);	
*/		compare =(ImageView)findViewById(R.id.screencompare);	
		help = (ImageView)findViewById(R.id.screenhelp);
		info =(ImageView)findViewById(R.id.screeninfo);
		admin = (ImageView)findViewById(R.id.screenadmin);
		search = (ImageView)findViewById(R.id.screensearch);
		
			compare.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching All products Activity
				Intent i = new Intent(getApplicationContext(), Compare.class);
				startActivity(i);
			
				
			}
		});
			
			search.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// Launching All products Activity
					Intent i = new Intent(getApplicationContext(), Search.class);
					startActivity(i);
				
					
				}
			});

	
	help.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// Launching All products Activity
			Intent i = new Intent(getApplicationContext(), Help.class);
			startActivity(i);
		
			
		}
	});

info.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View view) {
		// Launching All products Activity
		Intent i = new Intent(getApplicationContext(), Info.class);
		startActivity(i);
	
		
	}
});
admin.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View view) {
		// Launching All products Activity
		Intent i = new Intent(getApplicationContext(), Admin.class);
		startActivity(i);
	
		
	}
});
}
	
		
	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
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
}
