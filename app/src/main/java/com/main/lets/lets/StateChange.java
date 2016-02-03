package com.main.lets.lets;

import android.support.design.widget.Snackbar;
import android.widget.LinearLayout;

/**
 * Created by smithij on 1/20/2016.
 */
public class StateChange {

    private final MainActivity app;

    public StateChange(MainActivity app){
        this.app = app;
    }

    public void changeState(int state, LinearLayout mLinearLayout){
        if(state == 0){
            this.app.addFeed(this.app.mLinearLayout);
        }else if(state == 1){
            this.app.addProfile();
        }else{
            this.app.addFeed(this.app.mLinearLayout);
            Snackbar.make(mLinearLayout, "Event hidden", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
