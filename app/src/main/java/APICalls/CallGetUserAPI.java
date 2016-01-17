package APICalls;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rajaa on 1/13/2016.
 */
public class CallGetUserAPI extends CallAPI {

    public Activity callAPI;
    public String signName;
    public String signAddress;
    public String signGender;
    public CallGetUserAPI(Activity callAPI, String name, String address, String Gender){
        this.signName = name;
        this.signAddress = address;
        this.signGender = Gender;

    }
    protected void onPostExecute(String result) {
        try {
            JSONObject person = new JSONObject(result);
            JSONObject nameObj = person.getJSONObject("profile");
            signName= nameObj.get("name").toString();
            JSONObject addObj = nameObj.getJSONObject("address");
            signAddress =addObj.get("city").toString() + ", " + addObj.get("state").toString();

            signGender = nameObj.get("gender").toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
