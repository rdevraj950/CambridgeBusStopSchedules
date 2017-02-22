package com.rajkishandevraj.busstopschedules.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.rajkishandevraj.busstopschedules.AsyncTaskListeners.IAsyncTaskListener;
import com.rajkishandevraj.busstopschedules.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VixConnectBusArrivalsAsyncTask extends AsyncTask<String, Void, Document>{
    private Context _context;
    private IAsyncTaskListener _iAsyncTaskListener;
    private String _URL;

    public VixConnectBusArrivalsAsyncTask(String URL, Context context, IAsyncTaskListener iAsyncTaskListener) {
        _context = context;
        _iAsyncTaskListener = iAsyncTaskListener;
        _URL = URL;
    }

    @Override
    protected Document doInBackground(String... params) {
        try {
            return Jsoup.connect(_URL).get();
        } catch (IOException e) {
            ((Activity)_context).setTitle("fail");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Document doc) {
        _iAsyncTaskListener.OnPostExecuteComplete(_context, doc);
        lastUpdated();
    }

    private void lastUpdated(){
        TextView lastUpdateTxtView = (TextView) ((Activity)_context).findViewById(R.id.lastUpdatedTxtView);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        lastUpdateTxtView.setText("Last Updated: " + dateFormat.format(cal.getTime()));
    }
}
