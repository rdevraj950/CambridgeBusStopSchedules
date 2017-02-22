package com.rajkishandevraj.busstopschedules.AsyncTaskListeners;

import android.app.Activity;
import android.content.Context;

import com.rajkishandevraj.busstopschedules.Bus.Bus;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class VixConnectAsyncTaskListener implements IAsyncTaskListener<Document> {
    @Override
    public void OnPostExecuteComplete(Context context, Document document) {
        Elements details = document.getElementsByClass("emphasise");
        Elements departures = document.getElementsByClass("body-cell");

        try {
            ((Activity)context).setTitle(details.get(1).text());
        } catch(Exception e){((Activity)context).setTitle("fail");}

        int counter = 1;
        Bus tmpBus = new Bus();
        for (int i = 0; i < departures.size(); i++) {
            if (counter == 1){
                tmpBus.setBusNumber(departures.get(i).ownText());
                counter++;
                continue;
            }
            if (counter == 2){
                counter++;
                continue;
            }
            if (counter == 3){
                tmpBus.setDestination(departures.get(i).ownText());
                counter++;
                continue;
            }
            if (counter == 4){
                counter++;
                continue;
            }
            if (counter == 5){
                tmpBus.setArrivalTime(departures.get(i).ownText());
                counter++;
                continue;
            }
            if (counter == 6){
                counter = 0;
                counter++;
                continue;
            }
        }
    }
}
