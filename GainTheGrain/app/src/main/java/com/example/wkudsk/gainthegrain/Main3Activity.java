package com.example.wkudsk.gainthegrain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ListView listView = (ListView) findViewById(R.id.presets);

        final ArrayList<String> presets = new ArrayList<String>();
        presets.add("Challah");
        presets.add("Standard Sourdough Loaf");
        presets.add("Plain Bread Dough");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, presets);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
                    intent.putExtra("challah", presets.get(position));
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("bread", presets.get(position));
                    startActivity(intent);
                }
            }
        });
    }
}
