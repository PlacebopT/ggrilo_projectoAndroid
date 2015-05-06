package ggrilo.millionaireapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ContentIntentService extends IntentService {

    public ContentIntentService()
    {
        // Invocar o construtor do pai indicando o nome da queue que vai conter o trabalho a executar.
        // Tipicamente é o próprio nome da classe.
        super("ContentIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int count = 0;
        try
        {
            URL url = new URL("http://54.187.166.51:81/questions");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String res = "";
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null)
                res += line;

            JSONObject response = new JSONObject(res);



            //double temp = response.getJSONObject("main").getDouble("temp");


        }
        catch(Exception e)
        {
            //
        }
    }


}
