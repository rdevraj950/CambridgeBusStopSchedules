package com.rajkishandevraj.busstopschedules;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rajkishandevraj.busstopschedules.ArrayAdapter.BusArrayAdapter;
import com.rajkishandevraj.busstopschedules.AsyncTasks.VixConnectBusArrivalsAsyncTask;
import com.rajkishandevraj.busstopschedules.Bus.DebugBus;
import com.rajkishandevraj.busstopschedules.Bus.IBus;
import com.rajkishandevraj.busstopschedules.BusProviders.DebugBusProvider;
import com.rajkishandevraj.busstopschedules.Factories.BusProviderFactory;

public class BusStopActivity extends AppCompatActivity {
    private String _url;
    private SwipeRefreshLayout _refreshSwipe;
    private ArrayAdapter<IBus> busArrayAdapter;
    private DebugBusProvider debugBusProvider;
    private VixConnectBusArrivalsAsyncTask vixConnectBusArrivalsAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop_schedule);

        Bundle busStopURLBndl = getIntent().getExtras();
        _url = "http://cambridgeshire.acisconnect.com/Text/WebDisplay.aspx?stopRef=" + busStopURLBndl.getString("busStopUrl");
        debugBusProvider = new DebugBusProvider();
        vixConnectBusArrivalsAsyncTask = new VixConnectBusArrivalsAsyncTask(_url, this, debugBusProvider);
        vixConnectBusArrivalsAsyncTask.execute();
        populateListView();

        _refreshSwipe = (SwipeRefreshLayout) findViewById(R.id.swipeFresh);
        _refreshSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                vixConnectBusArrivalsAsyncTask.execute();
                populateListView();
                _refreshSwipe.setRefreshing(false);
            }
        });
    }
    private void populateListView(){
        busArrayAdapter =  new BusArrayAdapter(this, R.layout.bus_card, debugBusProvider.getBuses());

        ListView listView = (ListView) findViewById(R.id.listViewBusStopSchedule);
        listView.setAdapter(busArrayAdapter);
    }
}
