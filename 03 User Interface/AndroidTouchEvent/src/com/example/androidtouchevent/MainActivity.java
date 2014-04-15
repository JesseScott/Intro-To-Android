package com.example.androidtouchevent;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//
		  final TextView textView = (TextView)findViewById(R.id.textView);
		    // this is the view on which you will listen for touch events
		    final View touchView = findViewById(R.id.touchView);
		    touchView.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
			        textView.setText("Touch coordinates : " +
			                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
					return false;
				}
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
