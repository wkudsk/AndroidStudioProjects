package com.example.wkudsk.whereareyou;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //if these are true, then we have permission.
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView Address = (TextView) findViewById(R.id.Address);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                displayLocation(location);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            //if we dont have permission, ask for it.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            displayLocation(lastKnownLocation);

        }
    }

    public void displayLocation(Location location)
    {
        TextView Latitude = (TextView) findViewById(R.id.Latitude);
        TextView Longitude = (TextView) findViewById(R.id.Longitude);
        TextView Accuracy = (TextView) findViewById(R.id.Accuracy);
        TextView Altitude = (TextView) findViewById(R.id.Altitude);
        TextView Street = (TextView) findViewById(R.id.Street);
        TextView Locality = (TextView) findViewById(R.id.Locality);
        TextView Postal = (TextView) findViewById(R.id.Postal);
        TextView Country = (TextView) findViewById(R.id.Country);

        //sets the latitude, longitude, Accuracy and Altitude
        Latitude.setText("Latitude: " + Double.toString(location.getLatitude()));
        Longitude.setText("Longitude: " + Double.toString(location.getLongitude()));
        Accuracy.setText("Accuracy: " + Float.toString(location.getAccuracy()));
        Altitude.setText("Altitude: " + Double.toString(location.getAltitude()));

        //Finds nearby addresses
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (listAddresses != null && listAddresses.size() > 0) {
                    String address = "";
                if (listAddresses.get(0).getSubThoroughfare() != null)
                    address += listAddresses.get(0).getSubThoroughfare() + " ";
                if (listAddresses.get(0).getThoroughfare() != null)
                    address += listAddresses.get(0).getThoroughfare();

                Street.setText(address);

                if (listAddresses.get(0).getLocality() != null)
                    Locality.setText(listAddresses.get(0).getLocality());
                if (listAddresses.get(0).getPostalCode() != null)
                    Postal.setText(listAddresses.get(0).getPostalCode());
                if (listAddresses.get(0).getCountryName() != null)
                    Country.setText(listAddresses.get(0).getCountryName());



            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }




}
