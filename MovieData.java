import java.io.*;
import java.net.*;
import java.nio.charset.*;
import org.json.*;

public class MovieData {

	private MovieData() {

	}

	private InputStream queryApi(String api, String name) {
		String baseUrl, searchParamKey, apiKey;
		switch(api) {
			case "omdb":
				baseUrl = "http://www.omdbapi.com/";
				searchParamKey = "t=";
				apiKey = "";
				break;
			case "rotten":
				baseUrl = "http://api.rottentomatoes.com/api/public/movies.json"; //API key application closed!
				searchParamKey = "q=";
				apiKey = "";
				break;
			case "spotify":
				baseUrl = "https://api.spotify.com/v1/search";
				searchParamKey = "type=album&q=album:";
				apiKey = "";
				break;
			default:
				baseUrl = "http://www.omdbapi.com/";
				searchParamKey = "t";
				apiKey = "";
				break;
		}

		URL url;
		URLConnection connection;
		InputStream response;

		try {
			url = new URL(baseUrl + "?apiKey=" + apiKey + "&" + searchParamKey + name);
			connection = url.openConnection();
			connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
			response = connection.getInputStream();
			return response;
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String args[]) {
		MovieData movieData = new MovieData();
		InputStream is = movieData.queryApi(args[0], args[1]);

		try{ 
			InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8.name());
			BufferedReader streamReader = new BufferedReader(isr);
			StringBuilder responseStrBuilder = new StringBuilder();
			String inputStr;

			while ((inputStr = streamReader.readLine()) != null) {
	    		responseStrBuilder.append(inputStr);

	   			//try {
				// 	JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
				// } catch(JSONException e) {
				// 	e.printStackTrace();
				// }

				//System.out.println(jsonObject.getString("Year"));
				System.out.println(inputStr);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
}