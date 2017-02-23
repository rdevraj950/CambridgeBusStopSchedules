package com.rajkishandevraj.busstopschedules.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.rajkishandevraj.busstopschedules.BusProviders.DebugBusProvider;
import com.rajkishandevraj.busstopschedules.BusProviders.IBusProvider;
import com.rajkishandevraj.busstopschedules.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VixConnectBusArrivalsAsyncTask extends AsyncTask<Void, Void, Document>{
    private Context _context;
    private IBusProvider _iBusProvider;
    private String _URL;

    public VixConnectBusArrivalsAsyncTask(String URL, Context context, IBusProvider iBusProvider) {
        _context = context;
        _iBusProvider = iBusProvider;
        _URL = URL;
    }

    @Override
    protected Document doInBackground(Void... params) {
        //Log.d("Im in DoBack", "HeloMe");
        //Log.e("Help", "I am HerE");
        try {
            return Jsoup.connect("http://www.google.com").get();
        } catch (IOException e) {
            ((Activity) _context).setTitle("Failed");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Document doc) {
        _iBusProvider.OnPostExecuteComplete(_context, doc);
        lastUpdated();
    }

    private void lastUpdated(){
        TextView lastUpdateTxtView = (TextView) ((Activity)_context).findViewById(R.id.lastUpdatedTxtView);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        lastUpdateTxtView.setText("Last Updated: " + dateFormat.format(cal.getTime()));
    }
}
