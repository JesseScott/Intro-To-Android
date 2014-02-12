/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.accelerometerplay;



import java.io.Console;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
//import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;



public class AccelerometerPlayActivity extends Activity {

    private SimulationView mSimulationView;
    private SensorManager mSensorManager;
    private WindowManager mWindowManager;
    private Display 	  mDisplay;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get an instance of the SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get an instance of the WindowManager
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        // instantiate our simulation view and set it as the activity's content
        mSimulationView = new SimulationView(this);
        setContentView(mSimulationView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Start the simulation
        mSimulationView.startSimulation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop the simulation
        mSimulationView.stopSimulation();

    }

    class SimulationView extends View implements SensorEventListener {

        private Sensor mAccelerometer;
        private float mSensorX;
        private float mSensorY;
        private float mSensorZ;

        Paint paint = new Paint();
        
        public SimulationView(Context context) {
            super(context);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

           // DisplayMetrics metrics = new DisplayMetrics();
            //getWindowManager().getDefaultDisplay().getMetrics(metrics);

        }

        public void startSimulation() {
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        }

        public void stopSimulation() {
            mSensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
                return;

            switch (mDisplay.getRotation()) {
                case Surface.ROTATION_0:
                    mSensorX = event.values[0];
                    mSensorY = event.values[1];
                    mSensorZ = event.values[2];
                    break;
                case Surface.ROTATION_90:
                    mSensorX = -event.values[1];
                    mSensorY = event.values[0];
                    mSensorZ = event.values[2];
                    break;
                case Surface.ROTATION_180:
                    mSensorX = -event.values[0];
                    mSensorY = -event.values[1];
                    mSensorZ = event.values[2];
                    break;
                case Surface.ROTATION_270:
                    mSensorX = event.values[1];
                    mSensorY = -event.values[0];
                    mSensorZ = event.values[2];
                    break;
            }

        }
        
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        		//
        }

        @Override
        protected void onDraw(Canvas canvas) {
        	
        		paint.setColor(Color.WHITE);
        		paint.setTextSize(36);
        		canvas.drawText(String.valueOf("OUR X SENSOR IS: " + mSensorX), 100, 100, paint);
        		canvas.drawText(String.valueOf("OUR Y SENSOR IS: " + mSensorY), 100, 200, paint);
        		canvas.drawText(String.valueOf("OUR Z SENSOR IS: " + mSensorZ), 100, 300, paint);

            invalidate();
        }
     
    }
}
