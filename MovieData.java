import java.io.*;
import java.net.*;
import java.nio.charset.*;
import org.json.simple.*;

public class MovieData {

	private MovieData() {

	}

	private InputStream queryApi(String api, String movieName) {
		String baseUrl;
		switch(api) {
			case "omdb":
				baseUrl = "http://www.omdbapi.com/";
				break;
			case "rottenTomato":
				baseUrl = "http://www.omdbapi.com/";
				break;
			default:
				baseUrl = "http://www.omdbapi.com/";
				break;
		}

		URL url;
		URLConnection connection;
		InputStream response;

		try {
			url = new URL(baseUrl + "?t=" + movieName);
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
				//JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
				//System.out.println(jsonObject.getString("Year"));
				System.out.println(responseStrBuilder.toString());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
}