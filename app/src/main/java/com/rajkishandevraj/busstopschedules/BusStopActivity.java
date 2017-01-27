package com.rajkishandevraj.busstopschedules;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
//This entire class needs to be refactored into a Repository pattern
public class BusStopActivity extends AppCompatActivity {
    private String url;
    private SwipeRefreshLayout refreshSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop_schedule);

        Bundle busStopURLBndl = getIntent().getExtras();
        url = "http://cambridgeshire.acisconnect.com/Text/WebDisplay.aspx?stopRef=" + busStopURLBndl.getString("busStopUrl");

        new VixConnectScraper().execute();

        refreshSwipe = (SwipeRefreshLayout) findViewById(R.id.swipeFresh);
        refreshSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new VixConnectScraper().execute();
                refreshSwipe.setRefreshing(false);
            }
        });
    }

    private class VixConnectScraper extends AsyncTask<Void, Void, Void> {
        protected Elements details;
        protected Elements departures;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(url).get();
                details = doc.getElementsByClass("emphasise");
                departures = doc.getElementsByClass("body-cell");
            } catch (IOException e) {
                setTitle("fail");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                setTitle(details.get(1).text());
            } catch(Exception e){setTitle("fail");}

            TextView busStopSchedule = (TextView) findViewById(R.id.busStopSchedulesTxtView);
            busStopSchedule.setText("");

            int counter = 1;
            for (int i = 0; i < departures.size(); i++) {
                if (counter == 1){
                    busStopSchedule.append("Bus Number: " + departures.get(i).ownText() + "\n");
                    counter++;
                    continue;
                }
                if (counter == 2){
                    counter++;
                    continue;
                }
                if (counter == 3){
                    busStopSchedule.append("Destination: " + departures.get(i).ownText()+ "\n");
                    counter++;
                    continue;
                }
                if (counter == 4){
                    counter++;
                    continue;
                }
                if (counter == 5){
                    if (departures.size() - 2 == i){
                        busStopSchedule.append("Time: " + departures.get(i).ownText());
                        counter++;
                        continue;
                    }
                    busStopSchedule.append("Time: " + departures.get(i).ownText() + "\n\n");
                    counter++;
                    continue;
                }
                if (counter == 6){

                    counter = 0;
                    counter++;
                    continue;
                }
            }
            lastUpdated();
        }

        private void lastUpdated(){
            TextView lastUpdateTxtView = (TextView) findViewById(R.id.lastUpdatedTxtView);
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            lastUpdateTxtView.setText("Last Updated: " + dateFormat.format(cal.getTime()));
        }
    }
}
