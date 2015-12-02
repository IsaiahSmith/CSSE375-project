package com.main.lets.lets;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventActivity2 extends Activity {
	LinearLayout mLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_event);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.event, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

//	public void setLayout(String[] args, String[] restrictions) {
//		String str = "";
//		for (String s : restrictions)
//			str += s + ", ";
//		str = str.substring(0, str.length() - 2);
//
//		mLinearLayout = (LinearLayout) findViewById(R.id.eventLL);
//		View v = getLayoutInflater().inflate(R.layout.fragment_event,
//				mLinearLayout);
//
//		TextView[] tv = { (TextView) v.findViewById(R.id.TitleView),
//				(TextView) v.findViewById(R.id.LocationView),
//				(TextView) v.findViewById(R.id.TimeView),
//				(TextView) v.findViewById(R.id.DescriptionView),
//				(TextView) v.findViewById(R.id.AttendenceView),
//				(TextView) v.findViewById(R.id.CapView),
//				(TextView) v.findViewById(R.id.RestrictionsView) };
//
//		tv[0].setText(args[0]);
//		tv[1].setText(args[1]);
//		tv[2].setText(args[2]);
//		tv[3].setText(args[3]);
//		tv[4].setText(args[4] + " People are going");
//		tv[5].setText(args[5] + " People max");
//		tv[6].setText("Restrtions: " + str);
//
//		LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.WRAP_CONTENT,
//				LinearLayout.LayoutParams.WRAP_CONTENT);
//
//		rlp.setMargins(0, 100, 0, 0);
//		tv[1].setLayoutParams(rlp);
//		tv[6].setLayoutParams(rlp);
//
//		LinearLayout ll = (LinearLayout) v.findViewById(R.id.EventLL2);
//		ll.setLayoutParams(rlp);
//
//		ImageView iv = (ImageView) v.findViewById(R.id.IconView);
//
//		Bitmap bm = BitmapFactory.decodeResource(getResources(),
//				R.drawable.drinks);
//
//		iv.setImageBitmap(bm);
//
//
//	}

}
