package com.example.androidmusicrecorder;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.media.MediaRecorder;
import java.io.File;
import java.io.IOException;


public class MainActivity extends Activity {

	// Declarations
	MediaRecorder mRecorder;
	
	// Variables
	public final String TAG = " -- MEDIA_RECORDER -- ";
	public final String dirName = "//sdcard//REC";
	public String recFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Layout
		setContentView(R.layout.activity_main);
		
		// Create Custom Directory
		try{
		    File newFile = new File(dirName);
		    Log.v(TAG, "dirName is " + dirName);
		    newFile.mkdirs();
		    if(newFile.exists()) {
		      if(newFile.isDirectory()) {
		        Log.v(TAG, "Its A Directory...");
		      }
		    } 
		    else {
		    		Log.v(TAG, "Directory Doesn't Exist...");
	    		}		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// Buttons
		Button startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v(TAG, "Clicked Start...");
				startRecording();
			}
		});
		
		Button stopButton = (Button) findViewById(R.id.stopButton);
		stopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v(TAG, "Clicked Stop...");
				stopRecording();
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
	  // Release the Recorder
	  Log.v(TAG, "Paused...");
	  if (mRecorder != null) {
	    mRecorder.release();
	    mRecorder = null;
	  } 
	}
	
	// Recording
	public void startRecording() {		  
		  // Set Directory
		  Log.v(TAG, "recFileName is " + recFileName);
		  recFileName += "//REC//" + "file" + ".3gp";
		  Log.v(TAG, "Now recFileName is " + recFileName);
		  // Set MediaRecorder
		  mRecorder = new MediaRecorder();
		  mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		  mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		  mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		  mRecorder.setOutputFile(recFileName);
		  Log.v(TAG, "Set... ");
		  // Prepare
		  try {
			Log.v(TAG, "Preparing... ");
		    mRecorder.prepare();
		  } 
		  catch (IOException e) {
		    e.printStackTrace();
		  }
		  // Record
		  Log.v(TAG, "Starting...");
		  mRecorder.start();
	}

	public void stopRecording() {
		  Log.v(TAG, "Stopping...");
		  mRecorder.stop();
		  mRecorder.release();
		  mRecorder = null;
	}

}
