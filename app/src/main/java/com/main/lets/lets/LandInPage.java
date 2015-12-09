package com.main.lets.lets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LandInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_in_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });

        Button signInButton = (Button) findViewById(R.id.land_in_sign_in);
        Button registerButton = (Button) findViewById(R.id.land_in_register);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignIN();


            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();


            }
        });


    }

    public void goToSignIN(){
        Intent mainPage = new Intent(getApplicationContext(), MainActivity.class);
        mainPage.putExtra("LOG_IN_USER","ismith2013@yahoo.com");
        mainPage.putExtra("FROM_LOG_IN","true");
        startActivity(mainPage);
        //showProgress(true);

        //Intent signIntent = new Intent(this,SignInActivity.class);
        //this.startActivity(signIntent);

    }
    public void goToRegister(){
        Intent registerIntent = new Intent(this,RegisterActivity.class);
        this.startActivity(registerIntent);

    }

}
