package com.example.wkudsk.weather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    TextView weatherInfo;
    EditText city;
    Button button;

    public void findWeather(View view) throws UnsupportedEncodingException {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(city.getWindowToken(), 0);

        try
        {
            DownloadTask task = new DownloadTask();
            String encodedCityName = URLEncoder.encode(city.getText().toString(), "UTF-8");
            task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName +"&appid=e79554f8c9151b466461186a8b7a7b5a");
        }
        catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
        }



    }

    public class DownloadTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != -1)
                {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;

            } catch (Exception e) {

                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            try {
                String message = "";
                JSONObject jsonObject = new JSONObject(result);

                String weather = jsonObject.getString("weather");

                Log.i("Website content: ", weather);

                JSONArray array = new JSONArray(weather);

                for(int i = 0; i < array.length(); i++)
                {
                    JSONObject part = array.getJSONObject(i);

                    String main = "";
                    main = part.getString("main");
                    String description = "";
                    description = part.getString("description");


                    Log.i("main", part.getString("main"));
                    Log.i("description", part.getString("description"));

                    if(main != "" && description != "")
                    {
                        message = main + ": " + description + "\r\n";
                    }
                }

                if(message != "")
                {
                    weatherInfo.setText(message);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherInfo = findViewById(R.id.result);
        city = findViewById(R.id.inputCity);
        button = findViewById(R.id.button);




    }
}
