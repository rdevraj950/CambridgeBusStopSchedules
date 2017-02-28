package com.rajkishandevraj.busstopschedules.AsyncTasks;

import android.os.AsyncTask;

import com.rajkishandevraj.busstopschedules.Factories.BusProviderFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class VixConnectBusArrivalsAsyncTask extends AsyncTask<Void, Void, Document>{
    private String _URL;

    public VixConnectBusArrivalsAsyncTask(String URL) {
        _URL = URL;
    }

    @Override
    protected Document doInBackground(Void... params) {
        try {
            return Jsoup.connect(_URL).get();
        } catch (IOException e) {}
        return null;
    }

    @Override
    protected void onPostExecute(Document doc) {
        BusProviderFactory.instance().OnPostExecuteComplete(doc);
    }
}
