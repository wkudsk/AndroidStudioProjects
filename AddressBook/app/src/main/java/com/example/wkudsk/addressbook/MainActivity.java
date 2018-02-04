package com.example.wkudsk.addressbook;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> addressBook;
    public static ArrayList<LatLng> locations;
    public static ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Starting up listview
        ListView listView = (ListView) findViewById(R.id.listView);
        addressBook = new ArrayList<String>();
        locations = new ArrayList<LatLng>();
        addressBook.add("Add to your address book...");
        LatLng openLatLng = new LatLng(40.12343, 40.12134);
        locations.add(openLatLng);




        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addressBook);
        listView.setAdapter(arrayAdapter);

        //when a place on the list is clicked, go to it.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);


            }
        });

    }
}
