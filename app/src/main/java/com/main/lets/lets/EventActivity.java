package com.main.lets.lets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {
    private CharSequence mTitle;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final dummyEvent e = MainActivity.events.get(MainActivity.ACTIVE_EVENT);

        setTitle(e.getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e.isAttending()){
                    e.setAttending(false);
                    Snackbar.make(view, "You are no longer going.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else{
                    e.setAttending(true);
                    Snackbar.make(view, "You are now going!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });

        setLayout(e);
    }


    public void setLayout(dummyEvent e) {
        final dummyEvent rEvent = e;
        String str = "";
        for (String s : e.getRestrictions())
            str += s + ", ";
        str = str.substring(0, str.length() - 2);

        mLinearLayout = (LinearLayout) findViewById(R.id.EventLayout);
        View v = getLayoutInflater().inflate(R.layout.fragment_event,
                mLinearLayout);

        Space s = (Space) v.findViewById(R.id.space);
        s.setMinimumWidth(50);

        Button b = (Button) v.findViewById(R.id.button4);
        b.setTextColor(Color.LTGRAY);
        b.setBackgroundColor(Color.WHITE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.events.remove(rEvent);
                rEvent.setAttending(false);

                finish();
            }
        });

        TextView[] tv = {(TextView) v.findViewById(R.id.LocationView),
                (TextView) v.findViewById(R.id.TimeView),
                (TextView) v.findViewById(R.id.DescriptionView),
                (TextView) v.findViewById(R.id.AttendenceView),
                (TextView) v.findViewById(R.id.CapView),
                (TextView) v.findViewById(R.id.RestrictionsView)};

        tv[1].setText("Change in EventActivity");
        tv[0].setText(e.getStartTimeString() + " - " + e.getEndTimeString());
        tv[2].setText(e.getDescription());
        tv[3].setText("Change in Event Activity" + " People are going");
        tv[4].setText(e.getMaxA() + " People max");
        tv[5].setText("Restrictions: " + str);

        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        rlp.setMargins(0, 100, 0, 0);
        tv[0].setLayoutParams(rlp);
        tv[5].setLayoutParams(rlp);

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.EventLL2);
        ll.setLayoutParams(rlp);

        ImageView iv = (ImageView) v.findViewById(R.id.IconView);

        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);

        iv.setImageBitmap(bm);


    }
}
