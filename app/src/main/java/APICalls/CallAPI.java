package APICalls;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by smithij on 1/6/2016.
 */
public abstract class CallAPI extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        String urlString=params[0]; // URL to call
        String resultToDisplay;
        BufferedReader in = null;
        String answer = "";

        // HTTP Get
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        } catch (Exception e ) {

            System.out.println(e.getMessage());

            return e.getMessage();

        }

        try {
            answer = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  answer;
    }

    protected abstract void onPostExecute(String result);

}
