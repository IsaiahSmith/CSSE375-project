package com.main.lets.lets;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

import APICalls.CallEventAPI;
import APICalls.CallGetUserAPI;
import APICalls.CallSearchAPI;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int STATE = 0;
    public static int ACTIVE_EVENT = -1;
    public static LinkedList<dummyEvent> events;
    public FloatingActionButton fab;
    public static final String LOG_IN_USER = "LOG_IN_USER";
    public static String currentUser;

    public final static String apiURL = GlobalVars.ipAddr;

    private String signName;
    private String signGender;
    private String signAddress;



    /**
     * Used to store the last screen title. For use in
     */
    private CharSequence mTitle;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        events = new LinkedList<>();
        Intent data = this.getIntent();
        String logInUser = "";
        logInUser = data.getStringExtra(SignInActivity.LOG_IN_USER);

        currentUser = logInUser;

        String userInfoURL = apiURL + "/GetUserServlet?_id=" + currentUser;
        new CallGetUserAPI(this, signName, signAddress, signGender).execute(userInfoURL);

        String getEventURL = apiURL + "/GetAllEventsServlet";
        new CallEventAPI(this).execute(getEventURL);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateEvent();

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mLinearLayout = (LinearLayout) findViewById(R.id.MainLayout);


    }

    @Override
    public void onResume() {
        super.onResume();
        mLinearLayout.removeAllViews();

        switch (STATE){
            case 0:
                addFeed();
                break;
            case 1:
                addProfile();
                break;
            case 2:
                addFeed();
                Snackbar.make(mLinearLayout, "Event hidden", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feed) {
            STATE = 0;
            mLinearLayout.removeAllViews();
            fab.setVisibility(View.VISIBLE);
            addFeed();

        } else if (id == R.id.nav_profile) {
            STATE = 1;
            mLinearLayout.removeAllViews();
            addProfile();
        } else if (id == R.id.nav_filters) {
            mLinearLayout.removeAllViews();
            fab.setVisibility(View.GONE);
            addFilters();

        } else if(id == R.id.nav_friends){
            mLinearLayout.removeAllViews();
            fab.setVisibility(View.GONE);
            viewFriends();
        }
        else if (id == R.id.nav_send) {
            mLinearLayout.removeAllViews();
            Intent myIntent = new Intent(this, MapsActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addFilters() {
        View v = getLayoutInflater().inflate(R.layout.fragment_filters,
                mLinearLayout);

        TextView[] tv = {(TextView) v.findViewById(R.id.nameView),
                (TextView) v.findViewById(R.id.NumOfEvents),
                (TextView) v.findViewById(R.id.Score),
                (TextView) v.findViewById(R.id.Friends),
                (TextView) v.findViewById(R.id.Events)};
        Button searchButton = (Button) v.findViewById(R.id.filter_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });
    }

    public void doSearch(){
        View v = getLayoutInflater().inflate(R.layout.fragment_filters,
                mLinearLayout);
        TextView tags = (TextView) v.findViewById(R.id.tagEdit);

        String searchURL = apiURL + "/AdvancedSearchServlet";
        searchURL += "?tags="+tags;
        new CallSearchAPI(this).execute(searchURL);
    }

    public void viewFriends(){
        View v = getLayoutInflater().inflate(R.layout.fragment_friends,mLinearLayout);
        ListView friendList = (ListView) findViewById(R.id.list_view_friend);
        String[] dummyFriends = new String[] {"Runzi", "John","Coco","Aaron","Isaiah","Runzi", "John","Coco","Aaron","Isaiah","Runzi", "John","Coco","Aaron","Isaiah"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dummyFriends);
        friendList.setAdapter(adapter);

    }
    public void addFeed() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        for (int i = 0; i < events.size(); i++) {
            View v = getLayoutInflater().inflate(R.layout.sample_event_view, null);
            dummyEvent event = events.get(i);
            EventView e = (EventView) v.findViewById(R.id.event);

            e.setTitle(event.getTitle());
            e.setDate(event.getDateString());
            e.setTime(event.getStartTimeString() + " - " + event.getEndTimeString());

            final int x = i;
            e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ACTIVE_EVENT = x;
                    openEvent();

                }
            });

            e.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    if (MainActivity.events.get(x).isAttending()) {
                        Snackbar.make(v, "You're no longer going.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        MainActivity.events.get(x).setAttending(false);

                        return true;
                    }
                    Snackbar.make(v, "You're now going!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    MainActivity.events.get(x).setAttending(true);

                    return true;
                }
            });

            mLinearLayout.addView(v);
        }

    }

    public void openEvent() {
        Intent myIntent = new Intent(this, EventActivity.class);
        startActivity(myIntent);
    }

    public void openCreateEvent() {
        Intent myIntent = new Intent(this, CreateEventActivity.class);
        myIntent.putExtra(LOG_IN_USER,currentUser);
        startActivity(myIntent);

    }


    public void addProfile() {
        fab.setVisibility(View.GONE);
        View v = getLayoutInflater().inflate(R.layout.fragment_profile,
                mLinearLayout);

        TextView[] tv = {(TextView) v.findViewById(R.id.nameView),
                (TextView)v.findViewById(R.id.genderView),
                (TextView)v.findViewById(R.id.addressView),
                (TextView) v.findViewById(R.id.NumOfEvents),
                (TextView) v.findViewById(R.id.Score),
                (TextView) v.findViewById(R.id.Friends),
                (TextView) v.findViewById(R.id.Events)};

        ImageView iv = (ImageView) v.findViewById(R.id.imageView1);

        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        tv[0].setText(signName);
        tv[1].setText(signGender);
        tv[2].setText(signAddress);
        tv[3].setText("(81) Events");
        tv[4].setText("Score: (69)");

        // Put this on Right Side of Screen

        rlp.setMargins(0, 200, 0, 0);

        tv[3].setLayoutParams(rlp);
        tv[4].setLayoutParams(rlp);

        bm = Bitmap.createScaledBitmap(bm, 400, 400, true);
        iv.setImageBitmap(bm);

    }
}
