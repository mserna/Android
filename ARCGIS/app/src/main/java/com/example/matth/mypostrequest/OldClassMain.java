/*package com.example.matth.mypostrequest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISLocalTiledLayer;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;

public class MainActivity extends AppCompatActivity {

    //http://stackoverflow.com/questions/20062552/arcgis-how-to-get-device-location
    private GraphicsLayer myGraphicalLayer;
    public MapView myMapView;
    public ArcGISLocalTiledLayer myBaseLayer;
    private LocationManager myLocationManager;
    private LocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_main);
        myLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myLocationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, myLocationListener);
        myLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,0,myLocationListener);
        myMapView = (MapView)findViewById(R.id.localMap);
        myMapView.addLayer(myBaseLayer);
        myGraphicalLayer = new GraphicsLayer();
    }

    private void SetMyLocationPoint(final double x, final double y) {
        PictureMarkerSymbol myPin = new PictureMarkerSymbol(getResources().getDrawable(
                R.drawable.img1));

        Point wgspoint = new Point(x, y);
        Point mapPoint = (Point) GeometryEngine.project(wgspoint, SpatialReference.create(4326),
                myMapView.getSpatialReference());

        Graphic myPinGraphic = new Graphic(mapPoint, myPin);

        try {
            myGraphicalLayer.removeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        myGraphicalLayer.addGraphic(myPinGraphic);
        myGraphicalLayer.setVisible(true);
        myMapView.addLayer(myGraphicalLayer);

    }

    public class MyLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location loc) {
            SetMyLocationPoint(loc.getLongitude(), loc.getLatitude());
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "provider enabled", Toast.LENGTH_SHORT)
                    .show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "provider disabled", Toast.LENGTH_SHORT)
                    .show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}
*/