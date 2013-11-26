/**
 * 
 */
package com.price_watch;

import org.json.JSONException;
import org.json.JSONObject;

import splash.Splashscreen;

import db.Databasehandler;
import db.Userfunctions;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author benjamin

 *
 */
public  class Passcodechange extends Activity implements OnClickListener{
	EditText passcode1,passcon,email;
	Button change;
	/**
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.passcode);
		
		email = (EditText)findViewById(R.id.email2);
		passcode1=(EditText)findViewById(R.id.passchange);
		passcon =(EditText)findViewById(R.id.passconfirm);
		change = (Button)findViewById(R.id.btnupdate);
		this.change.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		if(v==this.change)
		{
			   
           if ( email.getText().toString().equals(""))
            {
                Toast.makeText(getApplicationContext(),"Email field is Empty", Toast.LENGTH_SHORT).show();
            }
            else if(passcode1.getText().toString().equals(""))
            {
                Toast.makeText(getApplicationContext(),"New Password field is Empty", Toast.LENGTH_SHORT).show();
            }
            else if(passcon.getText().toString().equals(""))
            {
            	Toast.makeText(getApplicationContext(),"Confrim Password field is Empty", Toast.LENGTH_SHORT).show();
            }
            else if(passcode1.getText().toString()!= passcon.getText().toString())
            {
            	Toast.makeText(getApplicationContext(),"Password do not match please check again", Toast.LENGTH_SHORT).show();
            }
            else
            {
                changecode(v); 
            }	
		}
	}
	private class changepassword extends AsyncTask{
		private ProgressDialog pDialog;
		//define the variables
		String email1,passcode2,passcon1;
		
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();

	            email1 = email.getText().toString();
	            passcode2 =passcode1.getText().toString();
	            passcon1=passcon.getText().toString();
	            pDialog = new ProgressDialog(Passcodechange.this);
	            pDialog.setTitle("Contacting Servers");
	            pDialog.setMessage("Updating Password ...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

		@Override
		protected Object doInBackground(Object... params) {
			
			return null;
		}
		
        protected void onPostExecute(Object v) {
        	//returns the app to the login for the user to login using the new password 
            Intent i = new Intent(Passcodechange.this, Admin.class);
    		startActivity(i);
        	pDialog.dismiss();
       }
		
	}
	  public void changecode(View v){
	    	new changepassword().execute();
	        //new NetCheck().execute();
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.updatemenu, menu);
		return true;
	}

}
