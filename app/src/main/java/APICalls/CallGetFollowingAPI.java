package APICalls;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.main.lets.lets.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by smithij on 1/19/2016.
 */
public class CallGetFollowingAPI  extends CallAPI  {

    private final MainActivity main;
    private final ListView friendList;
    private ArrayAdapter<String> followingAdapter;

    public CallGetFollowingAPI(MainActivity mainActivity, ArrayAdapter<String> adapter, ListView friendList){
        this.main = mainActivity;
        this.followingAdapter = adapter;
        this.friendList = friendList;
    }

    protected void onPostExecute(String result) {
        try {
            ArrayList<String> tempList = new ArrayList<String>();
            JSONObject person = new JSONObject(result);
            JSONArray friends = person.getJSONArray("following");
            for (int i = 0; i < friends.length(); i++) {
                tempList.add(friends.getString(i));
            }

            int size = tempList.size();
            String[] strArray = tempList.toArray(new String[size]);
            this.followingAdapter = new ArrayAdapter<String>(this.main, android.R.layout.simple_list_item_1, strArray);

            friendList.setAdapter(followingAdapter);
        }
        catch(JSONException e){

        }
    }
}
