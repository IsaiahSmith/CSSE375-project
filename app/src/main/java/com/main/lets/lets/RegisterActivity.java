package com.main.lets.lets;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import APICalls.CallRegisterAPI;


public class RegisterActivity extends AppCompatActivity {
    public static final String LOG_IN_USER = "LOG_IN_USER";
    public static final String FROM_REGISTER = "FROM_REGISTER";
    private String userEmailExtra;
    public final static String apiURL = GlobalVars.ipAddr+"/InsertUserServlet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupActionBar();
        Button registerButton = (Button) findViewById(R.id.signUp);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempRegister();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void attempRegister(){

        EditText editEmail = (EditText) findViewById(R.id.email);
        Editable userEmail = editEmail.getText();
        userEmailExtra = userEmail.toString();
        EditText editPassword = (EditText) findViewById(R.id.password);
        Editable userPassword = editPassword.getText();
        EditText editConfirmPass = (EditText) findViewById(R.id.confirmPassword);
        Editable confirmPass = editConfirmPass.getText();
        EditText editFirstName = (EditText) findViewById(R.id.firstName);
        Editable userFirstName = editFirstName.getText();
        EditText editLastName = (EditText) findViewById(R.id.lastName);
        Editable userLastName = editLastName.getText();
        EditText editAddress = (EditText) findViewById(R.id.address);
        Editable userAddress = editAddress.getText();
        EditText editCity = (EditText) findViewById(R.id.city);
        Editable userCity = editCity.getText();
        EditText editState = (EditText) findViewById(R.id.state);
        Editable userState = editState.getText();
        EditText editZip = (EditText) findViewById(R.id.zip);
        Editable userZip = editZip.getText();
        EditText editDOB = (EditText) findViewById(R.id.dob);
        Editable userDOB = editDOB.getText();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.genderRadios);
        String gender = "";
        if(radioGroup.getCheckedRadioButtonId()!=-1){
            int id = radioGroup.getCheckedRadioButtonId();
            View radioButton = radioGroup.findViewById(id);
            int radioId = radioGroup.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
            gender = ((String) btn.getText()).toLowerCase();
        }

        if(userPassword.toString().equals(confirmPass.toString())){
            String urlString = apiURL+
                    "?_id="+userEmail+
                    "&name="+userFirstName+" "+userLastName+
                    "&password="+userPassword+
                    "&street="+userAddress+
                    "&city="+userCity+
                    "&state="+userState+
                    "&zipCode="+userZip+
                    "&gender="+gender+
                    "&dob="+userDOB;

            String parsedURL = urlString.replaceAll(" ", "%20");
            new CallRegisterAPI(this).execute(parsedURL);
        }else{
            Toast.makeText(
                    getBaseContext(),
                    "Your passwords do not match!",
                    Toast.LENGTH_LONG).show();
        }
    }
}

