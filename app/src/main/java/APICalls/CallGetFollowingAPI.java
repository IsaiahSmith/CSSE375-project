package APICalls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by smithij on 1/19/2016.
 */
public class CallGetFollowingAPI  extends CallAPI  {

    private ArrayList<String> following;

    protected void onPostExecute(String result) {
        try {
            JSONObject person = new JSONObject(result);
            JSONArray friends = person.getJSONArray("following");
            for(int i = 0; i < friends.length(); i++){
                this.following.add((String) friends.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getFollowing(){
        return this.following;
    }
}
