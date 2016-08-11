package com.example.matth.geolocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by matth on 7/16/2016.
 */
public class MainActivity extends AppCompatActivity {

    private Button button1;
    @Override
    protected void onCreate(Bundle savedBundleInstance)
    {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_main);
        final Intent GPSIntent = new Intent(this, GPS.class);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(GPSIntent);
            }
        });
    }
}
