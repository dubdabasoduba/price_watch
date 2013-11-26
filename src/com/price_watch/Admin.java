/**
 * 
 */
package com.price_watch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import db.Databasehandler;
import db.Userfunctions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author benjamin
 *
 */

public class Admin extends Activity implements OnClickListener {
	Button login;
	EditText emailjava,passwordjava;
	TextView forgot,register;
	AlertDialog alert;
	
	 private static String KEY_SUCCESS = "success";
	    private static final String KEY_ID = "personid";
	    private static final String KEY_FIRSTNAME = "firstname";
	    private static final String KEY_SECONDNAME = "secondname";
	    private static final String KEY_EMAIL = "email";
	    private static final String KEY_TELEPHONE = "telphone";
	    private static final String KEY_POBOX = "pobox";
	    private static final String KEY_PASSWORD = "password";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		login = (Button)findViewById((R.id.btnlogin));
		this.login.setOnClickListener(this);
		 emailjava=(EditText)findViewById((R.id.email));
		passwordjava=(EditText)findViewById((R.id.password));
		forgot=(TextView)findViewById((R.id.forgot));
		this.forgot.setOnClickListener(this);
		register=(TextView)findViewById((R.id.register));
		this.register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		  if(v==this.login)
	        {
				  
	                if (  ( !emailjava.getText().toString().equals("")) && ( !passwordjava.getText().toString().equals("")) )
	                {
	                    NetAsync(v);
	                }
	                else if ( ( !emailjava.getText().toString().equals("")) )
	                {
	                    Toast.makeText(getApplicationContext(),
	                            "Password field empty", Toast.LENGTH_SHORT).show();
	                }
	                else if ( ( !passwordjava.getText().toString().equals("")) )
	                {
	                    Toast.makeText(getApplicationContext(),
	                            "Email field empty", Toast.LENGTH_SHORT).show();
	                }
	                else
	                {
	                    Toast.makeText(getApplicationContext(),
	                            "Email and Password field are empty", Toast.LENGTH_SHORT).show();
	                }
	            }
	     	        
	            else if(v==forgot){
	            	
	            	Intent i = new Intent(getApplicationContext(), Passcodechange.class);
					startActivity(i);
	            }
	            else{
	            	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10.0.2.2/xampp/splash.php"));
	            	startActivity(browserIntent);
	            }
	}
	                
//	private class NetCheck extends AsyncTask
//	    {
//	        private ProgressDialog nDialog;
//	 
//	        @Override
//	        protected void onPreExecute(){
//	            super.onPreExecute();
//	            nDialog = new ProgressDialog(Admin.this);
//	            nDialog.setTitle("Checking Network");
//	            nDialog.setMessage("Loading...");
//	            nDialog.setIndeterminate(false);
//	            nDialog.setCancelable(true);
//	            nDialog.show();
//	        }
//
//			@Override
//			protected Object doInBackground(Object... params) {
//				   ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//		            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//		            if (netInfo != null && netInfo.isConnected()) {
//		                try {
//		                    URL url = new URL("http://10.0.2.2/xampp/splash.php");
//		                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
//		                    urlc.setConnectTimeout(3000);
//		                    urlc.connect();
//		                    if (urlc.getResponseCode() == 200) {
//		                        return true;
//		                    }
//		                } catch (MalformedURLException e1) {
//		                    // TODO Auto-generated catch block
//		                    e1.printStackTrace();
//		                } catch (IOException e) {
//		                    // TODO Auto-generated catch block
//		                    e.printStackTrace();
//		                }
//		            }
//				return false;
//			}  
//			
//			 
//			protected void onPostExecute(Boolean th){
//		 
//		            if(th == true){
//		                nDialog.dismiss();
//		               new ProcessLogin().execute();
//		            }
//		            else{
//		                nDialog.dismiss();
//		                Toast.makeText(getApplicationContext(),
//	                            "ERROR IN NETWORK CONNECTION", Toast.LENGTH_SHORT).show();
//		            }
//		        }
//	        
//	    }
	 private class ProcessLogin extends AsyncTask {
		 
	        private ProgressDialog pDialog;
	 
	         String inputemail,inputpassword;

	 
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();

	            inputemail = emailjava.getText().toString();
	            inputpassword = passwordjava.getText().toString();
	            pDialog = new ProgressDialog(Admin.this);
	            pDialog.setTitle("Contacting Servers");
	            pDialog.setMessage("Logging in ...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }
	 
	        protected JSONObject doInBackground(String... args) {
	            
	            Userfunctions userFunction = new Userfunctions();
	            JSONObject json = userFunction.LoginUser(inputemail, inputpassword);
	            return json;
	        }
	 
	        protected void onPostExecute(JSONObject json) {
	            try {
	               if (json.getString(KEY_SUCCESS) != null) {
	 
	                    String res = json.getString(KEY_SUCCESS);
	 
	                    if(Integer.parseInt(res) == 1){
	                        pDialog.setMessage("Loading Dashboard");
	                        pDialog.setTitle("Getting Data...");
	                        Databasehandler db = new Databasehandler(getApplicationContext());
	                        JSONObject json_user = json.getJSONObject("user");
	                        
	                        /* Clear all previous data in SQlite database.
	                         **/
	                        Userfunctions logout = new Userfunctions();
	                        logout.logoutUser(getApplicationContext());
	                        db.addUser(json_user.getString(KEY_FIRSTNAME),json_user.getString(KEY_SECONDNAME),json_user.getString(KEY_EMAIL),json_user.getString(KEY_TELEPHONE),json_user.getString(KEY_POBOX),json_user.getString(KEY_PASSWORD));
	                       /**
	                        *If JSON array details are stored in SQlite it launches the User Panel.
	                        **/
	                        Intent upanel = new Intent(getApplicationContext(), Dashboard.class);
	                        upanel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                        pDialog.dismiss();
	                        startActivity(upanel);
	                        /**
	                         * Close Login Screen
	                         **/
	                        finish();
	                    }else{
	 
	                        pDialog.dismiss();
	                        Toast.makeText(getApplicationContext(),
		                            "INCORRECT PASSWORD OR EMAIL", Toast.LENGTH_SHORT).show();
	                    }
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	       }

			@Override
			protected Object doInBackground(Object... params) {
				// TODO Auto-generated method stub
				return null;
			}

	    }
	 
	    public void NetAsync(View v){
	    	new ProcessLogin().execute();
	       // new NetCheck().execute();
	    }
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loginmenu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.login:
	        	Intent i = new Intent(getApplicationContext(),Admin.class);
				startActivity(i);
	            return true;
	        case R.id.help:
	        	Intent a = new Intent(getApplicationContext(), Help.class);
				startActivity(a);
	            return true;
	        case R.id.cancel:
	        	Intent b = new Intent(getApplicationContext(),index.class);
				startActivity(b);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}



	    

}
