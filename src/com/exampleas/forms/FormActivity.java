package com.exampleas.forms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.auth.AuthenticationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;




public class FormActivity extends Activity {
	
	private final Log log = LogFactory.getLog(FormActivity.class);

    private static final String METHOD_POST = "POST";

    private static String formsubmissionJson;

    private static String encounterJson;
    
    private static String provider_uuid;
    private static String location_uuid;
    private static String obs_uuid;
    private static String type;
    private static String value;

    private static String consultationJson;
   // private EditText value;
    private Button btn;
    private ProgressBar pb;
    Spinner sprCoun;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		//value=(EditText)findViewById(R.id.editText1);
		//btn=(Button)findViewById(R.id.bn_post);
		//pb=(ProgressBar)findViewById(R.id.progressBar1);
		//pb.setVisibility(View.GONE);
		//btn.setOnClickListener(this);
		
		
		
		/*Spinner spinner = (Spinner) findViewById(R.id.spinner2);{
		String Text = spinner.getSelectedItem().toString();{
			
			if (Text.equals("Win")) {
				provider_uuid= ("3c023ab8-82ff-11e2-96ef-f0def12f7061");
			}
				else
					if (Text.equals("Admin"))
						
						provider_uuid= ("3c023ab8-82ff-11e2-96ef-f0def12f7061");
			}
		
			Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
			String Text_3 = spinner.getSelectedItem().toString();
			{
			if (Text_3 == "Reach") {
				location_uuid= ("e1b1d9eb-69a2-4843-bccc-e73e33c70e4c");
			}
				else
					if (Text == "Ampath")
						
						location_uuid= ("8d6c993e-c2cc-11de-8d13-0010c6dffd0f");
			};
		}
		*/
		
		
       
	}
	
	/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form, menu);
		return true;
	}

*/


	public void sendFeedback(View button) {
		
		final Spinner feedbackSpinner = (Spinner) findViewById(R.id.spinner2);  
		String provider = feedbackSpinner.getSelectedItem().toString(); 

			
			if (provider.equals("Win")) 
				provider_uuid= ("3c023ab8-82ff-11e2-96ef-f0def12f7061");
			
				else
					if (provider.equals("Admin"))
						
						provider_uuid= ("3c023ab8-82ff-11e2-96ef-f0def12f7061");
		
			final Spinner locationSpinner = (Spinner) findViewById(R.id.spinner3);  
			String location = locationSpinner.getSelectedItem().toString(); 
			
			if(location.equals("Reach"))
				location_uuid=("e1b1d9eb-69a2-4843-bccc-e73e33c70e4c");
			else
				if(location.equals("Ampath"))
			location_uuid=("3d0c01b7-0306-45cd-a86f-8c96fee0b012");
			
			final Spinner SputumAcidSpinner = (Spinner) findViewById(R.id.spinner1);  
			String Sputtest = SputumAcidSpinner.getSelectedItem().toString(); 
			
			if (Sputtest.equals("THREEPLUS")){
				obs_uuid=("307AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				type=("coded");
				value=("1364AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			} else 
							
					if(Sputtest.equals("ONEPLUS")){
						obs_uuid=("307AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
						type=("coded");
						value=("1362AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
					}
					else
						
						if(Sputtest.equals("TwOPLUS")){
							obs_uuid=("307AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
							type=("coded");
							value=("1363AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
						} else
							if(Sputtest.equals("NEGATIVE")){
								obs_uuid=("307AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
								type=("coded");
								value=("664AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			
							}else
								if(Sputtest.equals("POSITIVE")){
									obs_uuid=("307AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
									type=("coded");
									value=("703AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				
								}
			final Spinner SputumAppearanceSpinner = (Spinner) findViewById(R.id.spinner4);  
			String Appearance = SputumAppearanceSpinner.getSelectedItem().toString(); 
			if(Appearance.equals("Purulent")){
				obs_uuid=("159969AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				type=("coded");
				value=("1076AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

			} else
				if(Appearance.equals("Red")){
					obs_uuid=("159969AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
					type=("coded");
					value=("127778AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				}
				else
					if(Appearance.equals("Saliva")){
						obs_uuid=("159969AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
						type=("coded");
						value=("160013AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
						
					}else
						if(Appearance.equals("Mucopulurent")){
							obs_uuid=("159969AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
							type=("coded");
							value=("160014AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");}
			
			
				
			JSONObject formObject = new JSONObject();
			
			
	        try {
				formObject.put("dataSource", "954b9e59-eba0-4679-afbb-2878580c054d");
				 formObject.put("discriminator", "encounter");
			        
			        JSONObject payload = new JSONObject();
			        
			        
					JSONObject encounter= new JSONObject();
					encounter.put("form.uuid","fe9fc58f-d196-46cb-956d-f46445f558d9");
					encounter.put("encounterType.uuid","bdb58960-4d91-4ca7-a27a-8dabde40ec12");
					encounter.put("provider.uuid",provider_uuid);
					encounter.put("location.uuid",location_uuid);
					encounter.put("datetime","2013-06-12 00:00:00");
					
					System.out.println("Encounter Object: **************"+ encounter.toString());
					
					
					JSONObject person= new JSONObject();
					person.put("person.uuid","269857b6-f0b9-4c93-af4a-f18293679e89");//replace value with this.getString(R.Id)

					JSONArray obs=new JSONArray();
					JSONObject o = new JSONObject();
					o.put("uuid", obs_uuid);//obs uuid for SPUTUM FOR ACID FAST BACILLI
					o.put("type",type);//coded
					o.put("value",value);
					obs.put(o);
					
					payload.put("person",person); // Device type
					payload.put("encounter",encounter);
					payload.put("obs",obs);
					
					
					
			       
			        formObject.put("payload", payload);
			      
			        formsubmissionJson = formObject.toString();
			        
			        
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//b.setVisibility(View.VISIBLE);
			new MyAsyncTask().execute();	
			
		
	}
	
	private class MyAsyncTask extends AsyncTask<String, Integer, Double>{
		 
@Override
protected Double doInBackground(String... params) {
// TODO Auto-generated method stub
try {
	postingQueueData();
	
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;
}

public void postingQueueData() throws Exception   {
    URL url = new URL("http://192.168.1.5:8081/openmrs-standalone/ws/rest/v1/muzima/queueData");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    String encodedAuthorization = "Basic " + Base64.encodeToString("admin:test".getBytes(), Base64.NO_WRAP);
   // Base64.encode("admin:test".getBytes());
  //  String encoding = Base64.encodeToString(AuthenticationException.getBytes(), Base64.NO_WRAP);
    connection.setRequestProperty("Authorization", encodedAuthorization);
    connection.setRequestMethod(METHOD_POST);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setDoOutput(true);

    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
    writer.write(formsubmissionJson);
    writer.flush();

    InputStreamReader reader = new InputStreamReader(connection.getInputStream());
    int responseCode = reader.read();
    if (responseCode == HttpServletResponse.SC_OK
            || responseCode == HttpServletResponse.SC_CREATED) {
        log.info("Queue data created!");
    }

    reader.close();
    writer.close();
}
 
protected void onPostExecute(Double result){
//pb.setVisibility(View.GONE);
//Toast.makeText(getApplicationContext(), "post sent", Toast.LENGTH_LONG).show();
}
protected void onProgressUpdate(Integer... progress){
//pb.setProgress(progress[0]);
}
 

 
}

}
