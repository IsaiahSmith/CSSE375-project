package APICalls;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by smithij on 1/19/2016.
 */
public class CallFollowAPI extends CallAPI {

    private final Activity app;

    public CallFollowAPI(Activity activity){
        this.app = activity;
    }

    protected void onPostExecute(String result) {
        if(result.equals("true")){
            Toast.makeText(this.app.getBaseContext(), "You followed successfully!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this.app.getBaseContext(), "User does not exist! Please try again.", Toast.LENGTH_LONG).show();
        }
    }
}
