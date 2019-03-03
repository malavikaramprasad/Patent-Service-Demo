package com.patent.dataloader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.patent.model.Patent;
import com.patent.model.USPTOOutput;
import com.patent.model.USPTOResponse;
 
@Component
public class DataFetcher {
	private final String URL = "https://developer.uspto.gov/ibd-api/v1/patent/application";
	private final int HTTP_RESPONSE_OK = 200;
	private Gson gson = new Gson();
	
	public List<Patent> getData(String searchText) {
		System.out.println(URL);
		List<Patent> patentList;
		URL obj = null;
		try {
			String finalUrl = URL+"?searchText="+searchText;
			System.out.println(finalUrl);
			obj = new URL(finalUrl);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept-Encoding", "gzip");
			int responseCode = connection.getResponseCode();
			//Get the data and put into Patent Obj
			if (responseCode == HTTP_RESPONSE_OK) {
				InputStreamReader in = new InputStreamReader(new GZIPInputStream(connection.getInputStream()));
				USPTOOutput usptoOutput = gson.fromJson(in, USPTOOutput.class);
				USPTOResponse respData = usptoOutput.getResponse();
				patentList = respData.getDocs();
				System.out.println(patentList.size());
				return patentList;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				throw new Exception(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
}
