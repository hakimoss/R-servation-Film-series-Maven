package com.codeboxx.reservationFilm.CallApi;


import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;




public class CallApi {
	
	private final OkHttpClient httpClient = new OkHttpClient();
	
	OkHttpClient client = new OkHttpClient();
	private String responseFinal;
	
	public Object getImage(String mc) throws IOException {

        Request request = new Request.Builder()
                .url("https://tvjan-tvmaze-v1.p.rapidapi.com/search/shows?q="+mc)
                .get()
                .addHeader("x-rapidapi-host", "tvjan-tvmaze-v1.p.rapidapi.com")  // add request headers
                .addHeader("x-rapidapi-key", "41dd91cc0bmsh4b24b2eae64802ep1b4b7bjsn8aa5c5ecffcd")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Get response headers
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                //System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            responseFinal = response.body().string();
        }

        return parseImage(responseFinal);

    }
	
	public static Object parseImage(String responseBody) {
		JSONArray res=new JSONArray(responseBody);
		Object show = null;
		//String imageFilm = null;
		for (int i=0; i < res.length(); i++) {
			JSONObject movie = res.getJSONObject(i);
			show=movie.getJSONObject("show");
			//imageFilm = (String) show;
			break;
		}
		return show;		
	}
	
	
}
