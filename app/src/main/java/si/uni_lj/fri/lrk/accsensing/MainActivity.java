package si.uni_lj.fri.lrk.accsensing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private static final String TAG = "MainActivity";
    SensorManager mManager;
    Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Instantiate sensor manager
        mManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // TODO: Check if sensor present, set mSensor if so
        if (mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            mSensor = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: Register sensor listening
        mManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // TODO: Unregister sensor listening
        mManager.unregisterListener(this);
    }

    // TODO: Override onSensorChanged
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // TODO: don't do heavy processing in this function
        //  start a new Thread if needed

        double x = sensorEvent.values[0];
        double y = sensorEvent.values[1];
        double z = sensorEvent.values[2];

        double mean = (x + y + z)/3;

        Log.d(TAG, "X: "+x+" Y: "+y+" Z: "+z);

        TextView tvx = findViewById(R.id.tv_x);
        tvx.setText("X: "+x);
        TextView tvy = findViewById(R.id.tv_y);
        tvy.setText("Y: "+y);
        TextView tvz = findViewById(R.id.tv_z);
        tvz.setText("Z: "+z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
