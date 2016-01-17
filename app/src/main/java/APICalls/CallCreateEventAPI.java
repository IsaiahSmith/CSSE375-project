package APICalls;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


/**
 * Created by rajaa on 1/13/2016.
 */
public class CallCreateEventAPI extends CallAPI {
    public Context appContext;
    public Context baseContext;
    public Activity eventActivity;
    public CallCreateEventAPI(Activity eventActivity){
        this.appContext = eventActivity.getApplicationContext();
        this.baseContext = eventActivity.getBaseContext();

    }


    protected void onPostExecute(String result) {
        if(result.equals("true")){
            //do the stuff saying you have registered!
            Toast.makeText(this.baseContext, "Your event was created!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this.baseContext, "Your event was not created due to an error! Please try again.", Toast.LENGTH_LONG).show();
        }
    }
}
