package APICalls;

import android.widget.Toast;

import com.main.lets.lets.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smithij on 1/19/2016.
 */
public class CallGetFollowingAPI  extends CallAPI  {

    private final MainActivity main;
    private List<String> following = new ArrayList<String>();

    public CallGetFollowingAPI(MainActivity mainActivity){
        this.main = mainActivity;
    }

    protected void onPostExecute(String result) {
        try {
            JSONObject person = new JSONObject(result);
            JSONArray friends = person.getJSONArray("following");
            for (int i = 0; i < friends.length(); i++) {
                this.following.add(friends.getString(i));
            }
            Toast.makeText(this.main.getBaseContext(), this.following.get(0), Toast.LENGTH_LONG).show();
        }
        catch(JSONException e){

        }
    }

    public String[] getFollowing(){
        int size = this.following.size();
        String[] strArray = this.following.toArray(new String[size]);
        return strArray;
    }
}
