package APICalls;

import android.app.Activity;
import android.view.View;

import com.main.lets.lets.dummyEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Time;


/**
 * Created by rajaa on 1/13/2016.
 */
public class CallSearchAPI extends CallAPI {
    private final View view;
    private Activity app;

    public CallSearchAPI(Activity app, View v){
        this.app = app;
        this.view = v;
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

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
