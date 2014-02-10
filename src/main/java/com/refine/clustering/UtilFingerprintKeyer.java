package com.refine.clustering;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class UtilFingerprintKeyer {
	
	public JSONObject parseObj(JSONArray jsonArr) throws JSONException {
		
		TreeMap<String, ArrayList<String>> Map = 
		        new TreeMap<String, ArrayList<String>>();
		
		for(int i = 0; i < jsonArr.length(); i++ ) {
			
			String InputString = jsonArr.getString(i);
			
			FingerprintKeyer Fingerprint = new FingerprintKeyer();
			String Mapkey = Fingerprint.key(InputString, (Object[]) null);
			
			ArrayList<String> values = Map.get( Mapkey );
			
			if( null == values ) {
			    values = new ArrayList<String>();
			    Map.put( Mapkey, values );
			}

			values.add(InputString); 
		}
		
		JSONObject jsonObj = new JSONObject();	
		
		for (Entry<String, ArrayList<String>> entry : Map.entrySet()) {
			
			JSONArray jsonvalues = new JSONArray(entry.getValue());
			jsonObj.put(entry.getKey(), jsonvalues);
		}
				
		return jsonObj;
	}

}
