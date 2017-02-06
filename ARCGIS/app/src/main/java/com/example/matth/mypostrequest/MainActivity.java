package com.example.matth.mypostrequest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISLocalTiledLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.LinearUnit;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.geometry.Unit;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleMarkerSymbol;

import android.os.AsyncTask.*;

public class MainActivity extends AppCompatActivity {

    //http://stackoverflow.com/questions/20062552/arcgis-how-to-get-device-location
    private GraphicsLayer myGraphicalLayer;
    public MapView myMapView;
    public ArcGISLocalTiledLayer myBaseLayer;
    private LocationManager myLocationManager;
    private LocationListener myLocationListener;
    private Status status;
    private GraphicsLayer gl;
    private LocationDisplayManager mLDM;
    private SpatialReference mMapSr;

    @Override
    protected void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_main);
        myMapView = (MapView) findViewById(R.id.localMap);
        viewMap();
        showMarker();
    }

    public void viewMap() {
        myMapView.setOnStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusChanged(Object o, STATUS status) {
                if (status == STATUS.INITIALIZED && o instanceof MapView) {
                    myMapView.getLocationDisplayManager().start();
                    mMapSr = myMapView.getSpatialReference();
                    setupLocationListener();
                    myMapView.addLayer(myBaseLayer);
                    gl = new GraphicsLayer();
                    myMapView.addLayer(gl);
                }
            }
        });
    }

    private void setupLocationListener() {
        if ((myMapView != null) && (myMapView.isLoaded())) {
            mLDM = myMapView.getLocationDisplayManager();
            mLDM.setLocationListener(new LocationListener() {

                boolean locationChanged = false;

                // Zooms to the current location when first GPS fix arrives.
                @Override
                public void onLocationChanged(Location loc) {
                    if (!locationChanged) {
                        locationChanged = true;
                        zoomToLocation(loc);

                        // After zooming, turn on the Location pan mode to show the location
                        // symbol. This will disable as soon as you interact with the map.
                        mLDM.setAutoPanMode(LocationDisplayManager.AutoPanMode.LOCATION);
                    }
                }

                @Override
                public void onProviderDisabled(String arg0) {
                }

                @Override
                public void onProviderEnabled(String arg0) {
                }

                @Override
                public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
                }
            });

            mLDM.start();
        }
    }

    private void zoomToLocation(Location loc) {
        Point mapPoint = getAsPoint(loc);
        Unit mapUnit = mMapSr.getUnit();
        double zoomFactor = Unit.convertUnits(20,
                Unit.create(LinearUnit.Code.MILE_US), mapUnit);
        Envelope zoomExtent = new Envelope(mapPoint, zoomFactor, zoomFactor);
        myMapView.setExtent(zoomExtent);
    }

    private Point getAsPoint(Location loc) {
        Point wgsPoint = new Point(loc.getLongitude(), loc.getLatitude());
        return (Point) GeometryEngine.project(wgsPoint, SpatialReference.create(4326),
                mMapSr);
    }

    public void showMarker()
    {
        SimpleMarkerSymbol boueyMarker = new SimpleMarkerSymbol(new Color().RED, 10, SimpleMarkerSymbol.STYLE.CIRCLE);
        try {
            //myMapView.getLocationDisplayManager().setDefaultSymbol(boueyMarker);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayOtherMarkers(double x, double y)
    {
        Point point = new Point(x,y);
        Graphic graphic = new Graphic(point,new SimpleMarkerSymbol(new Color().BLUE, 10, SimpleMarkerSymbol.STYLE.CIRCLE));
        gl.addGraphic(graphic);
    }
}
