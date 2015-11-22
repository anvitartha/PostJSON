package com.example.sekhar.contxt;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Sekhar on 05/11/2015.
 */
public class PostJson {
     //Context postContext;
    // String name = "uday";

    public static String HttpAction(String url, DataToPost lDataToPost){

        /* Initialize variables */
        InputStream inputStream = null;
        String result = "";
        JSONArray responseArry = null;
        HttpEntity inputstream = null;

          try {
              // 1. create HttpClient
              HttpClient httpclient = new DefaultHttpClient();
              HttpPost httpPost;
              HttpGet httpGet;

              // 2. make POST request to the given URL
              httpPost = new HttpPost(url);

              String json = "";

              // 3. build jsonObject
              JSONObject jsonObject = new JSONObject();
              jsonObject.accumulate("name", lDataToPost.getName());
              jsonObject.accumulate("country", lDataToPost.getCountry());
              jsonObject.accumulate("twitter", lDataToPost.getTwitter());

              // 4. convert JSONObject to JSON to String
              json = jsonObject.toString();

              // 5. set json to StringEntity
              StringEntity se = new StringEntity(json);

              // 6. set httpPost Entity
              httpPost.setEntity(se);

              // 7. Set some headers to inform server about the type of the content   
              httpPost.setHeader("Accept", "application/json");
              httpPost.setHeader("Content-type", "application/json");

              // 8. Execute POST request to the given URL
              HttpResponse httpResponse = httpclient.execute(httpPost);

              // 9. receive response as inputStream
              inputstream = httpResponse.getEntity();

              // 10. convert inputstream to string
              if (inputstream != null){
                  result = EntityUtils.toString(inputstream);
                  if(result != null){
                      JSONArray resultarray = new JSONArray(result);

                      for(int i=0;i<resultarray.length();i++)
                      {
                          JSONObject e = resultarray.getJSONObject(i);
                          String twittervalue = e.getString("twitter");
                      }

                  }
              }
              else {
                  result = "Did not work!";
              }
          } catch (Exception e) {
              Log.d("InputStream", e.getLocalizedMessage());
          }

          // 11. return result
          return result;

    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
      //  System.out.println()
        return result;

    }

}
