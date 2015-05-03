package com.example.tamer.motionpositionsensorexample;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/* an example of how to use the sensors on mobile device, we use TYPE_GRAVITY to use gravity
* sensor, there is so many other that you could play with.
* for more info:
*
* http://developer.android.com/guide/topics/sensors/sensors_motion.html
* http://developer.android.com/guide/topics/sensors/sensors_position.html
*
* also, if you see the power value for m/s^2 on the layout file, you'll notice that it's superscripted,
* see more info about it at:
* http://en.wikipedia.org/wiki/Unicode_subscripts_and_superscripts
* */
public class MainActivity extends ActionBarActivity implements SensorEventListener {

    SensorManager mySensorManager;
    protected Sensor mySensor;
    private TextView xAxis, yAxis, zAxis;
    protected Float xAxisData;
    protected Float yAxisData;
    protected Float zAxisData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xAxis = (TextView) findViewById(R.id.xAxis);
        yAxis = (TextView) findViewById(R.id.yAxis);
        zAxis = (TextView) findViewById(R.id.zAxis);

        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);// change the type from here and enjoy


    }

    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(this, mySensor, 250000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySensorManager.unregisterListener(this, mySensor);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        xAxisData = event.values[0];
        yAxisData = event.values[1];
        zAxisData = event.values[2];

        xAxis.setText(xAxisData.toString());
        yAxis.setText(yAxisData.toString());
        zAxis.setText(zAxisData.toString());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
