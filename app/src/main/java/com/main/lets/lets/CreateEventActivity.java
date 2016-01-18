package com.main.lets.lets;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class CreateEventActivity extends AppCompatActivity {
    public final static String apiURL = GlobalVars.ipAddr+"/InsertEventServlet";
    LinearLayout mLinearLayout;
    Time startTime;
    Time endTime;
    Date date;
    TimePicker tp;
    DatePicker dp;
    private static String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Intent data = this.getIntent();
        String logInUser = data.getStringExtra(MainActivity.LOG_IN_USER);
        currentUser = logInUser;
        Toast.makeText(getBaseContext(),logInUser, Toast.LENGTH_LONG).show();
        setStartTime();
//        createEvent();

    }

    public void setStartTime() {
        setTitle("Set Start Time");
        mLinearLayout = (LinearLayout) findViewById(R.id.CreateEventLayout);
        View v = getLayoutInflater().inflate(R.layout.fragment_time, mLinearLayout);

        tp = (TimePicker) v.findViewById(R.id.timePicker);
        Button b = (Button) v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = new Time(tp.getCurrentHour(), tp.getCurrentMinute(), 0);

                Snackbar.make(v, "Start time is " + startTime.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                setEndTime();

            }

        });

    }


    public void setEndTime() {
        setTitle("Set End Time");
        mLinearLayout.removeAllViews();
        mLinearLayout = (LinearLayout) findViewById(R.id.CreateEventLayout);
        View v = getLayoutInflater().inflate(R.layout.fragment_time, mLinearLayout);

        tp = (TimePicker) v.findViewById(R.id.timePicker);
        Button b = (Button) v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTime = new Time(tp.getCurrentHour(), tp.getCurrentMinute(), 0);

                Snackbar.make(v, "End time is " + endTime.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                setDate();
            }

        });

    }

    public void setDate() {
        setTitle("Set Date");
        mLinearLayout.removeAllViews();
        mLinearLayout = (LinearLayout) findViewById(R.id.CreateEventLayout);
        View v = getLayoutInflater().inflate(R.layout.fragment_date, mLinearLayout);

        dp = (DatePicker) v.findViewById(R.id.datePicker);
        Button b = (Button) v.findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = new Date(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());

                Snackbar.make(v, "Date set as " + date.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                createEvent();

            }

        });

    }

    public void createEvent() {
        setTitle("Create Event");
        mLinearLayout.removeAllViews();
        mLinearLayout = (LinearLayout) findViewById(R.id.CreateEventLayout);
        View v = getLayoutInflater().inflate(R.layout.fragment_create_event,
                mLinearLayout);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, MainActivity.CATOGORIES);

        AutoCompleteTextView eV = (AutoCompleteTextView) v
                .findViewById(R.id.CatText);
        eV.setAdapter(adapter);

        final EditText[] et = getEditTextArray(v);
        final CheckBox[] cb = getCheckBoxArray(v);

        Button b = (Button) v.findViewById(R.id.cButton);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] s = { et[0].getText().toString(), "Dummy Location",
                        et[1].getText().toString() + " - " + et[3].getText().toString(),
                        et[4].getText().toString()

                };

                for(String si: s){
                    if(si.matches("")){
                        Toast.makeText(getApplication(),
                                "All fields must be filled in.", Toast.LENGTH_SHORT)
                                .show();

                        return;
                    }

                }

                ArrayList<String> res = new ArrayList<String>();

                for (CheckBox c : cb) {
                    if (c.isChecked()) {
                        res.add(c.getText().toString());

                    }
                }

                String[] r = { "18+" };

                dummyEvent nEvent = populateEvent(new dummyEvent(), et);
                MainActivity.events.add(nEvent);
                new CallAPI().execute(createEventURLString(nEvent));
                finish();
            }
        });

    }

    private dummyEvent populateEvent(dummyEvent event, EditText[] et) {
        event.setTitle(et[0].getText().toString());

        event.setMinA(Integer.parseInt(et[2].getText().toString()));
        event.setMaxA(Integer.parseInt(et[3].getText().toString()));
        event.setDescription(et[4].getText().toString());
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setDate(date);
        return event;
    }

    private String createEventURLString(dummyEvent e) {
        String url = apiURL+
        "?owner_id="+currentUser+
        "&loc="+""+
        "&title="+e.getTitle()+
        "&startTime="+ "" +
        "&endTime="+ "" +
        "&street="+ "345 Sycamore St." +
        "&city="+ "Terre Haute"+
        "&state="+ "IN"+
        "&zipCode="+ "47803"+
        "&description="+ e.getDescription() +
        "&tags=" + e.getTags();
        return cleanURLString(url);
    }

    private String cleanURLString(String s) {
        return s.replaceAll(" ", "%20");
    }

    private EditText[] getEditTextArray(View v){
        return new EditText[]{(EditText) v.findViewById(R.id.TitleText),
                (EditText) v.findViewById(R.id.CatText),
                (EditText) v.findViewById(R.id.MinText),
                (EditText) v.findViewById(R.id.MaxText),
                (EditText) v.findViewById(R.id.DescText)
        };
    }

    private CheckBox[] getCheckBoxArray(View v) {
        return new CheckBox[]{ (CheckBox) v.findViewById(R.id.R1),
                (CheckBox) v.findViewById(R.id.R2),
                (CheckBox) v.findViewById(R.id.R3),
                (CheckBox) v.findViewById(R.id.R4),
                (CheckBox) v.findViewById(R.id.R5),
                (CheckBox) v.findViewById(R.id.R6)

        };
    }

    private class CallAPI extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String urlString=params[0]; // URL to call
            String resultToDisplay;
            BufferedReader in = null;
            String answer = "";

            // HTTP Get
            try {

                URL url = new URL(urlString);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            } catch (Exception e ) {

                System.out.println(e.getMessage());

                return e.getMessage();

            }

            try {
                answer = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  answer;
        }

        protected void onPostExecute(String result) {
            if(result.equals("true")){
                //do the stuff saying you have registered!
                Toast.makeText(getBaseContext(), "Your event was created!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getBaseContext(), "Your event was not created due to an error! Please try again.", Toast.LENGTH_LONG).show();
            }
        }

    }

}

