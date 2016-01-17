package APICalls;

import com.main.lets.lets.dummyEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Time;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.main.lets.lets.MainActivity;



/**
 * Created by rajaa on 1/13/2016.
 */
public class CallSearchAPI extends CallAPI {
    Activity callSearch;
    public CallSearchAPI(Activity callSearch){

    }
    protected void onPostExecute(String result) {

        try {
            String splitter = "@@@@@";

            String[] eventsList = result.split(splitter);

            for(String e : eventsList){

                dummyEvent dum = new dummyEvent();
                JSONObject event = new JSONObject(e);

                dum.setTitle(event.get("title").toString());


                dum.setDescription(event.get("description").toString());
                dum.setMaxA(10);

                dum.setStartTime(new Time(2, 0, 0));
                dum.setEndTime(new Time(4, 0, 0));


                JSONObject coor = event.getJSONObject("location");
                JSONArray coorPoint = coor.getJSONArray("coordinates");
                dum.setCoords(coorPoint.getInt(0), coorPoint.getInt(1));



                dum.setDate(new Date(2015, 11, 20));

                String[] args = {

                        "BSB 109",
                        "9:45PM - 11:45PM",
                        "0", "1"};

                String[] res = {"Public"};

                // events.add(new dummyEvent());
               MainActivity. events.add(dum);


            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Make addFeed() static
        //MainActivity.addFeed();


    }

}
