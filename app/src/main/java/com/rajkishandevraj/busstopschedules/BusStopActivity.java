package com.rajkishandevraj.busstopschedules;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rajkishandevraj.busstopschedules.ArrayAdapter.BusArrayAdapter;
import com.rajkishandevraj.busstopschedules.AsyncTasks.VixConnectBusArrivalsAsyncTask;
import com.rajkishandevraj.busstopschedules.Bus.IBus;
import com.rajkishandevraj.busstopschedules.Factories.BusProviderFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BusStopActivity extends AppCompatActivity {
    private String _url;
    private SwipeRefreshLayout _refreshSwipe;
    private ArrayAdapter<IBus> busArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop_schedule);

        Bundle busStopURLBndl = getIntent().getExtras();
        _url = "http://cambridgeshire.acisconnect.com/Text/WebDisplay.aspx?stopRef=" + busStopURLBndl.getString("busStopUrl");

        new VixConnectBusArrivalsAsyncTask(_url).execute();
        populateListView();
        lastUpdated();

        _refreshSwipe = (SwipeRefreshLayout) findViewById(R.id.swipeFresh);
        _refreshSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new VixConnectBusArrivalsAsyncTask(_url).execute();
                populateListView();
                lastUpdated();
                _refreshSwipe.setRefreshing(false);
            }
        });
    }
    private void populateListView(){
        busArrayAdapter =  new BusArrayAdapter(this, R.layout.bus_card, BusProviderFactory.instance().getBuses());

        ListView listView = (ListView) findViewById(R.id.listViewBusStopSchedule);
        listView.setAdapter(busArrayAdapter);
    }

    private void lastUpdated(){
        TextView lastUpdateTxtView = (TextView) findViewById(R.id.lastUpdatedTxtView);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        lastUpdateTxtView.setText("Last Updated: " + dateFormat.format(cal.getTime()));
    }
}
