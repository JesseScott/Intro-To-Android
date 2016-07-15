package com.example.androidvideoplayer;

// App
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
// IO
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.util.Log;
// Screen
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {
	
	// VARS
	private static final String TAG = "AndroidVideoPlayer";
	private static final String filePath = "/sdcard/Movies/GML Trailer.mp4";
	

	private VideoView myVideoView;
	private EditText Path;
	private ImageButton Play;
	private ImageButton Pause;
	private ImageButton Reset;
	private ImageButton Stop;
	private String current;

	// onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Layout
		setContentView(R.layout.activity_main);
		// View
		myVideoView = (VideoView) findViewById(R.id.surface_view);
		
		// Buttons
		Play = 	(ImageButton) findViewById(R.id.play);
		Pause = (ImageButton) findViewById(R.id.pause);
		Reset =	(ImageButton) findViewById(R.id.reset);
		Stop =	(ImageButton) findViewById(R.id.stop);
		
		// Text Box
		Path = (EditText) findViewById(R.id.path);
		Path.setText(filePath);
		
		// Clicks
		Play.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				playVideo();
			}
		});
		
		Pause.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (myVideoView != null) {
					myVideoView.pause();
				}
			}
		});
		
		Reset.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (myVideoView != null) {
					myVideoView.seekTo(0);
				}
			}
		});
		
		Stop.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (myVideoView != null) {
					current = null;
					myVideoView.stopPlayback();
				}
			}
		});
		
		// Thread
		runOnUiThread(new Runnable() {
			public void run() {
				playVideo();
			}
		});
		
	} /* End of onCreate() */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	// Play Video
	private void playVideo() {
		try {
			final String path = Path.getText().toString();
			Log.v(TAG, "path: " + path);
			if (path == null || path.length() == 0) {
				Toast.makeText(MainActivity.this, "File URL/path is empty",
						Toast.LENGTH_LONG).show();
			} else {
				// If the path has not changed, just start the media player
				if (path.equals(current) && myVideoView != null) {
					myVideoView.start();
					myVideoView.requestFocus();
					return;
				}
				current = path;
				Log.v(TAG, "Setting Movie From " + filePath);
				myVideoView.setVideoPath(filePath);
				myVideoView.start();
				myVideoView.requestFocus();

			}
		} catch (Exception e) {
			Log.e(TAG, "playVideo | Error: " + e.getMessage(), e);
			if (myVideoView != null) {
				myVideoView.stopPlayback();
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (myVideoView != null) {
			myVideoView.stopPlayback();
			//myVideoView.release();
			myVideoView = null;
		}
	}


} /* End of Activity */
