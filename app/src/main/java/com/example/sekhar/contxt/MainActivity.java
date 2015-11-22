package com.example.sekhar.contxt;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PostJson PostJ;
    DataToPost dataToPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPost= (Button)findViewById(R.id.post);
        String httpResult;
        dataToPost = new DataToPost();

        dataToPost.setCountry("India1");
        dataToPost.setName("Srilu1");
        dataToPost.setTwitter("Akshat");

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String postOnURL = "http://hmkcode.appspot.com/jsonservlet";
                new HttpAsyncTask(getApplicationContext()).execute(postOnURL);

                //Toast.makeText(getApplicationContext(), httpResult , Toast.LENGTH_LONG).show();
             }
        });


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

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        Context mainContext;

        @Override
        protected String doInBackground(String... urls) {

            String  httpResult;

            httpResult = PostJ.HttpAction(urls[0],dataToPost);
         //   Toast.makeText(getBaseContext(), httpResult, Toast.LENGTH_LONG).show();

            return httpResult;
        }

        public HttpAsyncTask(Context context){
            this.mainContext = context;
        }
    }

}

// REMOVED CONTEXT VARIABLE FROM HTTPACTION
