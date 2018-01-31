package com.example.wkudsk.downloadingimagefromtheweb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button button;
    ImageView internetImage;




    public void downloadImage(View view) throws ExecutionException, InterruptedException {
        view.setVisibility(View.INVISIBLE);
        internetImage.setVisibility(View.VISIBLE);

        //https://res.cloudinary.com/teepublic/image/private/s--Ug0iCq1F--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1488911584/production/designs/1298385_1.jpg
        DownloadTask task = new DownloadTask();
        Bitmap myImage;

        myImage = task.execute("https://res.cloudinary.com/teepublic/image/private/s--Ug0iCq1F--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1488911584/production/designs/1298385_1.jpg").get();

        internetImage.setImageBitmap(myImage);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internetImage = (ImageView) findViewById(R.id.internetImage);
        button = (Button) findViewById(R.id.downloadButton);

    }

    public class DownloadTask extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls) {

            Bitmap result;
            URL url;
            HttpURLConnection urlConnection = null;

            try
            {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();

                result = BitmapFactory.decodeStream(in);

                return result;
            }

            catch(MalformedURLException e)
            {
                e.printStackTrace();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
