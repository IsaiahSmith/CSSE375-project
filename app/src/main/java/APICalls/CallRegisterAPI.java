package APICalls;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.main.lets.lets.RegisterActivity;
import com.main.lets.lets.SignInActivity;

/**
 * Created by rajaa on 1/13/2016.
 */
public class CallRegisterAPI extends CallAPI {
    public Context appContext;
    public Context baseContext;
    public RegisterActivity registerActivity;

    public CallRegisterAPI(RegisterActivity registerActivity){
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
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            registerActivity.finish();
            appContext.startActivity(intent);
        }else if(result.equals("true")){
            Toast.makeText(
                    this.baseContext,
                    "Thanks for joining Let's!",
                    Toast.LENGTH_LONG).show();
            Intent signinPage = new Intent(this.appContext, SignInActivity.class);
            signinPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mainPage.putExtra(LOG_IN_USER,userEmailExtra.toString());
            // mainPage.putExtra(FROM_REGISTER, "true");
            appContext.startActivity(signinPage);
        }else{
            Toast.makeText(
                    this.baseContext,
                    result,
                    Toast.LENGTH_LONG).show();
            Intent intent = registerActivity.getIntent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            registerActivity.finish();
            appContext.startActivity(intent);
        }
    }
}

