package APICalls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.main.lets.lets.RegisterActivity;

/**
 * Created by rajaa on 1/13/2016.
 */
public class CallRegisterAPI extends CallAPI {
    public Context appContext;
    public Context baseContext;
    public Activity registerActivity;

    public CallRegisterAPI(Activity registerActivity){
        this.appContext = registerActivity.getApplicationContext();
        this.baseContext = registerActivity.getBaseContext();

    }





    protected void onPostExecute(String result) {
        if(result.equals("false")){
            //do the stuff that says you failed:(
            Toast.makeText(
                    this.baseContext,
                    "You failed to register, try agian",
                    Toast.LENGTH_LONG).show();

            Intent intent = registerActivity.getIntent();
            registerActivity.finish();
            appContext.startActivity(intent);
        }else if(result.equals("true")){
            Toast.makeText(
                    this.baseContext,
                    "Thanks for joining Let's!",
                    Toast.LENGTH_LONG).show();
            Intent mainPage = new Intent(this.appContext, RegisterActivity.class);
//                mainPage.putExtra(LOG_IN_USER,userEmailExtra.toString());
            // mainPage.putExtra(FROM_REGISTER, "true");
            appContext.startActivity(mainPage);
        }else{
            Toast.makeText(
                    this.baseContext,
                    result,
                    Toast.LENGTH_LONG).show();
            Intent intent = registerActivity.getIntent();
            registerActivity.finish();
            appContext.startActivity(intent);
        }
    }
}

