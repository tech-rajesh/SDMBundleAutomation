package com.test.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class RestClient {
	public static Properties prop;
	public File file;
	public FileInputStream fis;

	/**
	 * This method establish connection, deserialize the JSON and returns list of
	 * deserialize JSON objects.
	 * 
	 * @return
	 */
	public List<JSONObject> getSDMProductList() {
		List<JSONObject> jsonSDMList = new ArrayList<JSONObject>();
		try {
			// Establish connection
			URL url = new URL("http://10.200.10.70/cicd/rest/service/geocoding/bundles/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			// checking for valid response
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output = "";

			StringBuilder sb = new StringBuilder();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(sb.toString());

			TypeReference<List<JSONObject>> mapType = new TypeReference<List<JSONObject>>() {
			};
			jsonSDMList = mapper.readValue(root, mapType);

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return jsonSDMList;
	}



}