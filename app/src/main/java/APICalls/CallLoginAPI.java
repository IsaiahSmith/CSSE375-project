package APICalls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.main.lets.lets.MainActivity;
import com.main.lets.lets.SignInActivity;


/**
 * Created by smithij on 1/6/2016.
 */
public class CallLoginAPI extends CallAPI {
    public static final String LOG_IN_USER = "LOG_IN_USER";
    public static final String FROM_LOG_IN = "FROM_LOG_IN";

    public String email;
    public Context appContext;
    public Context baseContext;

    public CallLoginAPI(String email, Activity signInActivity){
        this.email = email;
        this.appContext = signInActivity.getApplicationContext();
        this.baseContext = signInActivity.getBaseContext();
    }

    @Override
    protected void onPostExecute(String result){
        if(result.length()>=20){
            Intent mainPage = new Intent(appContext, MainActivity.class);
            mainPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mainPage.putExtra(LOG_IN_USER, email);
            mainPage.putExtra(FROM_LOG_IN, "true");
            appContext.startActivity(mainPage);
        }else{
            Toast.makeText(baseContext, "Incorrect email/password. Please try again.", Toast.LENGTH_LONG).show();
            Intent signinPage = new Intent(appContext, SignInActivity.class);
            signinPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(signinPage);
        }
    }
}
