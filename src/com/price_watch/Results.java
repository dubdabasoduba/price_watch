/**
 * 
 */
package com.price_watch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * @author benjamin
 *
 */
public class Results extends Activity implements OnClickListener {
	 private String jsonResult;
	 private String url = "http://localhost/priceWatch/pricewatchselect.php";
	 private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		listView = (ListView) findViewById(R.id.listView1);
		accessWebService();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
		return true;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

	 // Async Task to access the web
	 private class JsonReadTask extends AsyncTask<String, Void, String> {
	  @Override
	  protected String doInBackground(String... params) {
	   HttpClient httpclient = new DefaultHttpClient();
	   HttpPost httppost = new HttpPost(params[0]);
	   try {
	    HttpResponse response = httpclient.execute(httppost);
	    jsonResult = inputStreamToString(
	      response.getEntity().getContent()).toString();
	   }
	 
	   catch (ClientProtocolException e) {
	    e.printStackTrace();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	   return null;
	  }
	 
	  private StringBuilder inputStreamToString(InputStream is) {
	   String rLine = "";
	   StringBuilder answer = new StringBuilder();
	   BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	 
	   try {
	    while ((rLine = rd.readLine()) != null) {
	     answer.append(rLine);
	    }
	   }
	 
	   catch (IOException e) {
	    // e.printStackTrace();
	    Toast.makeText(getApplicationContext(),
	      "Error..." + e.toString(), Toast.LENGTH_LONG).show();
	   }
	   return answer;
	  }
	 
	  @Override
	  protected void onPostExecute(String result) {
	   ListDrwaer();
	  }
	 }
	  public void accessWebService() {
		  JsonReadTask task = new JsonReadTask();
		  // passes values for the urls string array
		  task.execute(new String[] { url });
		 }
	  
	  // build hash set for list view
	  public void ListDrwaer() {
	   List<Map<String, String>> employeeList = new ArrayList<Map<String, String>>();
	  
	   try {
	    JSONObject jsonResponse = new JSONObject(jsonResult);
	    JSONArray jsonMainNode = jsonResponse.optJSONArray("persons");
	  
	    for (int i = 0; i < jsonMainNode.length(); i++) {
	     JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
	     String name = jsonChildNode.optString("firstname");
	     String number = jsonChildNode.optString("telphone");
	     String outPut = name + "-" + number;
	     employeeList.add(createEmployee("persons", outPut));
	    }
	   } catch (JSONException e) {
	    Toast.makeText(getApplicationContext(), "Error" + e.toString(),
	      Toast.LENGTH_SHORT).show();
	   }
	  
	   SimpleAdapter simpleAdapter = new SimpleAdapter(this, employeeList,
	     android.R.layout.simple_list_item_1,
	     new String[] { "persons" }, new int[] { android.R.id.text1 });
	   listView.setAdapter(simpleAdapter);
	  }
	  
	  private HashMap<String, String> createEmployee(String name, String number) {
	   HashMap<String, String> employeeNameNo = new HashMap<String, String>();
	   employeeNameNo.put(name, number);
	   return employeeNameNo;
	  }
	  
	 }


