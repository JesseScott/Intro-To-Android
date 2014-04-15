package com.example.androidmusicplayer;



import java.io.IOException;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	private MediaPlayer mp;
	private ImageButton button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Media Player
		final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.sample);
		/*
		//mp = new MediaPlayer();
		Uri uri = Uri.parse("android.resource://android.MainActivity/R.raw.sample");
		//mp.setDataSource(MainActivity.this, R.raw.sample);
		//mp.setDataSource(uri);
		try {
			mp.setDataSource("android.resource://android.MainActivity/R.raw.sample");
			mp.prepare();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// Button
		button = (ImageButton) findViewById(R.id.button);
		button.setImageResource(R.drawable.img_btn_play);
				button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// check for already playing
						if(mp.isPlaying()){
							if(mp != null){
								mp.pause();
								// Changing button image to play button
								button.setImageResource(R.drawable.img_btn_play);
							}
						}else{
							// Resume song
							if(mp != null){
								mp.start();
								// Changing button image to pause button
								button.setImageResource(R.drawable.img_btn_pause);
							}
						}
					}
				});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(mp != null) {
			mp.release();
			mp = null;
		}
	}

}
